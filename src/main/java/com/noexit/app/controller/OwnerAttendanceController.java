package com.noexit.app.controller;

import com.noexit.app.common.AuthUtil;
import com.noexit.app.common.PaginateUtil;
import com.noexit.app.model.AttendCrew;
import com.noexit.app.model.AttendForm;
import com.noexit.app.model.AttendItemDTO;
import com.noexit.app.model.AttendanceListDTO;
import com.noexit.app.model.User;
import com.noexit.app.service.AttendanceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/owner/attendance")
public class OwnerAttendanceController {

    private final AttendanceService attendanceService;
    private final PaginateUtil paginateUtil;

    @GetMapping("") // 출석체크
    public String attendance(@RequestParam(name = "page", defaultValue = "1") int currentPage,
                             HttpSession session, HttpServletRequest req, Model model) {
        String redirect = AuthUtil.checkStaff(session);
        if (redirect != null)
        	return redirect;

        User loginUser = (User) session.getAttribute("loginUser");
        String role = (String) session.getAttribute("role");

        try {
            int size = 10; // 한 화면에 보여주는 예약 수
            int totalPage = 0;
            int dataCount = 0;

            Map<String, Object> map = new HashMap<>();
            Long userId = loginUser.getUserId();
            map.put("ownerUserId", userId);
            map.put("managerUserId", userId);

            // 데이터 개수 파악
            dataCount = attendanceService.dataCountByRole(map, role);
            if (dataCount != 0) {
                // 전체 페이지 수 계산
                totalPage = paginateUtil.pageCount(dataCount, size);
            }

            // 페이지 번호 보정
            currentPage = Math.min(currentPage, totalPage);

            // 시작점 계산
            int offset = (currentPage - 1) * size;
            if (offset < 0) offset = 0;
            map.put("offset", offset);
            map.put("size", size);

            // 리스트 가져오기
            List<AttendanceListDTO> attendList = attendanceService.selectAttendListByRole(map, role);

            // 페이징 처리 및 URL 생성
            String cp = req.getContextPath();
            String listUrl = cp + "/owner/attendance";
            String paging = paginateUtil.paging(currentPage, totalPage, listUrl);

            Map<String, List<Long>> status = attendanceService.checkStatus(session, attendList);

            model.addAttribute("doneList", status.get("done"));
            model.addAttribute("partialList", status.get("partial"));
            model.addAttribute("attendList", attendList);
            model.addAttribute("paging", paging);
            model.addAttribute("dataCount", dataCount);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("size", size);

        } catch (Exception e) {
            log.info("attendance : ", e);
        }

        return "owner/attendance";
    }

    @GetMapping("/check")
    public String check(@RequestParam(name = "reservationId") Long reservationId, HttpSession session, Model model) {
        
    	String redirect = AuthUtil.checkStaff(session);
        
    	if (redirect != null) 
        	return redirect;
        
        List<AttendCrew> crewList = attendanceService.selectCrewByReservationId(reservationId);

        // 이전 선택값 복원
        @SuppressWarnings("unchecked")
        List<AttendItemDTO> drafts = (List<AttendItemDTO>) session.getAttribute("attendDraft");
        if (drafts != null && crewList != null) 
        {
            for (AttendCrew c : crewList) 
            {
                for (AttendItemDTO d : drafts) 
                {
                    if (reservationId.equals(d.getReservationId()) && c.getUserId().equals(d.getUserId())) 
                    {
                        c.setAttendStatusId(d.getAttendStatusId());
                        break;
                    }
                }
            }
        }

        model.addAttribute("reservationId", reservationId);
        model.addAttribute("crewList", crewList);
        return "owner/attendanceCheck";
    }

    // 개별 출석체크 임시저장 (세션 누적)
    @PostMapping("/saveDraft")
    public String saveDraft(AttendForm form, HttpSession session) {
        String redirect = AuthUtil.checkStaff(session);
        if (redirect != null) 
        	return redirect;

        try {
            attendanceService.saveDraft(form, session);
        } catch (Exception e) {
            log.info("saveDraft : ", e);
        }
        return "redirect:/owner/attendance";
    }

    // 최종확인버튼 : ATTENDANCE + ATTENDANCE_DETAIL INSERT
    @PostMapping("/finalize")
    public String finalizeAttend(HttpSession session) {
        String redirect = AuthUtil.checkStaff(session);
        if (redirect != null) return redirect;

        try {
            User loginUser = (User) session.getAttribute("loginUser");
            attendanceService.finalizeAttendance(session, loginUser.getUserId());
        } catch (Exception e) {
            log.info("finalize : ", e);
        }
        return "redirect:/owner/attendance";
    }

    @GetMapping("/history") // 출석기록
    public String history(@RequestParam(name = "page", defaultValue = "1") int currentPage,
                          HttpSession session, HttpServletRequest req, Model model) {
        String redirect = AuthUtil.checkStaff(session);
        if (redirect != null)
        	return redirect;

        User loginUser = (User) session.getAttribute("loginUser");
        String role = (String) session.getAttribute("role");

        try {
            int size = 10; // 한 화면에 보여주는 기록 수
            int totalPage = 0;
            int dataCount = 0;

            Map<String, Object> map = new HashMap<>();
            Long userId = loginUser.getUserId();
            map.put("ownerUserId", userId);
            map.put("managerUserId", userId);

            // 데이터 개수 파악
            dataCount = attendanceService.dataCountHistoryByRole(map, role);
            if (dataCount != 0) {
                // 전체 페이지 수 계산
                totalPage = paginateUtil.pageCount(dataCount, size);
            }

            // 페이지 번호 보정
            currentPage = Math.min(currentPage, totalPage);

            // 시작점 계산
            int offset = (currentPage - 1) * size;
            if (offset < 0) offset = 0;
            map.put("offset", offset);
            map.put("size", size);

            // 리스트 가져오기
            List<AttendanceListDTO> historyList = attendanceService.selectHistoryByRole(map, role);

            // 페이징 처리 및 URL 생성
            String cp = req.getContextPath();
            String listUrl = cp + "/owner/attendance/history";
            String paging = paginateUtil.paging(currentPage, totalPage, listUrl);

            model.addAttribute("historyList", historyList);
            model.addAttribute("paging", paging);
            model.addAttribute("dataCount", dataCount);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("size", size);

        } catch (Exception e) {
            log.info("history : ", e);
        }

        return "owner/attendanceHistory";
    }

}
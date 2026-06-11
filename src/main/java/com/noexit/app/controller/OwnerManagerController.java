package com.noexit.app.controller;

import com.noexit.app.common.AuthUtil;
import com.noexit.app.common.PaginateUtil;
import com.noexit.app.model.Manager;
import com.noexit.app.model.User;
import com.noexit.app.service.CafeService;
import com.noexit.app.service.ManagerService;
import com.noexit.app.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
@RequestMapping("/owner/manager")
public class OwnerManagerController {

    private final ManagerService managerService;
    private final UserService userService;
    private final CafeService cafeService;
    private final PaginateUtil paginateUtil;

    @GetMapping // 매니저 관리
    public String manager(@RequestParam(name = "page", defaultValue = "1") int currentPage,
                          HttpSession session, HttpServletRequest req, Model model) {
        String redirect = AuthUtil.checkOwner(session);
        if (redirect != null)
        return redirect;

        User loginUser = (User)session.getAttribute("loginUser");

        try {
            int size = 5; // 한 화면에 보여주는 매니저 수
            int totalPage = 0;
            int dataCount = 0;

            Map<String, Object> map = new HashMap<>();
            map.put("ownerUserId", loginUser.getUserId());

            // 데이터 개수 파악
            dataCount = managerService.dataCount(map);
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
            List<Manager> managerList = managerService.selectActiveByOwnerUserId(map);

            // 페이징 처리 및 URL 생성
            String cp = req.getContextPath();
            String listUrl = cp + "/owner/manager";
            String paging = paginateUtil.paging(currentPage, totalPage, listUrl);

            model.addAttribute("managerList", managerList);
            model.addAttribute("paging", paging);
            model.addAttribute("dataCount", dataCount);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("size", size);

        } catch (Exception e) {
            log.info("manager : ", e);
        }

        return "owner/manager";
    }

    @GetMapping("/enroll")
    public String managerEnrollForm(HttpSession session, Model model) {
        String redirect = AuthUtil.checkOwner(session);
        if (redirect != null)
        	return redirect;

        User loginUser = (User) session.getAttribute("loginUser");
        model.addAttribute("cafeList", cafeService.selectByUserId(loginUser.getUserId()));
        return "owner/managerEnrollForm";
    }

    @PostMapping("/enroll")
    public String managerEnroll(@RequestParam("loginId") String loginId,
                                @RequestParam("cafeId")  Long cafeId,
                                HttpSession session, Model model) {
        String redirect = AuthUtil.checkOwner(session);
        if (redirect != null) 
        	return redirect;

        User loginUser = (User) session.getAttribute("loginUser");
        User target = userService.findByLoginId(loginId);

        if (target == null) {
            model.addAttribute("errorMessage", "해당 아이디의 회원이 없습니다.");
            model.addAttribute("cafeList", cafeService.selectByUserId(loginUser.getUserId()));
            return "owner/managerEnrollForm";
        }

        Manager manager = new Manager();
        manager.setCafeId(cafeId);
        manager.setUserId(target.getUserId());
        try {
            managerService.enroll(manager);
        } catch (Exception e) {
            log.info("managerEnroll : ", e);
        }
        return "redirect:/owner/manager";
    }

    @PostMapping("/deact")
    public String managerDeact(@RequestParam("cafeId") Long cafeId
                              , @RequestParam("userId") Long userId
                              , HttpSession session) {
        String redirect = AuthUtil.checkOwner(session);
        if (redirect != null) 
        	return redirect;

        Manager manager = new Manager();
        manager.setCafeId(cafeId);
        manager.setUserId(userId);
        try {
            managerService.deact(manager);
        } catch (Exception e) {
            log.info("managerDeact : ", e);
        }
        return "redirect:/owner/manager";
    }
}
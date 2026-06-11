package com.noexit.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noexit.app.mapper.AttendanceMapper;
import com.noexit.app.model.AttendanceListDTO;
import com.noexit.app.model.AttendCrew;
import com.noexit.app.model.AttendForm;
import com.noexit.app.model.AttendItemDTO;
import com.noexit.app.model.Manner;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceServiceImpl implements AttendanceService {

	private final AttendanceMapper mapper;


	// 사장 출석 목록 (페이징 포함)
	@Override
	public List<AttendanceListDTO> selectListByOwnerUserId(Map<String, Object> map) {

		List<AttendanceListDTO> list = null;

		try {
			list = mapper.selectListByOwnerUserId(map);
		} catch (Exception e) {
			log.info("selectListByOwnerUserId : ", e);
		}

		return list;
	}

	// 매니저 출석 목록 (페이징 포함)
	@Override
	public List<AttendanceListDTO> selectListByManagerUserId(Map<String, Object> map) {

		List<AttendanceListDTO> list = null;

		try {
			list = mapper.selectListByManagerUserId(map);
		} catch (Exception e) {
			log.info("selectListByManagerUserId : ", e);
		}

		return list;
	}

	// 역할별 출석 목록 갯수
	@Override
	public int dataCountByRole(Map<String, Object> map, String role) {

		int result = 0;

		try {
			if ("OWNER".equals(role)) {
				result = mapper.dataCountByOwnerUserId(map);
			} else {
				result = mapper.dataCountByManagerUserId(map);
			}
		} catch (Exception e) {
			log.info("dataCountByRole : ", e);
		}

		return result;
	}

	@Override
	public List<AttendCrew> selectCrewByReservationId(Long reservationId) {

		List<AttendCrew> list = null;

		try {
			list = mapper.selectCrewByReservationId(reservationId);
		} catch (Exception e) {
			log.info("selectCrewByReservationId : ", e);
		}

		return list;
	}



	// 개별 출석체크 임시저장
	@Override
	public void saveDraft(AttendForm form, HttpSession session) throws Exception {

		try {
			@SuppressWarnings("unchecked")
			List<AttendItemDTO> drafts = (List<AttendItemDTO>) session.getAttribute("attendDraft");
			if (drafts == null) drafts = new ArrayList<>();

			// 같은 예약의 이전 draft 제거
			List<AttendItemDTO> newDrafts = new ArrayList<>();
			for (AttendItemDTO d : drafts) {
				if (!form.getReservationId().equals(d.getReservationId())) {
					newDrafts.add(d);
				}
			}

			// 이번 폼 항목 누적 (미정은 스킵)
			for (int i = 0; i < form.getUserIds().size(); i++) {
				Long statusId = form.getAttendStatusIds().get(i);
				if (statusId == null) continue;

				AttendItemDTO item = new AttendItemDTO();
				item.setReservationId(form.getReservationId());
				item.setUserId(form.getUserIds().get(i));
				item.setAttendStatusId(statusId);
				newDrafts.add(item);
			}

			session.setAttribute("attendDraft", newDrafts);

		} catch (Exception e) {
			log.info("saveDraft : ", e);
			throw e;
		}
	}


	// 최종확인 : ATTENDANCE + ATTENDANCE_DETAIL INSERT
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void finalizeAttendance(HttpSession session, Long staffUserId) throws Exception {

		try {
			@SuppressWarnings("unchecked")
			List<AttendItemDTO> drafts = (List<AttendItemDTO>) session.getAttribute("attendDraft");
			if (drafts == null || drafts.isEmpty()) return;

			// 중복 없는 reservationId 모으기
			List<Long> resIds = new ArrayList<>();
			for (AttendItemDTO d : drafts) {
				if (!resIds.contains(d.getReservationId())) {
					resIds.add(d.getReservationId());
				}
			}

			// 예약별 처리
			for (Long reservationId : resIds) {

			    AttendItemDTO head = new AttendItemDTO();

			    // 스케줄러가 먼저 박은 경우 → ATTENDANCE ID 재사용
			    Long existingId = mapper.selectAttendanceIdByReservationId(reservationId);

			    if (existingId != null) {
			        head.setAttendanceId(existingId);
			    } else {
			        // ATTENDANCE 
			        head.setReservationId(reservationId);
			        head.setUserId(staffUserId);
			        mapper.insertAttendance(head);
			    }

			    // ATTENDANCE_DETAIL 
			    for (AttendItemDTO dto : drafts) {
			        if (!reservationId.equals(dto.getReservationId())) continue;

			        dto.setAttendanceId(head.getAttendanceId());
			        mapper.insertAttendDetailByUser(dto);

			        // 노쇼면 매너온도 차감
			        if (dto.getAttendStatusId() != null && dto.getAttendStatusId() == 2L) {
			            Manner m = new Manner();
			            m.setUserId(dto.getUserId());
			            mapper.callInsertNoshow(m);
			        }			  
			    }
			}
			session.removeAttribute("attendDraft");
		} catch (Exception e) {
			log.info("finalizeAttendance : ", e);
			throw e;
		}
	}
	
	
	@Override
	public List<AttendanceListDTO> selectAttendListByRole(Map<String, Object> map, String role) {
	    if ("OWNER".equals(role)) {
	        return selectListByOwnerUserId(map);
	    } else {
	        return selectListByManagerUserId(map);
	    }
	}

	//draft 기준 done/partial 분류
	@Override
	public Map<String, List<Long>> checkStatus(HttpSession session, List<AttendanceListDTO> attendList) {
	   
		List<Long> doneList = new ArrayList<>();
	    List<Long> partialList = new ArrayList<>();

	    @SuppressWarnings("unchecked")
	    List<AttendItemDTO> draftList = (List<AttendItemDTO>) session.getAttribute("attendDraft");

	    if (draftList != null) {
	        // 중복 없는 reservationId 모으기
	        List<Long> resIds = new ArrayList<>();
	        for (AttendItemDTO d : draftList) {
	            if (!resIds.contains(d.getReservationId())) {
	                resIds.add(d.getReservationId());
	            }
	        }

	        // 예약별 draft 개수랑 TOTAL_MEMBER 비교
	        for (Long rid : resIds) {
	            int draftCount = 0;
	            for (AttendItemDTO d : draftList) {
	                if (rid.equals(d.getReservationId())) draftCount++;
	            }

	            int total = 0;
	            for (AttendanceListDTO a : attendList) {
	                if (rid.equals(a.getReservationId())) {
	                    total = a.getTotalMember();
	                    break;
	                }
	            }

	            if (draftCount >= total) {
	                doneList.add(rid);
	            } else {
	                partialList.add(rid);
	            }
	        }
	    }

	    Map<String, List<Long>> result = new HashMap<>();
	    result.put("done", doneList);
	    result.put("partial", partialList);

	    return result;
	}


	// 출석기록 목록 (역할별)
	@Override
	public List<AttendanceListDTO> selectHistoryByRole(Map<String, Object> map, String role) {

		List<AttendanceListDTO> list = null;

		try {
			if ("OWNER".equals(role)) {
				list = mapper.selectHistoryByOwnerUserId(map);
			} else {
				list = mapper.selectHistoryByManagerUserId(map);
			}
		} catch (Exception e) {
			log.info("selectHistoryByRole : ", e);
		}

		return list;
	}

	// 출석기록 갯수 (역할별)
	@Override
	public int dataCountHistoryByRole(Map<String, Object> map, String role) {

		int result = 0;

		try {
			if ("OWNER".equals(role)) {
				result = mapper.dataCountHistoryByOwnerUserId(map);
			} else {
				result = mapper.dataCountHistoryByManagerUserId(map);
			}
		} catch (Exception e) {
			log.info("dataCountHistoryByRole : ", e);
		}

		return result;
	}

}

package com.noexit.app.service;

import java.util.List;
import java.util.Map;

import com.noexit.app.model.AttendanceListDTO;
import com.noexit.app.model.AttendCrew;
import com.noexit.app.model.AttendForm;

import jakarta.servlet.http.HttpSession;

public interface AttendanceService {

	// 출석체크 목록 조회
	public List<AttendanceListDTO> selectListByOwnerUserId(Long ownerUserId); 
	// 매니저 출석체크 목록 조회
	public List<AttendanceListDTO> selectListByManagerUserId(Long managerUserId); 

	public List<AttendCrew> selectCrewByReservationId(Long reservationId);

	// 개별 출석체크 임시저장 (세션 누적용)
	public void saveDraft(AttendForm form, HttpSession session) throws Exception;

	// 최종확인용 draft 리스트를 ATTENDANCE + ATTENDANCE_DETAIL에 INSERT
	public void finalizeAttendance(HttpSession session, Long staffUserId) throws Exception;
	
	// 역할에 따른 출석 목록 조회
	public List<AttendanceListDTO> selectAttendListByRole(Long userId, String role);
	
	// draft 기준 done/partial 분류 
	public Map<String, List<Long>> checkStatus(HttpSession session, List<AttendanceListDTO> attendList);

	
	
}

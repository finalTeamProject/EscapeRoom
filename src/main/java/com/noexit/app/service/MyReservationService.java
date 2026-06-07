package com.noexit.app.service;

import java.util.List;
import java.util.Map;

import com.noexit.app.model.CancelMailDTO;
import com.noexit.app.model.MyReservationDTO;

public interface MyReservationService {

	// 예약 중 데이터 카운트 조회
	public int bookedCount(long userId);
	
	// 플레이 완료 데이터 카운트 조회 
	public int doneCount(long userId);
	
	// 예약 취소 데이터 카운트 조회
	public int canceledCount(long userId);
	
	// 예약 중인 목록 조회
	public List<MyReservationDTO> bookedList(Map<String, Object> map);
	
	// 플레이 완료된 목록 조회
	public List<MyReservationDTO> doneList(Map<String, Object> map);
	
	// 예약 취소된 목록 조회
	public List<MyReservationDTO> canceledList(Map<String, Object> map);
	
	// 사용자 예약 취소 프로시저
	public void cancelReservation(long reservationId, long userId) throws Exception;
	
	// 페이징 처리
	public Map<String, Object> getReservationPageData(long userId, int currentTab, int currentPage, int size);
	
	// 예약 취소 메일 보낼 파티원 목록 조회
	public List<CancelMailDTO> mailList(long reservationId);
	
}

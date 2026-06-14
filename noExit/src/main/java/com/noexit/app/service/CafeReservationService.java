package com.noexit.app.service;

import java.util.List;
import java.util.Map;

import com.noexit.app.model.CafeReservationDTO;

public interface CafeReservationService {
	
	// 예약 목록 조회
	public List<CafeReservationDTO> resList(Map<String, Object> map);
	
	// 예약 상세 조회
	public CafeReservationDTO resDetail(Long reservationId);
	
}

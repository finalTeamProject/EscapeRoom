package com.noexit.app.service;

import com.noexit.app.model.ReservationDTO;

public interface ReservationService {

	// 예약 등록 프로시저
	public void createReservation(Long userId, Long partyId) throws Exception;
	
	// 예약자 정보 조회
	public ReservationDTO findBooker(Long userId, Long partyId);
}

package com.noexit.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.noexit.app.model.ReservationDTO;

@Mapper
public interface ReservationMapper {

	// 예약 등록 프로시저
	public void createReservation(@Param("userId") Long userId, @Param("partyId") Long partyId) throws Exception;
	
	// 예약자 정보 조회
	public ReservationDTO findBooker(@Param("userId") Long userId, @Param("partyId") Long partyId);
}

package com.noexit.app.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.noexit.app.model.CafeReservationDTO;

@Mapper
public interface CafeReservationMapper {

	// 예약 목록 조회
	public List<CafeReservationDTO> resList(Map<String, Object> map);
	
	// 예약 상세 조회
	public List<CafeReservationDTO> resDetail(long reservationId);
	
	// 예약 취소 프로시저
	public void resDelete(long reservationId) throws Exception;
	
	
}

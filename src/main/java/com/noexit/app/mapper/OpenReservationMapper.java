package com.noexit.app.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.noexit.app.model.OpenReservationDTO;

@Mapper
public interface OpenReservationMapper {

	// 카페 목록 조회 
	public List<OpenReservationDTO> getCafeList (Long userId);
	
	// 테마 목록 조회
	public List<OpenReservationDTO> getThemeList (Long cafeId);
	
	// 등록된 예약 오픈 목록 조회
	public List<OpenReservationDTO> getOpenReservationList (Map<String, Object> map);
	
	// 예약 오픈 등록 프로시저
	public void openReservation(Map<String, Object> map) throws Exception;
	
	// 예약 비활성화 등록 프로시저
	public void dropOpen(@Param("userId")Long userId, @Param("resOpenId") Long resOpenId) throws Exception;
	
	
}

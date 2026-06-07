package com.noexit.app.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.noexit.app.model.MyReservationDTO;

@Mapper
public interface MyReservationMapper {

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
}

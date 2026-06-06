package com.noexit.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.noexit.app.mapper.MyReservationMapper;
import com.noexit.app.model.MyReservationDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyReservationServiceImpl implements MyReservationService {

	// 주요 속성 구성
	private final MyReservationMapper mapper;

	@Override
	public int bookedCount(long userId) {

		int result = 0;	
		
		try {
			result = mapper.bookedCount(userId);
			
		} catch (Exception e) {
			log.info("bookedCount: ", e);
			throw e;
		}
		return result;
	}


	@Override
	public int doneCount(long userId) {
		int result = 0;	
		
		try {
			result = mapper.doneCount(userId);
			
		} catch (Exception e) {
			log.info("doneCount: ", e);
			throw e;
		}
		return result;
	}

	@Override
	public int canceledCount(long userId) {
		int result = 0;	
		
		try {
			result = mapper.canceledCount(userId);
			
		} catch (Exception e) {
			log.info("canceledCount: ", e);
			throw e;
		}
		return result;
	}
	
	
	
	@Override
	public List<MyReservationDTO> bookedList(Map<String, Object> map) {
		
		List<MyReservationDTO> list = new ArrayList<>();
		
		try {
			list = mapper.bookedList(map);
		} catch (Exception e) {
			log.error("bookedList: ", e);
		}
		
		return list;
	}

	@Override
	public List<MyReservationDTO> doneList(Map<String, Object> map) {
		List<MyReservationDTO> list = new ArrayList<>();
		
		try {
			list = mapper.doneList(map);
		} catch (Exception e) {
			log.error("doneList: ", e);
		}
		
		return list;
	}

	@Override
	public List<MyReservationDTO> canceledList(Map<String, Object> map) {
		List<MyReservationDTO> list = new ArrayList<>();
		
		try {
			list = mapper.canceledList(map);
		} catch (Exception e) {
			log.error("canceledList: ", e);
		}
		
		return list;
	}

	
	
}

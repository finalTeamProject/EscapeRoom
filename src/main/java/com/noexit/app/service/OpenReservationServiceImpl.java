package com.noexit.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.noexit.app.mapper.OpenReservationMapper;
import com.noexit.app.model.OpenReservationDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpenReservationServiceImpl implements OpenReservationService {
	
	private final OpenReservationMapper mapper;
	
	
	// 카페 목록 가져오기
	@Override
	public List<OpenReservationDTO> getCafeList(Long userId) {

		List<OpenReservationDTO> result = new ArrayList<>();
		
		try {
			result = mapper.getCafeList(userId);
		} catch (Exception e) {
			log.error("getCafeList: ",e);
		}
		
		return result;
	}
	

	@Override
	public List<OpenReservationDTO> getThemeList(Long cafeId) {
		
		List<OpenReservationDTO> result = new ArrayList<>();
		
		try {
		
			result = mapper.getThemeList(cafeId);
			
		} catch (Exception e) {
			log.error("getThemeList: ",e);
		}
		
		return result;
	}


	@Override
	public List<OpenReservationDTO> getOpenReservationList(Map<String, Object> map) {
		
		List<OpenReservationDTO> result = new ArrayList<>();
		
		try {
			
			result = mapper.getOpenReservationList(map);
			
		} catch (Exception e) {
			log.error("getOpenReservationList: ", e);
		}
		
		return result;
	}

	@Override
	public void openReservation(Map<String, Object> map) throws Exception {
			
		try {
			
			mapper.openReservation(map);
			
			
		} catch (Exception e) {
			log.error("openReservation: ", e);
			throw e;
		}
	}

	@Override
	public void dropOpen(Long userId, Long resOpenId) throws Exception {
		try {
						
			mapper.dropOpen(userId, resOpenId);
			
		} catch (Exception e) {
			log.error("dropOpen: ", e);
			throw e;
		}
	}

	
}































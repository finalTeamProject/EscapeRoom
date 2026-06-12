package com.noexit.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.noexit.app.mapper.CafeReservationMapper;
import com.noexit.app.model.CafeReservationDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CafeReservatopmServiceImpl implements CafeReservationService{
	
	private final CafeReservationMapper mapper;
	
	

	@Override
	public List<CafeReservationDTO> resList(Map<String, Object> map) {
		
		List<CafeReservationDTO> result = new ArrayList<>();
		
		try {
			
			// offset, limit 기본값 세팅 확인
		    if (map.get("offset") == null) map.put("offset", 0);
		    if (map.get("limit") == null) map.put("limit", 10);
		 
			
			result = mapper.resList(map);
			
		} catch (Exception e) {
			log.error("resList: ",e);
		}
		return result;
	}
	
	
	@Override
	public CafeReservationDTO resDetail(Long reservationId) {
		
		CafeReservationDTO result = new CafeReservationDTO();
		
		try {
			
			result = mapper.resDetail(reservationId);
			
		} catch (Exception e) {
			log.error("resDetail: ",e);
		}
		return result;
	}
	

	
}

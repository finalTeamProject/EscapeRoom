package com.noexit.app.service;

import org.springframework.stereotype.Service;

import com.noexit.app.mapper.ReservationMapper;
import com.noexit.app.model.ReservationDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

	private final ReservationMapper mapper;
	
	@Override
	public void createReservation(Long userId, Long partyId) throws Exception {
		try {
			
			mapper.createReservation(userId, partyId);
		} catch (Exception e) {
			log.error("createReservation: ",e );
			throw e;
		}
		
	}

	@Override
	public ReservationDTO findBooker(Long userId, Long partyId) {
		
		ReservationDTO result = new ReservationDTO();
		try {
			
			result = mapper.findBooker(userId, partyId);
			
		} catch (Exception e) {
			log.error("findBooker: ", e);
		}
		return result;
	}

	
	
}

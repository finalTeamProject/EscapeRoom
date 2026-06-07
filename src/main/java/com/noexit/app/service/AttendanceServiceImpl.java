package com.noexit.app.service;

import org.springframework.stereotype.Service;

import com.noexit.app.mapper.AttendanceMapper;
import com.noexit.app.model.Manner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceServiceImpl implements AttendanceService {

	private final AttendanceMapper mapper;

	@Override
	public Manner noshow(Long userId) throws Exception {

		Manner manner = new Manner();
		manner.setUserId(userId);

		try {
			mapper.insertNoshow(manner);
		} catch (Exception e) {
			log.info("noshow : ", e);
			throw e;
		}

		return manner;
	}
}

package com.noexit.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.noexit.app.mapper.ManagerMapper;
import com.noexit.app.model.Manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerServiceImpl implements ManagerService {

	private final ManagerMapper mapper;

	// 매니저 리스트 조회 (페이징 포함)
	@Override
	public List<Manager> selectActiveByOwnerUserId(Map<String, Object> map) {
		List<Manager> list = null;
		try {
			list = mapper.selectActiveByOwnerUserId(map);
		} catch (Exception e) {
			log.info("selectActiveByOwnerUserId : ", e);
		}
		return list;
	}

	// 매니저 갯수 확인
	@Override
	public int dataCount(Map<String, Object> map) {
		int result = 0;
		try {
			result = mapper.dataCount(map);
		} catch (Exception e) {
			log.info("dataCount : ", e);
		}
		return result;
	}

	@Override
	public void enroll(Manager manager) throws Exception {
		try {
			mapper.insertEnroll(manager);
		} catch (Exception e) {
			log.info("enroll : ", e);
			throw e;
		}
	}

	@Override
	public void deact(Manager manager) throws Exception {
		try {
			mapper.insertDeact(manager);
		} catch (Exception e) {
			log.info("deact : ", e);
			throw e;
		}
	}

	@Override
	public int countActiveByUserId(Long userId) {
		int count = 0;
		try {
			count = mapper.countActiveByUserId(userId);
		} catch (Exception e) {
			log.info("countActiveByUserId : ", e);
		}
		return count;
	}
}

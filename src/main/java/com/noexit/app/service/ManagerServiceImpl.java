package com.noexit.app.service;

import java.util.List;

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

	@Override
	public List<Manager> selectActiveByOwnerUserId(Long ownerUserId) {
		List<Manager> list = null;
		try {
			list = mapper.selectActiveByOwnerUserId(ownerUserId);
		} catch (Exception e) {
			log.info("selectActiveByOwnerUserId : ", e);
		}
		return list;
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

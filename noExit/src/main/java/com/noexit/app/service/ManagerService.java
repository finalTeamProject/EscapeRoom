package com.noexit.app.service;

import java.util.List;
import java.util.Map;

import com.noexit.app.model.Manager;

public interface ManagerService {

	public List<Manager> selectActiveByOwnerUserId(Map<String, Object> map);
	public void enroll(Manager manager) throws Exception;
	public void deact(Manager manager) throws Exception;
	public int countActiveByUserId(Long userId);
	public int dataCount(Map<String, Object> map);
}

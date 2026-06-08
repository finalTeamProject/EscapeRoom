package com.noexit.app.service;

import java.util.List;

import com.noexit.app.model.Manager;

public interface ManagerService {

	public List<Manager> selectActiveByOwnerUserId(Long ownerUserId);
	public void enroll(Manager manager) throws Exception;
	public void deact(Manager manager) throws Exception;
	public int countActiveByUserId(Long userId);
}

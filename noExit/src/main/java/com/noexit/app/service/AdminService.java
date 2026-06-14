package com.noexit.app.service;

import java.util.List;

import com.noexit.app.model.Admin;
import com.noexit.app.model.AdminDashboard;
import com.noexit.app.model.Cafe;

public interface AdminService {

	public Admin login(Admin admin);
	public AdminDashboard getDashboardStats();	// 관리자 메인 대시보드 데이터 조회
	public List<Cafe> getCafeList();
}

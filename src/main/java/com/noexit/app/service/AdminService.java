package com.noexit.app.service;

import com.noexit.app.model.Admin;
import com.noexit.app.model.AdminDashboard;

public interface AdminService {

	public Admin login(Admin admin);
	public AdminDashboard getDashboardStats();	// 관리자 메인 대시보드 데이터 조회
}

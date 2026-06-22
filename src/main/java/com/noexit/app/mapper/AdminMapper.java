package com.noexit.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.noexit.app.model.Admin;
import com.noexit.app.model.AdminDashboard;
import com.noexit.app.model.Cafe;

@Mapper
public interface AdminMapper {
	
	public Admin findByLoginId(String loginId);
	public AdminDashboard getDashboardStats();	// 관리자 메인 화면
	public List<Cafe> getCafeList();			// 관리자 카페 리스트 조회
	public int hideCafe(Cafe cafe);
}

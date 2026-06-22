package com.noexit.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.noexit.app.mapper.AdminMapper;
import com.noexit.app.model.Admin;
import com.noexit.app.model.AdminDashboard;
import com.noexit.app.model.Cafe;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

	private final AdminMapper mapper;

	@Override
	public Admin login(Admin admin) {
		Admin result = null;
		try {
			Admin dto = mapper.findByLoginId(admin.getLoginId());
			if (dto != null && dto.getPassword().equals(admin.getPassword())) {
				result = dto;
			}
		} catch (Exception e) {
			log.info("login : ", e);
		}
		return result;
	}

	
	// 관리자 메인 대시보드 데이터 바인딩 메소드
	@Override
	public AdminDashboard getDashboardStats() {
	    
		return mapper.getDashboardStats();
	}
	
	// 카페 리스트 바인딩 메소드
	@Override
    public List<Cafe> getCafeList() {
		
        return mapper.getCafeList();
    }
	
	
	@Override
	public int hideCafe(Cafe cafe) {

		return mapper.hideCafe(cafe);
	}
	
	
}

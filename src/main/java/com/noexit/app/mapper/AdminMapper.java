package com.noexit.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.noexit.app.model.Admin;

@Mapper
public interface AdminMapper {
	
	public Admin findByLoginId(String loginId);

}

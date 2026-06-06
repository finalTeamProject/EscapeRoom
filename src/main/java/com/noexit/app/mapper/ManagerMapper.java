package com.noexit.app.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.noexit.app.model.Manager;

@Mapper
public interface ManagerMapper {

	public List<Manager> selectActiveByOwnerUserId(Long ownerUserId);
	public void insertEnroll(Manager manager) throws SQLException;
	public void insertDeact(Manager manager) throws SQLException;
	public int countActiveByUserId(Long userId);
}

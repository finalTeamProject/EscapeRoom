package com.noexit.app.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.noexit.app.model.Manner;

@Mapper
public interface AttendanceMapper {

	public void insertNoshow(Manner manner) throws SQLException;
}

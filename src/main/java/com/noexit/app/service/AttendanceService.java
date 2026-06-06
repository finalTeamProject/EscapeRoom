package com.noexit.app.service;

import com.noexit.app.model.Manner;

public interface AttendanceService {

	public Manner noshow(Long userId) throws Exception;
}

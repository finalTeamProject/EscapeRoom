package com.noexit.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noexit.app.mapper.AttendanceMapper;
import com.noexit.app.model.AttendanceListDTO;
import com.noexit.app.model.AttendCrew;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceServiceImpl implements AttendanceService {

	private final AttendanceMapper mapper;


	@Override
	public List<AttendanceListDTO> selectListByOwnerUserId(Long ownerUserId) {

		List<AttendanceListDTO> list = null;

		try {
			list = mapper.selectListByOwnerUserId(ownerUserId);
		} catch (Exception e) {
			log.info("selectListByOwnerUserId : ", e);
		}

		return list;
	}

	@Override
	public List<AttendanceListDTO> selectListByManagerUserId(Long managerUserId) {

		List<AttendanceListDTO> list = null;

		try {
			list = mapper.selectListByManagerUserId(managerUserId);
		} catch (Exception e) {
			log.info("selectListByManagerUserId : ", e);
		}

		return list;
	}

	@Override
	public List<AttendCrew> selectCrewByReservationId(Long reservationId) {

		List<AttendCrew> list = null;

		try {
			list = mapper.selectCrewByReservationId(reservationId);
		} catch (Exception e) {
			log.info("selectCrewByReservationId : ", e);
		}

		return list;
	}

	@Override
	@Transactional
	public void attendAll(List<AttendanceListDTO> list, Long staffUserId) throws Exception {

		try {
			AttendanceListDTO head = null;
			for (AttendanceListDTO dto : list) {
				if (head == null || !head.getReservationId().equals(dto.getReservationId())) {
					head = new AttendanceListDTO();
					head.setReservationId(dto.getReservationId());
					head.setUserId(staffUserId);
					mapper.insertAttendance(head);
				}
				AttendanceListDTO det = new AttendanceListDTO();
				det.setAttendanceId(head.getAttendanceId());
				det.setLeaderId(dto.getUserId());
				det.setAttendStatusId(dto.getAttendStatusId());
				mapper.insertAttendDetail(det);
			}
		} catch (Exception e) {
			log.info("attendAll : ", e);
			throw e;
		}
	}

}

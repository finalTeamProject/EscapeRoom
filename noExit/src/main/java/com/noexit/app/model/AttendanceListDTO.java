package com.noexit.app.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AttendanceListDTO {

	private Long   reservationId;
	private Date   openAt;
	private Long   cafeId;
	private String cafeName;
	private Long   roomId;
	private String roomName;
	private Long   leaderId;
	private String leaderNickname;
	private int    totalMember;
	private String statusName;
	private Long   attendanceId;
	private Long   userId;
	private Long   attendStatusId;

	// 출석기록 페이지용 카운트
	private int    doneCount;
	private int    noshowCount;
}

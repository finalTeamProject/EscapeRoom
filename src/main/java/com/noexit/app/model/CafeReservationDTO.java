package com.noexit.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CafeReservationDTO {
	private Long reservationId;
	private Long cafeId;
	private Long roomId;
	private String cafeName;
	private String roomName;
	private String openAt;
	private Long bookerId;
	private String bookerName;
	private String bookerTel;
	private	int totalMember;

}

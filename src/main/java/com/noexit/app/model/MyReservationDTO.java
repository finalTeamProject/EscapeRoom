package com.noexit.app.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyReservationDTO {
	private long reservationId;
	private long cafeId;
	private String cafeName;
	private long roomId;
	private String roomName;
	private int totalMember;
	private String partyRole;
	private String resStatus;
	private long resOpenId;
	private String openAt;
	private String bookedAt;
	private String attendStatus;
	private String cancelType;
	private String canceledAt;
}

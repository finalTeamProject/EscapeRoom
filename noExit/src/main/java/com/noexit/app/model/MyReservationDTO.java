package com.noexit.app.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyReservationDTO {
	private Long reservationId;
	private Long cafeId;
	private String cafeName;
	private String cafeTel;
	private Long roomId;
	private String roomName;
	private int totalMember;
	private Long partyId;
	private String partyName;
	private String partyRole;
	private String resStatus;
	private Long resOpenId;
	private String openAt;
	private String bookedAt;
	private String attendStatus;
	private String cancelType;
	private String canceledAt;
}

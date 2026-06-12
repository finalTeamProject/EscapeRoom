package com.noexit.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ReservationDTO {

	private Long partyId;
	private String cafeName;
	private String roomName;
	private String openAt;
	private Long bookerId;
	private String bookerName;
	private String bookerTel;
	private String roomImg;
	private int price;
	private int totalMember;
	
}

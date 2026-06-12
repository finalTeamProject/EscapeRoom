package com.noexit.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OpenReservationDTO {
	private Long cafeId;
	private String cafeName;
	private Long roomId;
	private String roomName;
	private Long ownerId;
	private Long managerId;
	private String openAt;
	private String openDate;
	private String openTime;
	private Long resOpenId;
}

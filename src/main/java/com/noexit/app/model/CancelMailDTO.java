package com.noexit.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CancelMailDTO {

	private long partyId;
	private String partyName;
	private long cafeId;
	private String cafeName;
	private long roomId;
	private String roomName;
	private long resOpenId;
	private String openAt;
	private long userId;
	private String nickname;
	private String name;
	private String email;
}

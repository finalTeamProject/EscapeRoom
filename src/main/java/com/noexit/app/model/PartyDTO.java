package com.noexit.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartyDTO
{
	private long partyId;
	private String partyStatus;
	private String partyName;
	private String cafeName;
	private String themeName;
	private String date;
	private String time;
	private int minPlayers;
	private int maxPlayers;
	private int price;
	private String slotStatus;
	private int genderCondition;
	private String partyComment;
}

package com.noexit.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartyCrewDTO
{
	private long crewId;
	private String name;
	private int age;
	private String gender;
	private int temp;
	private boolean ready;
	private String position;
}

package com.noexit.app.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ThemeReviewDTO
{
	private String name;
	private int satisfaction;
	private int difficulty;
	private int horror;
	private int activity;
	private int immersion;
	private String comment;
}

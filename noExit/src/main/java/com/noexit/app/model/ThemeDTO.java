package com.noexit.app.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThemeDTO
{
	private long themeId;
	private String imagePath;
	private List<MultipartFile> themeImageFile;
	
	private String cafeId;
	private String cafeName;
	private String cafeLocation;
	private String cafePhone;
	
	private String themeName;
	private String genre;
	private int duration;
	private int difficulty;
	private int horror;
	private int activity;
	private int price;
	private int minPlayers;
	private int maxPlayers;
	private int adult;
	private String description;
	private String createAt;
}

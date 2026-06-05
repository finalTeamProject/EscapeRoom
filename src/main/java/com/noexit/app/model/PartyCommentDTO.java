package com.noexit.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartyCommentDTO
{
	private long commentId;
	private String name;
	private String comment;
	private boolean delete;
}

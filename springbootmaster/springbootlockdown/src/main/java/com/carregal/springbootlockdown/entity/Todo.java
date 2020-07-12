package com.carregal.springbootlockdown.entity;

import java.util.Date;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Todo {

	private int id;
	private String user;
	@Size(min = 10, message = "A little too short description")
	private String desc;
	private Date targetDate;
	private boolean done;

	public Todo(int id, String user, String desc, Date targetDate, boolean isDone) {
		super();
		this.id = id;
		this.user = user;
		this.desc = desc;
		this.targetDate = targetDate;
		this.done = isDone;
	}

	public Todo() {
		super();
	} // TODO Auto-generated constructor stub }

}

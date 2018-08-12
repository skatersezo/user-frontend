 package com.iespinosa.userFront.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Appointment {
	
	// Appointment properties
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Date date;
	private String location;
	private String description;
	private boolean confirmed;
	// Appointment relationships
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	// Getters and setters
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Appointment [id=" + id + ", date=" + date + ", location=" + location + ", description=" + description
				+ ", confirmed=" + confirmed + ", user=" + user + "]";
	}
	
	

	
}

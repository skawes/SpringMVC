package com.awes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Points {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int points_id;
	@Column(unique = true)
	private String userId;

	private String match1;
	private String match2;
	private float points;
	public int getPoints_id() {
		return points_id;
	}
	public void setPoints_id(int points_id) {
		this.points_id = points_id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "Points [points_id=" + points_id + ", userId=" + userId + ", match1=" + match1 + ", match2=" + match2
				+ ", points=" + points + "]";
	}
	public String getMatch1() {
		return match1;
	}
	public void setMatch1(String match1) {
		this.match1 = match1;
	}
	public String getMatch2() {
		return match2;
	}
	public void setMatch2(String match2) {
		this.match2 = match2;
	}
	public float getPoints() {
		return points;
	}
	public void setPoints(float points) {
		this.points = points;
	}

}

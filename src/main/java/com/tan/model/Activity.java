package com.tan.model;

import java.util.Date;

public class Activity {
	
	    private int id;
	    private int publisherUserId;
	    private Date timeStart;
	    private Date timeEnd;
	    private int deposit;
	    private int totalAmount;
	    private String theme;
	    private String content;
	    private int activityType;
	    private int activityStatus;
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getPublisherUserId() {
			return publisherUserId;
		}
		public void setPublisherUserId(int publisherUserId) {
			this.publisherUserId = publisherUserId;
		}
		public Date getTimeStart() {
			return timeStart;
		}
		public void setTimeStart(Date timeStart) {
			this.timeStart = timeStart;
		}
		public Date getTimeEnd() {
			return timeEnd;
		}
		public void setTimeEnd(Date timeEnd) {
			this.timeEnd = timeEnd;
		}
		public int getDeposit() {
			return deposit;
		}
		public void setDeposit(int deposit) {
			this.deposit = deposit;
		}
		public int getTotalAmount() {
			return totalAmount;
		}
		public void setTotalAmount(int totalAmount) {
			this.totalAmount = totalAmount;
		}
		public String getTheme() {
			return theme;
		}
		public void setTheme(String theme) {
			this.theme = theme;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public int getActivityType() {
			return activityType;
		}
		public void setActivityType(int activityType) {
			this.activityType = activityType;
		}
		public int getActivityStatus() {
			return activityStatus;
		}
		public void setActivityStatus(int activityStatus) {
			this.activityStatus = activityStatus;
		}
	    
	    
		

}

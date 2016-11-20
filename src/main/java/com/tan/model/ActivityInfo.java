package com.tan.model;

import java.util.Date;

public class ActivityInfo {
	
	
	/*CREATE TABLE hy_activityInfo (  
			   activityInfo_id INT NOT NULL AUTO_INCREMENT,
			   activityInfo_activityId INT,
			   activityInfo_publisherId INT,
			   activityInfo_applicantsId INT,
			   activityInfo_deposit INT,
			   activityInfo_totalAmount INT,
			   activityInfo_activityInfostatus INT,
			   PRIMARY KEY  (`activityInfo_id`)
			  );*/


	  
	private int id;
	private int activityId;
    private int publisherId;
    private int applicantsId;   
    private int deposit;
    private int totalAmount;
    private int activityInfostatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public int getApplicantsId() {
		return applicantsId;
	}
	public void setApplicantsId(int applicantsId) {
		this.applicantsId = applicantsId;
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
	public int getActivityInfostatus() {
		return activityInfostatus;
	}
	public void setActivityInfostatus(int activityInfostatus) {
		this.activityInfostatus = activityInfostatus;
	}
    
    
	

}

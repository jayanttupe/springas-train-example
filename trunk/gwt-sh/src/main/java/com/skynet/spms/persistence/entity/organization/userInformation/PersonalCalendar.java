package com.skynet.spms.persistence.entity.organization.userInformation;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

import com.skynet.spms.client.entity.EventInfo;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;


@Entity
@Indexed
@Table(name = "SPMS_USER_CALENDAR")
public class PersonalCalendar extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1881054791290253386L;

	private String description;
	
	private String  event;
	
	private Date startDate;
	
	private Date endDate;
	
	private User user;
	
	public PersonalCalendar(){
	
	}

	public void updateByEvent(EventInfo info){
		this.description=info.getContext();
		this.event=info.getTitle();
		this.endDate=info.getDateEnd();
		this.startDate=info.getDateStart();
	}
	
	@Field(index=Index.TOKENIZED, store=Store.NO)
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Field(index=Index.TOKENIZED, store=Store.NO)
	@Column(name="EVENT_NAME")
	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
	
	@Field(index=Index.UN_TOKENIZED, store=Store.YES)
	@DateBridge(resolution = Resolution.DAY)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EVENT_START_DATE")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Field(index=Index.UN_TOKENIZED, store=Store.YES)
	@DateBridge(resolution = Resolution.DAY)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EVENT_END_DATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@ManyToOne
    @JoinColumn(name="USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Transient
	public EventInfo getEventInfo(){
		EventInfo info=new EventInfo();
		info.setContext(description);
		info.setDateEnd(endDate);
		info.setDateStart(startDate);
		info.setTitle(event);
		info.setId(super.getId());
		return info;
	}
}

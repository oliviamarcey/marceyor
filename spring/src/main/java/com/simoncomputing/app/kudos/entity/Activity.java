package com.simoncomputing.app.kudos.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "ACTIVITY")
public class Activity {

	@Id
	@SequenceGenerator(name = "activitySequence", sequenceName = "ACTIVITY_ID_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "activitySequence", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Long id;

	@Column(name = "KUDOS_ID")
	private Long kudosId;
		
	@Column(name = "FROM_USER_ID")
	private Long fromUserId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FROM_USER_ID", insertable = false, updatable = false)
	private User fromUser;
	
	@Column(name = "TO_USER_ID")
	private Long toUserId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TO_USER_ID", insertable = false, updatable = false)
	private User toUser;
	
	@Column(name = "TYPE", length = 50)
	private String type;
	
	@Column(name = "COMMENT", length = 50)
	private String comment;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENTRY_DTTM", insertable = false, updatable = false)
	private Date entryDateTime;
	
	public Activity() {
		// happy
	}
	
	public Activity(Long id, Long kudosId, String type, Long fromUserId, User fromUser, Long toUserId, User toUser,
			String comment, Date entryDateTime) {
		super();
		this.id = id;
		this.kudosId = kudosId;
		this.type = type;
		this.fromUserId = fromUserId;
		this.fromUser = fromUser;
		this.toUserId = toUserId;
		this.toUser = toUser;
		this.comment = comment;
		this.entryDateTime = entryDateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getKudosId() {
		return kudosId;
	}

	public void setKudosId(Long kudosId) {
		this.kudosId = kudosId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public Long getToUserId() {
		return toUserId;
	}

	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Date getEntryDateTime() {
		return entryDateTime;
	}

	public void setEntryDateTime(Date entryDateTime) {
		this.entryDateTime = entryDateTime;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Activity) {
			Activity k = (Activity) obj;
			
			boolean ret = 
					k.getId() == this.getId() &&
					k.getKudosId() == this.getKudosId() &&
					k.getFromUserId() == this.getFromUserId() &&
					k.getToUserId() == this.getToUserId() &&
					k.getType().equals(this.getType()) &&
					k.getComment().equals(this.getComment());
			
			return ret;
		} else {
			return false;
		}
	}
	
}
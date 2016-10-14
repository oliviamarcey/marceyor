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
@Table(name = "KUDOS")
public class Kudos {

	@Id
	@SequenceGenerator(name = "kudosSequence", sequenceName = "KUDOS_ID_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "kudosSequence", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Long id;

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

	@Column(name = "AMOUNT", precision = 2, scale = 0)
	private Integer amount;

	@Column(name = "MESSAGE", length = 500)
	private String message;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENTRY_DTTM", insertable = false, updatable = false)
	private Date entryDateTime;
	
	public Kudos() {
		// happy
	}
	
	public Kudos(Long id, Long fromUserId, User fromUser, Long toUserId, User toUser, Integer amount, String message, Date entryDateTime) {
		super();
		this.id = id;
		this.fromUserId = fromUserId;
		this.fromUser = fromUser;
		this.toUserId = toUserId;
		this.toUser = toUser;
		this.amount = amount;
		this.message = message;
		this.entryDateTime = entryDateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Date getEntryDateTime() {
		return entryDateTime;
	}

	public void setEntryDateTime(Date entryDateTime) {
		this.entryDateTime = entryDateTime;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Kudos) {
			Kudos k = (Kudos) obj;
			
			boolean ret = 
					k.getId() == this.getId() &&
					k.getFromUserId() == this.getFromUserId() &&
					k.getToUserId() == this.getToUserId() &&
					k.getAmount() == this.getAmount() &&
					k.getMessage().equals(this.getMessage());
			return ret;
		} else {
			return false;
		}
	}
	
}

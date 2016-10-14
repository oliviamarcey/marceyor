package com.simoncomputing.app.kudos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@SequenceGenerator(name = "userSequence", sequenceName = "USER_ID_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "userSequence", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Long id;
	
	@Column(name = "USER_NAME", unique = true, length = 50)
	private String username;
	
	@Column(name = "PASSWORD", length = 50)
	private String password;
	
	@Column(name = "FIRST_NAME", length = 50)
	private String first;
	
	@Column(name = "LAST_NAME", length = 50)
	private String last;
	
	public User() {
		// happy
	}
	
	public User(Long id, String username, String password, String first, String last) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.first = first;
		this.last = last;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirst() {
		return first;
	}
	
	public void setFirst(String first) {
		this.first = first;
	}
	
	public String getLast() {
		return last;
	}
	
	public void setLast(String last) {
		this.last = last;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof User) {
			User u = (User) obj;
			
			boolean ret = 
					u.getId() == this.getId() &&
					u.getFirst().equals(this.getFirst()) &&
					u.getLast().equals(this.getLast()) &&
					u.getUsername().equals(this.getUsername()) && 
					u.getPassword().equals(this.getPassword());
			
			return ret;
		} else {
			return false;
		}
	}
}

package com.excilys.computerdatabase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "log4j")
public class Log4j{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	@Column( name = "priority" )
	String priority;
	
	@Column( name = "category" )
	String category;
	
	@Column( name = "thread" )
	String thread;
	
	@Column( name = "message" )
	String message;
	
	@Column( name = "layout_msg" )
	String layout_msg;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column( name = "timestamp" )
	DateTime  timestamp;
	
	@Column( name = "addon" )
	String addon;
}

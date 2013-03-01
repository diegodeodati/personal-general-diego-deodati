package it.bplus.model;

// Generated 31-mar-2011 10.51.22 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;

/**
 * Tempodim generated by hbm2java
 */
public class Tempodim implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal id;
	private Date data;
	private Short hour;
	private Short day;
	private Short week;
	private Short tendays;
	private Short fifteendays;
	private Short month;
	private Short sixmonth;
	private Short year;
	private Boolean holiday;

	public Tempodim() {
	}

	public Tempodim(BigDecimal id) {
		this.id = id;
	}

	public Tempodim(BigDecimal id, Date data, Short hour, Short day, Short week,
			Short tendays, Short fifteendays, Short month, Short sixmonth,
			Short year, Boolean holiday) {
		this.id = id;
		this.data = data;
		this.hour = hour;
		this.day = day;
		this.week = week;
		this.tendays = tendays;
		this.fifteendays = fifteendays;
		this.month = month;
		this.sixmonth = sixmonth;
		this.year = year;
		this.holiday = holiday;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Short getHour() {
		return hour;
	}

	public void setHour(Short hour) {
		this.hour = hour;
	}

	public Short getDay() {
		return day;
	}

	public void setDay(Short day) {
		this.day = day;
	}

	public Short getWeek() {
		return week;
	}

	public void setWeek(Short week) {
		this.week = week;
	}

	public Short getTendays() {
		return tendays;
	}

	public void setTendays(Short tendays) {
		this.tendays = tendays;
	}

	public Short getFifteendays() {
		return fifteendays;
	}

	public void setFifteendays(Short fifteendays) {
		this.fifteendays = fifteendays;
	}

	public Short getMonth() {
		return month;
	}

	public void setMonth(Short month) {
		this.month = month;
	}

	public Short getSixmonth() {
		return sixmonth;
	}

	public void setSixmonth(Short sixmonth) {
		this.sixmonth = sixmonth;
	}

	public Short getYear() {
		return year;
	}

	public void setYear(Short year) {
		this.year = year;
	}

	public Boolean getHoliday() {
		return holiday;
	}

	public void setHoliday(Boolean holiday) {
		this.holiday = holiday;
	}


}
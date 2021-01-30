package com.example.demo.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;

@Data
public class CheckAptAvailabilityReq {
	public int doctorId;
	@Enumerated(value = EnumType.STRING)
	private AppointmentTimeSlots aptTimeSlot;
	

}

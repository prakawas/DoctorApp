package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;

import org.springframework.web.bind.annotation.RequestBody;

import lombok.Data;

@Data
public class ChangAppointmentStatusReq {

	public  int appointmentId;
	public  Checked status;
}

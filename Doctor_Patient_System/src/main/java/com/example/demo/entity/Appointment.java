package com.example.demo.entity;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Entity
@Data
public class Appointment {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int appointmentId;
private int  doctorId;
private int patientId;
private Date date;
private String times;
@Enumerated(value = EnumType.STRING)
private Checked checkedStatus;
@Enumerated(value = EnumType.STRING)
private AppointmentTimeSlots aptTimeSlot;

}

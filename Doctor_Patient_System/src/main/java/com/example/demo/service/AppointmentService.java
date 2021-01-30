package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;
import com.example.demo.repository.AppointmentRepository;
@Service
public class AppointmentService {

@Autowired
AppointmentRepository appointmentRepository;
	
	
	 public List<Appointment> findAll()
	   {
		   return appointmentRepository.findAll();	  
		   
	   }
	 
	 
	  public void SaveALL(Appointment appointment)
	   {
		  appointmentRepository.save(appointment);
	   }
	
	
}

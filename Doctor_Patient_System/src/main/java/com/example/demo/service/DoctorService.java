package com.example.demo.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DoctorRepository;

@Service
public class DoctorService {
	
    @Autowired
	DoctorRepository doctorRepository;
    @Autowired
   AppointmentRepository appointmentRepository;
	
   public void SaveALL(Doctor doctor)
   {
   	 doctorRepository.save(doctor);
   }
   public List<Doctor> findAll()
   {
	   return doctorRepository.findAllByOrderByFirstNameAsc();
   }
   public Doctor findById(int theId)
   {
	   Optional<Doctor> result=doctorRepository.findById(theId);
		Doctor thedoctor=null;
	  
	   if(result.isPresent())
	   {
		   thedoctor=result.get();
	   }
	   else
	   {
		   throw new RuntimeException("Did Not find Employee ID");
	   }
	return  thedoctor;
   }
   
   public void Delete(int theId)
   {
   	doctorRepository.deleteById(theId);
   }
   
public Doctor findByEmail(String email) {
	Doctor doctor = null;
	
	try {
		System.out.println("email before"+email);

		doctor = doctorRepository.findByDoctorEmail(email);
		
		System.out.println("email after"+email);
		
	} catch (Exception e) {
		
		e.printStackTrace();
	}

	return doctor;

}

public List<Appointment> listAppoint(int doctorId)
{
	
	return appointmentRepository.findListOfTodayAppointment(doctorId);
	
	
}

}

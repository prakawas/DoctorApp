 package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Checked;
import com.example.demo.entity.Patient;

public interface PatientRepository  extends JpaRepository<Patient, Integer>{

	List<Patient> findAllByOrderByFirstNameAsc();

	@Query(value = "SELECT patient_id, email,password, first_name,last_name,date_of_birth, gender, phone_number from patient p WHERE p.email =?1",nativeQuery = true)
	    Patient findByPatientEmail(String email);
	
}

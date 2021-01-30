package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	List<Doctor> findAllByOrderByFirstNameAsc();
	@Query(value = "SELECT patient_id, email,password, first_name,last_name,date_of_birth, gender, phone_number from patient p WHERE p.email =?1",nativeQuery = true)
	Doctor findByDoctorEmail(String email);
	
	
	
}

package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Checked;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;
@Service
public class PatientService {
	
	
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	AppointmentRepository appointmentRepository;
	@Autowired
	DoctorRepository doctorRepository;

	public void SaveALL(Patient patient) {
		patientRepository.saveAndFlush(patient);
	}

	public List<Patient> findAll() {
		return patientRepository.findAllByOrderByFirstNameAsc();
		
	}

	public Patient findById(int theId) {
		Optional<Patient> result = patientRepository.findById(theId);
		Patient thepatient = null;

		if (result.isPresent()) {
			thepatient = result.get();
		} else {
			throw new RuntimeException("Did Not find Patient ID");
		}
		return thepatient;
	}

	public void Delete(int theId) {
		patientRepository.deleteById(theId);
	}

	public List<Appointment> findPendingAppointment(int patientId, String checked) {
		System.out.println("====================");
		System.out.println(checked);
		
	return appointmentRepository.findPendingAppointment(patientId,checked);
	}

	public List<Doctor> fetchallDoctor() {

	  return doctorRepository.findAllByOrderByFirstNameAsc();

	}

	public Patient findByEmail(String email) {

		Patient patient = null;

		try {
			System.out.println("email before" + email);

			patient = patientRepository.findByPatientEmail(email);

			System.out.println("email after" + email);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return patient;
	}
}

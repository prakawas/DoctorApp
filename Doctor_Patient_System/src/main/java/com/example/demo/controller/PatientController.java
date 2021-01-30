package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Checked;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.PatientService;

@RestController

@RequestMapping("/patient")
public class PatientController {

	@Autowired
	PatientService patientService;

	@PostMapping("/save")
	public void addPatient(@RequestBody Patient thepatient) {

		System.out.println("======= PatientController.addPatient start ========");
		System.out.println("thepatient  " + thepatient.toString());
		patientService.SaveALL(thepatient);

		System.out.println("======= PatientController.addPatient End ========");

		// return "redirect:/doctor/list";
	}
	
	@GetMapping("/GetDoctor")
	public List<Doctor> getDoctor()
	{
		System.out.println("======= PatientController.getDoctor start ========");
	      List<Doctor> allDoctor = patientService.fetchallDoctor();
	      System.out.println(allDoctor.toString());
	      System.out.println("======= PatientController.getDoctor end ========");
		 return allDoctor;
		
	}
	
	
	
	
	
	

	@GetMapping("/showformadd")
	public String showForAdd(Model themodel) {

		Patient patient = new Patient();

		themodel.addAttribute("patient", patient);

		return "patient/patient-form";

	}

	@GetMapping("/list")
	public List<Patient> getPatient(Model theModel) {
		List<Patient> thelist = patientService.findAll();
		theModel.addAttribute("patient", thelist);
		// return "patient/doctor";
		return thelist;
	}

	@GetMapping("/getById/{patientId}")
	public Patient getById(@PathVariable("patientId") int patientId, Model theModel)

	{
		Patient thepatient = patientService.findById(patientId);
		if (thepatient == null) {
			throw new RuntimeException("Patient Not Found");
		}
		theModel.addAttribute("patient", thepatient);

		return thepatient;
		// return "patient/patient-form";

	}

	@GetMapping("/patients/{patientId}/")
	public void DeleteById(@PathVariable("patientId") int patientId) {
		patientService.Delete(patientId);

	}

	@GetMapping("/findpendingapp/{patientId}")
	public List<Appointment> findAppointment(@PathVariable("patientId") int patientId) {
		System.out.println(patientId);
       System.out.println("======= PatientController.findpenddingAppointment start ========");
		List<Appointment> pa = patientService.findPendingAppointment(patientId, Checked.COMPLETED.toString());
		System.out.println("======= PatientController.findpenddingAppointment end =======");
		System.out.println(pa.toString());
		return pa;
		
	}

	@GetMapping("/getValidation/{email}/{password}")
	public Patient Validation(@PathVariable String email, @PathVariable String password) {
		Patient patient = patientService.findByEmail(email);
		System.out.println(patient.getPassword());
		System.out.println(password);
		if (patient == null) {
			throw new RuntimeException("No Such type of User");
		}
		if (!patient.getPassword().equals(password)) {
			throw new RuntimeException("Incorrect Password");
		}

		return patient;
	}

}

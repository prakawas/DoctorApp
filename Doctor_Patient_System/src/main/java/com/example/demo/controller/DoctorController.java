package com.example.demo.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;
import com.example.demo.service.DoctorService;


	@RestController
	@RequestMapping("/doctor")
	@ResponseBody
	public class DoctorController {
	    @Autowired
		DoctorService thedoctorService;
	    
	    
		public DoctorController(DoctorService thedoctorService) {
			super();
			this.thedoctorService = thedoctorService;
		}

		@PostMapping("/save")
		public void addDoctor(@RequestBody Doctor thedoctor)
		{
		System.out.println(" ======= DoctorController.savePatient start======  ");
		System.out.println(thedoctor.toString());
			thedoctorService.SaveALL(thedoctor);
			System.out.println(" ======= DoctorController.savePatient end======  ");
		  //  return "redirect:/doctor/list";
		}
		
		@GetMapping("/showformadd")
		public String showForAdd(Model themodel)
		{
			
			Doctor doctors=new Doctor();
		    
			themodel.addAttribute("doctor", doctors);
			
			return "doctors/doctor-form";
			
		}
		
		@GetMapping("/list")
		public List<Doctor> getDoctor(Model theModel)
		{
			List<Doctor> thelist= thedoctorService.findAll();	
			theModel.addAttribute("doctor", thelist);
			//return "doctors/doctor";
			return thelist;
		}
		
		
	@GetMapping("/getById/{doctorId}")
		public Doctor getById(@PathVariable("doctorId") int doctorId,Model theModel)
		{
			
		Doctor thedoctor= thedoctorService.findById(doctorId);
		if(thedoctor==null)
		{
			throw new RuntimeException("Doctor Not Found");
		}
		theModel.addAttribute("doctor", thedoctor);
		
		return thedoctor;
		//return "doctors/doctor-form";
			
		}
	
	@GetMapping("/getTodayappoint/{doctorId}")
	public List<Appointment> todayAppointment(@PathVariable("doctorId") int doctorId)
	{
		
		List<Appointment> listAppointment = thedoctorService.listAppoint(doctorId);
		
		return listAppointment;
		
	}

	@GetMapping("/deleteById/{doctorId}")
	public void DeleteById(@PathVariable("doctorId") int doctorId)
	{
		 thedoctorService.Delete(doctorId) ;
		
	}
	
	@GetMapping("/getValidation/{email}/{password}")
	public  Doctor Validation(@PathVariable String email,@PathVariable String password)
	{
		Doctor doctor=thedoctorService.findByEmail(email);
		System.out.println(doctor.getPassword());
		System.out.println(password);
		if(doctor==null)
		{
			throw new RuntimeException("No Such type of User"); 
		}
		if(!doctor.getPassword().equals(password))
		{
			throw new RuntimeException("Incorrect Password");
		}

		return doctor;
	}

	}

	
	
	
	
	
	


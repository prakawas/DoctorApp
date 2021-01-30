package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.ChangAppointmentStatusReq;
import com.example.demo.entity.CheckAptAvailabilityReq;
import com.example.demo.entity.Checked;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.service.AppointmentService;

import java.util.List;
import java.util.Optional;

@RestController
public class AppintmentController {
	@Autowired
	AppointmentService appointmentService;

	@Autowired
	AppointmentRepository appointmentRepository;

	@PostMapping("/bookappointment")
	public String bookAppointment(@RequestBody Appointment appointment) {
		try {

			if (appointment == null) {
				return "faliure";
			}

			System.out.println("======= AppintmentController.bookAppointment start ========");
			System.out.println("appointment:  " + appointment.toString());
			try {
				appointmentService.SaveALL(appointment);

			} catch (Exception e) {
				e.printStackTrace();
				return "faliure";

			}

			System.out.println("======= AppintmentController.bookAppointment End ========");

		} catch (Exception e) {
			e.printStackTrace();
			return "faliure";

		}
		return "success";
	}

	@PatchMapping("/bookappointment")
	public String changAppointmentStatus(@RequestBody ChangAppointmentStatusReq changAppointmentStatusReq) {
		Appointment appointment;
		System.out.println("======= AppintmentController.changAppointmentStatus start ========");
		
		
		try {

			if (changAppointmentStatusReq.getAppointmentId()== 0 || changAppointmentStatusReq.getStatus() == null) {
				return "faliure";
			}
			
			
			System.out.println("appointmentId:  " + changAppointmentStatusReq.getAppointmentId() + " and Status:"
					+ changAppointmentStatusReq.getStatus());

			try {

				java.util.Optional<Appointment> appointmentTemp = appointmentRepository
						.findById(changAppointmentStatusReq.getAppointmentId());

				if (appointmentTemp.isPresent()) {
					System.out.println("Appointment with input appointment is present in the system");
					appointment = appointmentTemp.get();
					System.out.println("appointment in DB" + appointment.toString());
					appointment.setCheckedStatus(changAppointmentStatusReq.getStatus());
					appointmentRepository.saveAndFlush(appointment);

				} else {

					System.out.println("Appointment with input appointment is NOT present in the system");
					return "faliure";
				}

			} catch (Exception e) {
				e.printStackTrace();
				return "faliure";

			}

			System.out.println("======= AppintmentController.changAppointmentStatus End ========");

		} catch (Exception e) {
			e.printStackTrace();
			return "faliure";

		}
		return "success";
	}
	
	
	
	
	
	
	@PutMapping("/bookappointment")
	public String checkAptAvailability(@RequestBody CheckAptAvailabilityReq checkAptAvailabilityReq) {
		Appointment appointment;
		System.out.println("======= AppintmentController.checkAptAvailability start ========");
		System.out.println("checkAptAvailabilityReq.getDoctorId() : " + checkAptAvailabilityReq.getDoctorId() 
		+ "checkAptAvailabilityReq.getAptTimeSlot(): "  + checkAptAvailabilityReq.getAptTimeSlot());
		
		try {

			if (checkAptAvailabilityReq.getDoctorId()== 0|| checkAptAvailabilityReq.getAptTimeSlot() == null) {
				System.out.println("======= AppintmentController.checkAptAvailability Ended with failure | incomplete Arguments========");
				return "faliure";
			}
			
			
			

			try {

				List<Appointment> appointmentTemp = 
						appointmentRepository
						.checkAptAvailablity(checkAptAvailabilityReq.getDoctorId() ,
								checkAptAvailabilityReq.getAptTimeSlot().toString());
						

				if (appointmentTemp.size() >=1) {
					System.out.println("Appointment time slot is already BOOKED");
					return "BOOKED";
					
				} else {

					System.out.println("Appointment time slot is available");
					System.out.println("======= AppintmentController.checkAptAvailability Ended with success ========");
					return "AVAILABLE";
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("======= AppintmentController.checkAptAvailability Ended with failure ========");
				return "faliure";

			}

			

		} catch (Exception e) {
			e.printStackTrace();
			return "faliure";

		}
		
	}
}

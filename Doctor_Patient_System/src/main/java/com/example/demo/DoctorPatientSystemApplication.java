package com.example.demo;




import java.util.Date;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.MAX;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Checked;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Gender;
import com.example.demo.entity.Patient;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;




@SpringBootApplication
public class DoctorPatientSystemApplication {

	public static void main(String[] args) {
		ApplicationContext	context=SpringApplication.run(DoctorPatientSystemApplication.class, args);
		
		DoctorRepository doctorrep =context.getBean(DoctorRepository.class);
		PatientRepository patientrep =context.getBean(PatientRepository.class);
		AppointmentRepository appointmentrep =context.getBean(AppointmentRepository.class);
		Doctor doc=new Doctor();
		doc.setFirstName("Mangesh");
		doc.setAddress("Kiran Nagar no-2");
		
		doc.setDateOfBirth(new Date());
		doc.setGender(Gender.MALE);
		doc.setEmail("mangeshmanekar123@gmail.com");
		
	    Patient pat=new Patient();
	    pat.setFirstName("Kiran");
		pat.setLastName("Manekar");
		pat.setGender(Gender.FEMALE);
		pat.setEmail("kiran05@gmail.com");
		pat.setPassword("Om");
		pat.setDateOfBirth(new Date());
		   Patient pat1=new Patient();
		    pat1.setFirstName("Mangesh");
			pat1.setLastName("Manekar");
			pat1.setGender(Gender.MALE);
			pat1.setEmail("mangeshmanekar123@gmail.com");
			pat1.setPassword("Mango");
			pat1.setDateOfBirth(new Date());
		
		Appointment ap=new Appointment();
		ap.setCheckedStatus(Checked.WAITING);
	    ap.setDoctorId(0);
	    ap.setPatientId(1);
	    ap.setTimes("11:00");
	    ap.setDate(new Date());
		Doctor doct=doctorrep.save(doc);
		Patient pat5=patientrep.save(pat);
		Patient pat6=patientrep.save(pat1);
		Appointment ap1=appointmentrep.save(ap);
	}

}

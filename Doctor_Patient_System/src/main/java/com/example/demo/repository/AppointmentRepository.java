package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.AppointmentTimeSlots;
import com.example.demo.entity.Checked;


public interface AppointmentRepository extends JpaRepository<Appointment, Integer>
{
@Query( value = "SELECT appointment_id,doctor_id,patient_id,date, times, checked_status FROM Appointment a WHERE a.patient_id = ?1 and a.checked_status != ?2 ",
		  nativeQuery = true)
List<Appointment> findPendingAppointment(int patientId, String checked);

@Query(value="SELECT appointment_id,doctor_id,patient_id,date, times, checked_status FROM Appointment a WHERE a.doctor_id=?1", nativeQuery = true)
List<Appointment> findListOfTodayAppointment(int doctorId);

@Query(value="SELECT appointment_id,doctor_id,patient_id,date, times, checked_status,apt_time_slot FROM Appointment a WHERE a.doctor_id=?1 and a.apt_time_slot=?2 ", nativeQuery = true)
List<Appointment> checkAptAvailablity(int doctorId,String aptTimeSlot);



}

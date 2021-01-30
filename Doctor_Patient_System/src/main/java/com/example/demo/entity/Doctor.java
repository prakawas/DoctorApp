package com.example.demo.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;





@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctors")
public class Doctor {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int doctorId;
@NotBlank(message = "Invalid email address")
@Email(message = "Invalid email address")
private String email;

@Size(min = 5, message = "Password too short")
private String password;

@Size(min = 5, message = "First name too short")
private String firstName;

@Size(min = 5, message = "Last name too short")
private String lastName;

@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "PST")
@DateTimeFormat(pattern = "yyyy-MM-dd")
@NotNull(message = "Invalid date of birth")
private Date dateOfBirth;

@NotBlank(message = "Invalid gender.")
@Pattern(regexp = "^(MALE|FEMALE)$", message = "Invalid gender.")
@Enumerated(value = EnumType.STRING)
private Gender gender;

private String phoneNumber;

private String speciaList;
		
@Size(max = 256, message = "Invalid address length")
private String address;



	
}

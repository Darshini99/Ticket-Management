package com.darshini.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Tickets")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ticketid;
	final String msg = "Employee id must not be blank must not be null";
	@NotBlank
	@NotNull(message = "Employee id must not be null")
	private String empId;
	@NotBlank
	@NotNull(message = msg)
	private String title;
	@NotBlank
	@NotNull(message = msg)
	private String description;
	@NotBlank
	@NotNull(message = msg)
	private String status;
	@NotBlank
	@NotNull(message = msg)
	private String assignee;
	@NotBlank
	@NotNull(message = msg)
	private String priority;
	@NotBlank
	@NotNull(message = msg)
	private String reporter;
	@Digits(fraction = 0, integer = 10)
	@NotNull(message = msg)
	private Integer projectId;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private LocalDate dueDate;

}

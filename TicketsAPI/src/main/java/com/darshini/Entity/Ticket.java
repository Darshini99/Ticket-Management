package com.darshini.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name= "Tickets")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ticketid;
	private String empId;
	private String title;
	private String description;
	private String status;
	private String assignee;
	private String priority;
	private String reporter;
	private Integer projectId;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private LocalDate dueDate;
	
}

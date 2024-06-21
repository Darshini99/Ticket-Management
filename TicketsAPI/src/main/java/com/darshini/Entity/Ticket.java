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

@Data
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Entity
@Table(name = "Tickets")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ticketid;
	@NotBlank
	@NotNull(message = Message.msg)
	private String empId;
	@NotBlank
	@NotNull(message = Message.msg)
	private String title;
	@NotBlank
	@NotNull(message = Message.msg)
	private String description;
	@NotBlank
	@NotNull(message = Message.msg)
	private String status;
	@NotBlank
	@NotNull(message = Message.msg)
	private String assignee;
	@NotBlank
	@NotNull(message = Message.msg)
	private String priority;
	@NotBlank
	@NotNull(message = Message.msg)
	private String reporter;
	@Digits(fraction = 0, integer = 10)
	@NotNull(message = Message.msg)
	private Integer projectId;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private LocalDate dueDate;
	@Override
	public String toString() {
		return "Ticket [ticketid=" + ticketid + ", empId=" + empId + ", title=" + title + ", description=" + description
				+ ", status=" + status + ", assignee=" + assignee + ", priority=" + priority + ", reporter=" + reporter
				+ ", projectId=" + projectId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", dueDate="
				+ dueDate + "]";
	}

}

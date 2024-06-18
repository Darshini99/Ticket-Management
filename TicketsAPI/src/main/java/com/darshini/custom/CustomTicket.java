package com.darshini.custom;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.darshini.Entity.Ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomTicket {
	
	private String message;
	private HttpStatus status;
	private boolean success;
	
	//private Ticket ticket;

	private List<Ticket> ticketlist;

}

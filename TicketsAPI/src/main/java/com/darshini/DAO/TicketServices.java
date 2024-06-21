package com.darshini.DAO;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.darshini.Entity.Ticket;
import com.darshini.Exceptions.TicketNotFoundException;

public interface TicketServices {
	
	public ResponseEntity<Ticket> sendTicket(Ticket ticket) throws NoSuchMethodException;
	
	public ResponseEntity<List<Ticket>> allTickets() throws TicketNotFoundException;
	
	public ResponseEntity<List<Ticket>> fetchByTicketStatus(String status) throws TicketNotFoundException;
	
	public ResponseEntity<Ticket> ticketById (Integer id) throws TicketNotFoundException;
	
	public ResponseEntity<String> deleteTicketById(Integer id) throws TicketNotFoundException;
	
	public ResponseEntity<Ticket> updateTicketById(Integer id,Ticket ticket) throws TicketNotFoundException;
}

package com.darshini.DAO;

import org.springframework.http.ResponseEntity;

import com.darshini.Entity.Ticket;
import com.darshini.Exceptions.TicketNotFoundException;
import com.darshini.custom.CustomTicket;

public interface TicketServices {
	
	public ResponseEntity<CustomTicket> sendTicket(Ticket ticket) throws NoSuchMethodException;
	
	public ResponseEntity<CustomTicket> allTickets() throws TicketNotFoundException;
	
	public ResponseEntity<CustomTicket> fetchByTicketStatus(String status) throws TicketNotFoundException;
	
	public ResponseEntity<CustomTicket> ticketById (Integer id) throws TicketNotFoundException;
	
	public ResponseEntity<CustomTicket> deleteTicketById(Integer id) throws TicketNotFoundException;
	
	public ResponseEntity<CustomTicket> updateTicketById(Integer id,Ticket ticket) throws TicketNotFoundException;
}

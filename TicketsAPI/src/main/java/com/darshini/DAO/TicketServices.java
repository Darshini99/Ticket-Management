package com.darshini.DAO;

import java.util.List;
import java.util.Optional;

import com.darshini.Entity.Ticket;

public interface TicketServices {
	
	public Ticket sendTicket(Ticket ticket);
	
	public List<Ticket> allTickets();
	
	public List<Ticket> fetchByTicketStatus(String status);
	
	public Optional<Ticket> ticketById (Integer id);
	
	public void deleteTicketById(Integer id);
	
	public Ticket updateTicketById(Integer id,Ticket ticket);
}

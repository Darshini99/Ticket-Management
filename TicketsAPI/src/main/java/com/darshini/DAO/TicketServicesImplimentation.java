package com.darshini.DAO;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darshini.Entity.Ticket;
import com.darshini.Repository.TicketRepository;

@Service
public class TicketServicesImplimentation implements TicketServices{

	@Autowired
	private TicketRepository ticketrepository;
	
	public Ticket sendTicket(Ticket ticket){
		LocalDate date = LocalDate.now();
		ticket.setCreatedAt(date);
		ticket.setUpdatedAt(date);
		return ticketrepository.save(ticket);
	}

	@Override
	public List<Ticket> allTickets() {
		return ticketrepository.findAll();
	}

	@Override
	public List<Ticket> fetchByTicketStatus(String status) {
		return ticketrepository.findBystatusContaining(status);
	}

	@Override
	public Optional<Ticket> ticketById(Integer id) {
		return ticketrepository.findById(id);
	}
	
	public void deleteTicketById(Integer id) {
		 ticketrepository.deleteById(id);
	}

	@Override
	public Ticket updateTicketById(Integer id,Ticket ticket) {
		Optional<Ticket> optkt = ticketrepository.findById(id);
		Ticket tkt = optkt.get();
			System.out.println(tkt);
			tkt.setTitle(ticket.getTitle());
			tkt.setDescription(ticket.getDescription());
			tkt.setStatus(ticket.getStatus());
			tkt.setAssignee(ticket.getAssignee());
			tkt.setPriority(ticket.getPriority());
			tkt.setReporter(ticket.getReporter());
			tkt.setDueDate(ticket.getDueDate());
			return ticketrepository.save(tkt);
		
	}
}

package com.darshini.DAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.darshini.Entity.Ticket;
import com.darshini.Exceptions.TicketNotFoundException;
import com.darshini.Repository.TicketRepository;

@Service
public class TicketServicesImplimentation implements TicketServices {

	@Autowired
	private TicketRepository ticketrepository;

	public ResponseEntity<Ticket> sendTicket(Ticket ticket) throws NoSuchMethodException {
		LocalDate date = LocalDate.now();
		ticket.setCreatedAt(date);
		ticket.setUpdatedAt(date);
		Ticket tkt = ticketrepository.save(ticket);
		if (tkt != null) {
			return new ResponseEntity<Ticket>(tkt, HttpStatus.CREATED);
		} else
			throw new NoSuchMethodException("Please send data in valid format");
	}

	@Override
	public ResponseEntity<List<Ticket>> allTickets() throws TicketNotFoundException {
		List<Ticket> tktlist = ticketrepository.findAll();
		if (tktlist.isEmpty())
			throw new TicketNotFoundException("Oops!! There is no ticket ....");
		else {
			return new ResponseEntity<List<Ticket>>(tktlist, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<List<Ticket>> fetchByTicketStatus(String status) throws TicketNotFoundException {
		List<Ticket> tktlist = ticketrepository.findBystatusContaining(status);
		if (tktlist.isEmpty())
			throw new TicketNotFoundException("No tickets found with this status containing: " + status);
		else {
			return new ResponseEntity<List<Ticket>>(tktlist, HttpStatus.FOUND);
		}
	}

	@Override
	public ResponseEntity<Ticket> ticketById(Integer id) throws TicketNotFoundException {
		Optional<Ticket> optkt = ticketrepository.findById(id);
		if (optkt.isPresent()) {
			Ticket tkt = optkt.get();
			return new ResponseEntity<Ticket>(tkt, HttpStatus.FOUND);
		} else {
			throw new TicketNotFoundException("No ticket has been created with this id: " + id);
		}
	}

	public ResponseEntity<String> deleteTicketById(Integer id) throws TicketNotFoundException {
		Optional<Ticket> opticket = ticketrepository.findById(id);
		if (opticket.isPresent()) {
			ticketrepository.deleteById(id);
			Optional<Ticket> optionalticket = ticketrepository.findById(id);
			if (optionalticket.isEmpty())
				return new ResponseEntity<String>("Deleted successfully!!", HttpStatus.OK);
		} else
			throw new TicketNotFoundException("No ticket found with this id: " + id);
		return null;
	}

	@Override
	public ResponseEntity<Ticket> updateTicketById(Integer id, Ticket ticket) throws TicketNotFoundException {
		Optional<Ticket> opticket = ticketrepository.findById(id);
		if (opticket.isPresent()) {
			Ticket tkt = opticket.get();
			tkt.setTitle(ticket.getTitle());
			tkt.setDescription(ticket.getDescription());
			tkt.setStatus(ticket.getStatus());
			tkt.setAssignee(ticket.getAssignee());
			tkt.setPriority(ticket.getPriority());
			tkt.setReporter(ticket.getReporter());
			tkt.setDueDate(ticket.getDueDate());
			ticketrepository.save(tkt);
			return new ResponseEntity<Ticket>(tkt, HttpStatus.OK);
		} else
			throw new TicketNotFoundException("No ticket found with this id: " + id);
	}
}

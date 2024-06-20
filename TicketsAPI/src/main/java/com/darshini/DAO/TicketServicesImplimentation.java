package com.darshini.DAO;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.darshini.Entity.Ticket;
import com.darshini.Exceptions.TicketNotFoundException;
import com.darshini.Repository.TicketRepository;
import com.darshini.custom.CustomTicket;

@Service
public class TicketServicesImplimentation implements TicketServices{

	@Autowired
	private TicketRepository ticketrepository;
	
	public ResponseEntity<CustomTicket> sendTicket(Ticket ticket) throws NoSuchMethodException{
		LocalDate date = LocalDate.now();
		ticket.setCreatedAt(date);
		ticket.setUpdatedAt(date);
		CustomTicket cust =  CustomTicket.builder().message("Ticket added successfully")
				.status(HttpStatus.CREATED).success(true).build();
		Ticket tkt = ticketrepository.save(ticket);
		if(tkt!=null) {
			List<Ticket> tktlist = new ArrayList<>() ;
			tktlist.add(tkt);
			cust.setTicketlist(tktlist);
			return new ResponseEntity<CustomTicket>(cust,HttpStatus.CREATED); 
		} else throw new NoSuchMethodException("Please send data in valid format");
	}

	@Override
	public ResponseEntity<CustomTicket> allTickets() throws TicketNotFoundException {
		CustomTicket cust =CustomTicket.builder().message("Tickets are")
				.status(HttpStatus.OK).success(true).build();
		List<Ticket> tktlist = ticketrepository.findAll();
		if(tktlist.isEmpty()) throw new TicketNotFoundException("Oops!! There is no ticket ....");
		else {
			cust.setTicketlist(tktlist);
			return new ResponseEntity<CustomTicket>(cust,HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<CustomTicket> fetchByTicketStatus(String status) throws TicketNotFoundException {
		CustomTicket cust= CustomTicket.builder().message("The tickets by status..")
				.status(HttpStatus.FOUND).success(true).build();
		List<Ticket> tktlist = ticketrepository.findBystatusContaining(status);
		if(tktlist.isEmpty()) throw new TicketNotFoundException("No tickets found with this status containing: "+status);
		else {
			cust.setTicketlist(tktlist);
			return new ResponseEntity<CustomTicket>(cust,HttpStatus.FOUND);
		}
				
	}

	@Override
	public ResponseEntity<CustomTicket> ticketById(Integer id) throws TicketNotFoundException {
		CustomTicket cust = CustomTicket.builder().message("Ticket with the id is :"+id)
				.status(HttpStatus.FOUND).success(true).build();
		Optional<Ticket> optkt = ticketrepository.findById(id);
		if(optkt.isPresent()) {
			List<Ticket> tktlist = new ArrayList<>();
			tktlist.add(optkt.get());
			cust.setTicketlist(tktlist);
			return new ResponseEntity<CustomTicket>(cust,HttpStatus.FOUND);
		}else {
			throw new TicketNotFoundException("No ticket has been created with this id: "+id);
		}		
	}
	
	public ResponseEntity<CustomTicket> deleteTicketById(Integer id) throws TicketNotFoundException {
		CustomTicket cust = CustomTicket.builder().message("Ticket deleted successfully....")
				.status(HttpStatus.OK).success(true).build();
		Optional<Ticket> opticket = ticketrepository.findById(id);
		if(opticket.isPresent()) {
			ticketrepository.deleteById(id);
			Optional<Ticket> optionalticket = ticketrepository.findById(id);
			System.out.println(optionalticket);
			if(optionalticket.isEmpty())return new ResponseEntity<CustomTicket>(cust,HttpStatus.OK);
		}
		else throw new TicketNotFoundException("No ticket found with this id: "+id);
		return null;
	}

	@Override
	public ResponseEntity<CustomTicket> updateTicketById(Integer id,Ticket ticket) throws TicketNotFoundException {
		CustomTicket cust = CustomTicket.builder().message("Updated ticket is: ")
				.status(HttpStatus.OK).success(true).build();
	Optional<Ticket> opticket = ticketrepository.findById(id);
	if(opticket.isPresent()) {
		Ticket tkt = opticket.get();
		tkt.setTitle(ticket.getTitle());
		tkt.setDescription(ticket.getDescription());
		tkt.setStatus(ticket.getStatus());
		tkt.setAssignee(ticket.getAssignee());
		tkt.setPriority(ticket.getPriority());
		tkt.setReporter(ticket.getReporter());
		tkt.setDueDate(ticket.getDueDate());
		ticketrepository.save(tkt);
		List<Ticket> tktlist = new ArrayList<>();
		tktlist.add(tkt);
		cust.setTicketlist(tktlist);
		cust.setTicketlist(tktlist);
		return new ResponseEntity<CustomTicket>(cust,HttpStatus.OK);
		}else throw new TicketNotFoundException("No ticket found with this id: "+id);

	}
}

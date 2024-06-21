package com.darshini.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.darshini.DAO.TicketServicesImplimentation;
import com.darshini.Entity.Ticket;
import com.darshini.Exceptions.TicketNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class TicketController {
	
	@Autowired
	TicketServicesImplimentation ticketserviceimpl;
	
	@PostMapping("/addTicket")
	public ResponseEntity<Ticket> createTicket(@Valid @RequestBody Ticket ticket) throws NoSuchMethodException{
		return ticketserviceimpl.sendTicket(ticket);
	}
	
	@GetMapping("/getAllTickets")
	public ResponseEntity<List<Ticket>> getAllTickets() throws TicketNotFoundException{
		return ticketserviceimpl.allTickets();
	}
	
	@GetMapping("/getTicketByStatus/{sts}")
	public ResponseEntity<List<Ticket>> getTicketsByStatus(@PathVariable String sts) throws TicketNotFoundException{
		return ticketserviceimpl.fetchByTicketStatus(sts);
	}
	
	@GetMapping("/getTicketById")
	public ResponseEntity<Ticket> getTicketsById(@RequestParam Integer id) throws TicketNotFoundException{
		return ticketserviceimpl.ticketById(id);
	}
	
	@DeleteMapping("/deleteTicketById")
	public ResponseEntity<String> deleteTicketsById(@RequestParam Integer id) throws TicketNotFoundException{
		return ticketserviceimpl.deleteTicketById(id);
	}
	
	@PutMapping("/updateById/{id}")
	public ResponseEntity<Ticket> updateTicketsById(@PathVariable Integer id,@Valid @RequestBody Ticket ticket) throws TicketNotFoundException{
		return ticketserviceimpl.ticketById(id);
	}
}

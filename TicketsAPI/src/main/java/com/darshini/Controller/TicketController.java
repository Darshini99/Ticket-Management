package com.darshini.Controller;

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
import com.darshini.custom.CustomTicket;

@RestController
@RequestMapping("/api/v1")
public class TicketController {
	
	@Autowired
	TicketServicesImplimentation ticketserviceimpl;
	
	@PostMapping("/addTicket")
	public ResponseEntity<CustomTicket> createTicket(@RequestBody Ticket ticket) throws NoSuchMethodException{
		return ticketserviceimpl.sendTicket(ticket);
	}
	
	@GetMapping("/getAllTickets")
	public ResponseEntity<CustomTicket> getAllTickets() throws TicketNotFoundException{
		return ticketserviceimpl.allTickets();
	}
	
	@GetMapping("/getTicketByStatus/{sts}")
	public ResponseEntity<CustomTicket> getTicketsByStatus(@PathVariable String sts) throws TicketNotFoundException{
		return ticketserviceimpl.fetchByTicketStatus(sts);
	}
	
	@GetMapping("/getTicketById")
	public ResponseEntity<CustomTicket> getTicketsById(@RequestParam Integer id) throws TicketNotFoundException{
		return ticketserviceimpl.ticketById(id);
	}
	
	@DeleteMapping("/deleteTicketById")
	public ResponseEntity<CustomTicket> deleteTicketsById(@RequestParam Integer id) throws TicketNotFoundException{
		return ticketserviceimpl.deleteTicketById(id);
	}
	
	@PutMapping("/updateById/{id}")
	public ResponseEntity<CustomTicket> updateTicketsById(@PathVariable Integer id,@RequestBody Ticket ticket) throws TicketNotFoundException{
		return ticketserviceimpl.ticketById(id);
	}
}

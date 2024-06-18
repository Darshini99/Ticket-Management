package com.darshini.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.darshini.custom.CustomTicket;

@RestController
@RequestMapping("/api/v1")
public class TicketController {
	
	@Autowired
	TicketServicesImplimentation ticketserviceimpl;
	
	@PostMapping("/addTicket")
	public ResponseEntity<CustomTicket> createTicket(@RequestBody Ticket ticket){
		CustomTicket cust =  CustomTicket.builder().message("ticket added successfully")
		.status(HttpStatus.CREATED).success(true).build();
		Ticket tkt = ticketserviceimpl.sendTicket(ticket);
		List<Ticket> tktlist = new ArrayList<>() ;
		tktlist.add(tkt);
		cust.setTicketlist(tktlist);
		return new ResponseEntity<CustomTicket>(cust,HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllTickets")
	public ResponseEntity<CustomTicket> getAllTickets(){
		CustomTicket cust =CustomTicket.builder().message("ticket are")
		.status(HttpStatus.OK).success(true).build();
		List<Ticket> tktlist = ticketserviceimpl.allTickets();
		cust.setTicketlist(tktlist);
		return new ResponseEntity<CustomTicket>(cust,HttpStatus.OK);
	}
	
	@GetMapping("/getTicketByStatus/{sts}")
	public ResponseEntity<CustomTicket> getTicketsByStatus(@PathVariable String sts){
		CustomTicket cust= CustomTicket.builder().message("The tickets by status")
		.status(HttpStatus.FOUND).success(true).build();
		List<Ticket> tktlist = ticketserviceimpl.fetchByTicketStatus(sts);
		cust.setTicketlist(tktlist);
		return new ResponseEntity<CustomTicket>(cust,HttpStatus.FOUND);
	}
	
	@GetMapping("/getTicketById")
	public ResponseEntity<CustomTicket> getTicketsById(@RequestParam Integer id){
		CustomTicket cust = CustomTicket.builder().message("ticket is: ")
		.status(HttpStatus.FOUND).success(true).build();
		Optional<Ticket> optkt = ticketserviceimpl.ticketById(id);
		List<Ticket> tktlist = new ArrayList<>();
		tktlist.add(optkt.get());
		cust.setTicketlist(tktlist);
		return new ResponseEntity<CustomTicket>(cust,HttpStatus.FOUND);
	}
	
	@DeleteMapping("/deleteTicketById")
	public ResponseEntity<CustomTicket> deleteTicketsById(@RequestParam Integer id){
		CustomTicket cust = CustomTicket.builder().message("ticket is: ")
				.status(HttpStatus.FOUND).success(true).build();
		ticketserviceimpl.deleteTicketById(id);
		return new ResponseEntity<CustomTicket>(cust,HttpStatus.FOUND);
	}
	
	@PutMapping("/updateById/{id}")
	public ResponseEntity<CustomTicket> updateTicketsById(@PathVariable Integer id,@RequestBody Ticket ticket){
		CustomTicket cust = CustomTicket.builder().message("updated ticket is: ")
				.status(HttpStatus.OK).success(true).build();
		Ticket tkt = ticketserviceimpl.updateTicketById(id,ticket);
		List<Ticket> tktlist = new ArrayList<>();
		tktlist.add(tkt);
		cust.setTicketlist(tktlist);
		cust.setTicketlist(tktlist);
		return new ResponseEntity<CustomTicket>(cust,HttpStatus.OK);
	}
}

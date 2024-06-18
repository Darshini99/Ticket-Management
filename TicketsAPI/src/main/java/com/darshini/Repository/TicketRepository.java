package com.darshini.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.darshini.Entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	public List<Ticket> findBystatusContaining(String status);
	//public Ticket updateTicketByTicketId(Integer id,Ticket ticket);
}

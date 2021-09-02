package com.softraysolutions.basicpassengerservice.repositories;

import com.softraysolutions.basicpassengerservice.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}

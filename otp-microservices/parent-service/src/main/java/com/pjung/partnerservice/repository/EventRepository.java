package com.pjung.partnerservice.repository;

import com.pjung.partnerservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findEventByEventId(Long eventId);
}

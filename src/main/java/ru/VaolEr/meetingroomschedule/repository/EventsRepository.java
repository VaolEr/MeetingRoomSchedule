package ru.VaolEr.meetingroomschedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.VaolEr.meetingroomschedule.model.Event;

@Transactional(readOnly = true)
public interface EventsRepository extends JpaRepository<Event, Integer> {
}

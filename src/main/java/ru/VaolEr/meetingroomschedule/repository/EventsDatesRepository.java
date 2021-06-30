package ru.VaolEr.meetingroomschedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.VaolEr.meetingroomschedule.model.EventDate;

@Transactional(readOnly = true)
public interface EventsDatesRepository extends JpaRepository<EventDate, Integer> {
}

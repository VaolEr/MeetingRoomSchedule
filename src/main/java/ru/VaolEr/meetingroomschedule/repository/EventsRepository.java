package ru.VaolEr.meetingroomschedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.VaolEr.meetingroomschedule.model.Event;

import java.sql.Timestamp;
import java.util.List;

@Transactional(readOnly = true)
public interface EventsRepository extends JpaRepository<Event, Integer> {
    @Query(value = "SELECT ed.id FROM event_date ed WHERE ed.startTime < :event_end AND ed.endTime > :event_start")
    List<Integer> checkOverlapsBetweenDatesAndReturnEventIds(@Param("event_start")Timestamp event_start, @Param("event_end") Timestamp event_end);

}

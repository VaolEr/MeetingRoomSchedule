package ru.VaolEr.meetingroomschedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.VaolEr.meetingroomschedule.model.MeetingRoom;

@Transactional(readOnly = true)
public interface MeetingRoomsRepository extends JpaRepository<MeetingRoom, Integer> {
}

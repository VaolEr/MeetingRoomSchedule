package ru.VaolEr.meetingroomschedule.model;

import ru.VaolEr.meetingroomschedule.model.abstractentity.AbstractNamedEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity(name = "meetingRoom")
@Table(name = "MeetingRooms")
public class MeetingRoom extends AbstractNamedEntity {

    @OneToMany
    @JoinColumn(name = "meetingRoom_id")
    List<EventScheduleDate> eventScheduleDate;

}

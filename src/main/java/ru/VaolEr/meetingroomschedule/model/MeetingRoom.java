package ru.VaolEr.meetingroomschedule.model;

import ru.VaolEr.meetingroomschedule.model.abstractentity.AbstractNamedBaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "meetingRoom")
@Table(name = "meetingrooms")
public class MeetingRoom extends AbstractNamedBaseEntity {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "meetingRoom")
    List<EventDate> eventDate;

    @Override
    public String toString() {
        return "MeetingRoom{" +
                "eventDate=" + eventDate.toString() +
                '}';
    }
}

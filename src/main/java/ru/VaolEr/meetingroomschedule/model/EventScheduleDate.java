package ru.VaolEr.meetingroomschedule.model;

import ru.VaolEr.meetingroomschedule.model.abstractentity.AbstractEntity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "event_date")
@Table(name = "Events_ScheduleDates")
public class EventScheduleDate extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "date")
    private ScheduleDate scheduleDate; //single date, but many events in a day

    @Column(name = "startTime")
    private Long startTime;

    @Column(name = "endTime")
    private Long endTime;

    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToOne
    @JoinColumn(name = "meetingRoom_id")
    private MeetingRoom meetingRoom;

}

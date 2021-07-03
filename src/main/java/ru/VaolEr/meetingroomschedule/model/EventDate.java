package ru.VaolEr.meetingroomschedule.model;

import lombok.Getter;
import lombok.Setter;
import ru.VaolEr.meetingroomschedule.model.abstractentity.AbstractBaseEntity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity(name = "event_date")
@Table(name = "Events_Dates")
@Getter
@Setter
public class EventDate extends AbstractBaseEntity {

    @JoinColumn(name = "date")
    private Date date;

    @Column(name = "starttime")
    private Timestamp startTime;

    @Column(name = "endtime")
    private Timestamp endTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "meetingroom_id")
    private MeetingRoom meetingRoom;

    @Override
    public String toString() {
        return "EventDate{" +
                "id=" + this.getId() +
                " date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", event=" + event.getId() +
                ", meetingRoom=" + meetingRoom.getId() +
                '}';
    }
}

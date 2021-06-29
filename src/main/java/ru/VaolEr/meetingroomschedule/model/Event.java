package ru.VaolEr.meetingroomschedule.model;

import ru.VaolEr.meetingroomschedule.model.abstractentity.AbstractNamedEntity;

import javax.persistence.*;

@Entity(name = "event")
@Table(name = "Events")
public class Event extends AbstractNamedEntity{

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "event_id")
    private EventScheduleDate eventScheduleDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "creatorUser_id")
    private User user;

}

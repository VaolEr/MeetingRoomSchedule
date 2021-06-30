package ru.VaolEr.meetingroomschedule.model;

import ru.VaolEr.meetingroomschedule.model.abstractentity.AbstractNamedBaseEntity;

import javax.persistence.*;

@Entity(name = "event")
@Table(name = "Events")
public class Event extends AbstractNamedBaseEntity {

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private EventDate eventDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "creatoruser_id")
    private User creatorUser;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + this.getId() +
                " description='" + description + '\'' +
                ", eventDate=" + eventDate.toString() +
                ", user=" + creatorUser.toString() +
                '}';
    }
}

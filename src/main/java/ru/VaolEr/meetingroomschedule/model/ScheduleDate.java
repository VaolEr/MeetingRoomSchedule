package ru.VaolEr.meetingroomschedule.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity(name = "scheduleDate")
@Table(name = "ScheduleDates")
public class ScheduleDate implements Persistable<Date> {

    @Id
    Date date;

    @Override
    public Date getId() {
        return date;
    }

    @Override
    public boolean isNew() {
        return null == getId();
    }
}

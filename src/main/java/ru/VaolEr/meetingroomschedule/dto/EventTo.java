package ru.VaolEr.meetingroomschedule.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Builder
@Data
public class EventTo {

    private String name;
    private String description;
    private Date date;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer creatorId;
    private Integer meetingRoomId;

    public Integer getDayOfWeek(){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(this.date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public Integer getHourOfDay(){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(this.startTime);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
}

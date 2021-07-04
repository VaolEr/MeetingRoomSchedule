package ru.VaolEr.meetingroomschedule.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @Author VaolEr
 * This class describes Event transfer object
 */

@Builder
@Data
public class EventTo {

    private Integer id;
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

    public Integer getMinutesOfHour(){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(this.startTime);
        return calendar.get(Calendar.MINUTE);
    }

    public String getStartAndEndTime(){
        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarStop = Calendar.getInstance();
        calendarStart.setTime(startTime);
        calendarStop.setTime(endTime);

        return String.format("%s - %s", getFormattedTime(calendarStart), getFormattedTime(calendarStop));
    }

    private String getFormattedTime(Calendar calendar){

        String hour = (calendar.get(Calendar.HOUR_OF_DAY) < 10) ? String.format("0%d", calendar.get(Calendar.HOUR_OF_DAY)) : String.format("%d", calendar.get(Calendar.HOUR_OF_DAY));
        String minutes = (calendar.get(Calendar.MINUTE) < 10) ? String.format("0%d", calendar.get(Calendar.MINUTE)) : String.format("%d", calendar.get(Calendar.MINUTE));

        return String.format("%s:%s", hour, minutes);
    }
}

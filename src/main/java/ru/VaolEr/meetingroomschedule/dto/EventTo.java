package ru.VaolEr.meetingroomschedule.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @Author VaolEr
 * This class describes Event transfer object
 */


@Data
@NoArgsConstructor
public class EventTo {

    private Integer id;
    private String name;
    private String description;
    private Date date;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer creatorId;
    private Integer meetingRoomId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private String stringStartTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private String stringEndTime;


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

    public void getStartAndEndTimeFromStrings(){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            java.util.Date parsedDate = dateFormat.parse(this.stringStartTime);
            startTime = new java.sql.Timestamp(parsedDate.getTime());
            parsedDate  = dateFormat.parse(this.stringEndTime);
            endTime = new java.sql.Timestamp(parsedDate.getTime());
        } catch(Exception e) { //this generic but you can control another types of exception
            // look the origin of excption
        }

    }

//    public String newEventToFromFormValidityCheck() {
//        this.getStartAndEndTimeFromStrings();
//        Calendar calendarStart = Calendar.getInstance();
//        Calendar calendarStop = Calendar.getInstance();
//        calendarStart.setTime(startTime);
//        calendarStop.setTime(endTime);
//        if(calendarStop.getTimeInMillis() < calendarStart.getTimeInMillis()) return "Start date can't be in future / End date can't be in past!";
//        if(calendarStop.getTimeInMillis() - calendarStart.getTimeInMillis() < 30 * 60 * 1000) return "Minimum time period have to be 30 minutes!";
//        if(calendarStop.getTimeInMillis() - calendarStart.getTimeInMillis() > 24 * 60 * 60 * 1000) return "Maximum time can be 24 hours!";
//        if(this.name.length() > 63) return "Max length of event name is 64 symbols. Please, change it!";
//        if(this.description.length() > 254) return "Max length of event name is 255 symbols. Please, change it!";
//        else return "passed";
//    }
}

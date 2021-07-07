package ru.VaolEr.meetingroomschedule.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @Author Valentin Eremizin
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


    public Integer getDayOfWeek() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(this.date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public Integer getHourOfDay() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(this.startTime);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public Integer getMinutesOfHour() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(this.startTime);
        return calendar.get(Calendar.MINUTE);
    }

    public String getStartAndEndTimeAsString() {
        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarStop = Calendar.getInstance();
        calendarStart.setTime(startTime);
        calendarStop.setTime(endTime);

        return String.format("%s - %s", getFormattedTimeAsString(calendarStart), getFormattedTimeAsString(calendarStop));
    }

    private String getFormattedTimeAsString(Calendar calendar) {

        String hour = (calendar.get(Calendar.HOUR_OF_DAY) < 10) ? String.format("0%d", calendar.get(Calendar.HOUR_OF_DAY)) : String.format("%d", calendar.get(Calendar.HOUR_OF_DAY));
        String minutes = (calendar.get(Calendar.MINUTE) < 10) ? String.format("0%d", calendar.get(Calendar.MINUTE)) : String.format("%d", calendar.get(Calendar.MINUTE));

        return String.format("%s:%s", hour, minutes);
    }

    public void getStartAndEndTimeFromStrings() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            java.util.Date parsedDate = dateFormat.parse(this.stringStartTime);
            startTime = new java.sql.Timestamp(parsedDate.getTime());
            parsedDate = dateFormat.parse(this.stringEndTime);
            endTime = new java.sql.Timestamp(parsedDate.getTime());
        } catch (Exception e) {
        }

    }
}

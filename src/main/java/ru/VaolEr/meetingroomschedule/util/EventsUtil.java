package ru.VaolEr.meetingroomschedule.util;

import ru.VaolEr.meetingroomschedule.dto.EventTo;
import ru.VaolEr.meetingroomschedule.model.Event;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class EventsUtil {

    public static EventTo toEventTo(Event event){
        EventTo eventTo = new EventTo();
        eventTo.setId(event.getId());
        eventTo.setName(event.getName());
        eventTo.setDescription(event.getDescription());
        eventTo.setDate(event.getEventDate().getDate());
        eventTo.setStartTime(event.getEventDate().getStartTime());
        eventTo.setEndTime(event.getEventDate().getEndTime());
        eventTo.setCreatorId(event.getCreatorUser().getId());
        eventTo.setMeetingRoomId(event.getEventDate().getMeetingRoom().getId());
        return eventTo;
    }

    public static List<EventTo> toEventTos (List<Event> events){
        return events.stream().map(EventsUtil::toEventTo).collect(Collectors.toList());
    }

    public static Event fromEventTo(EventTo eventTo){
        Event newEvent = new Event();
        newEvent.setName(eventTo.getName());
        newEvent.setDescription(eventTo.getDescription());
        return newEvent;
    }

    public static String validateEventTo(EventTo eventTo){
        eventTo.getStartAndEndTimeFromStrings();
        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarStop = Calendar.getInstance();
        calendarStart.setTime(eventTo.getStartTime());
        calendarStop.setTime(eventTo.getEndTime());
        if(calendarStop.getTimeInMillis() < calendarStart.getTimeInMillis()) return "Start date can't be in future / End date can't be in past!";
        if(calendarStop.getTimeInMillis() - calendarStart.getTimeInMillis() < 30 * 60 * 1000) return "Minimum time period have to be 30 minutes!";
        if(calendarStop.getTimeInMillis() - calendarStart.getTimeInMillis() > 24 * 60 * 60 * 1000) return "Maximum time can be 24 hours!";
        if(eventTo.getName().length() > 63) return "Max length of event name is 64 symbols. Please, change it!";
        if(eventTo.getDescription().length() > 254) return "Max length of event name is 255 symbols. Please, change it!";
        else return "passed";
    }
}

package ru.VaolEr.meetingroomschedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.VaolEr.meetingroomschedule.dto.EventTo;
import ru.VaolEr.meetingroomschedule.model.eventscalendar.EventsCalendarWeek;
import ru.VaolEr.meetingroomschedule.model.eventscalendar.EventsCalendarYear;

import javax.annotation.PostConstruct;

import java.util.HashMap;
import java.util.List;

import static ru.VaolEr.meetingroomschedule.util.EventsUtil.toEventTos;

@Service
@RequiredArgsConstructor
public class EventsCalendarService {

    private final EventsService eventsService;

    private EventsCalendarYear eventsCalendarYear;

    @PostConstruct
    public void init(){
        eventsCalendarYear = new EventsCalendarYear();
        eventsCalendarYear.insertEvents(toEventTos(eventsService.getAllEvents()));
    }

    public EventsCalendarYear getEventsCalendarYear(){
        return eventsCalendarYear;
    }

    public EventsCalendarYear getEventsCalendarYearByYear(Integer year){
        EventsCalendarYear eventsCalendarYearByYear = new EventsCalendarYear(year);
        eventsCalendarYearByYear.insertEvents(toEventTos(eventsService.getAllEvents()));
        return eventsCalendarYearByYear;
    }

    public EventsCalendarWeek getEventsCalendarWeekByNumber(Integer weekNumber){
        return eventsCalendarYear.getEventCalendarWeekByNumber(weekNumber);
    }

}

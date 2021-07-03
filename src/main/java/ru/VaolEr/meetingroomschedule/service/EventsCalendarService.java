package ru.VaolEr.meetingroomschedule.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ru.VaolEr.meetingroomschedule.model.eventscalendar.EventsCalendarWeek;
import ru.VaolEr.meetingroomschedule.model.eventscalendar.EventsCalendarYear;
import ru.VaolEr.meetingroomschedule.model.paging.Page;
import ru.VaolEr.meetingroomschedule.model.paging.Paged;
import ru.VaolEr.meetingroomschedule.model.paging.Paging;

import javax.annotation.PostConstruct;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

    public Paged<EventsCalendarWeek> getPagedEventsCalendarWeek(int weekNumber, int size){
        try {
            List<EventsCalendarWeek> listOfWeeks = eventsCalendarYear.getListOfWeeks();

            List<EventsCalendarWeek> paged = listOfWeeks.stream()
                    .skip(weekNumber)
                    .limit(size) //size = 1
                    .collect(Collectors.toList());

            int totalPages = 52; //listOfWeeks.size() / size;
            return new Paged<>(new Page<>(paged, totalPages), Paging.of(totalPages, weekNumber, size));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Paged<>();
    }
}

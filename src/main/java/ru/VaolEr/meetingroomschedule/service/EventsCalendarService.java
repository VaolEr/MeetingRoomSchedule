package ru.VaolEr.meetingroomschedule.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ru.VaolEr.meetingroomschedule.model.eventscalendar.EventsCalendarWeek;
import ru.VaolEr.meetingroomschedule.model.eventscalendar.EventsCalendarYear;
import ru.VaolEr.meetingroomschedule.model.paging.Page;
import ru.VaolEr.meetingroomschedule.model.paging.Paged;
import ru.VaolEr.meetingroomschedule.model.paging.Paging;

import javax.annotation.PostConstruct;


import java.util.List;
import java.util.stream.Collectors;

import static ru.VaolEr.meetingroomschedule.util.EventsUtil.toEventTos;

@Service
@RequiredArgsConstructor
public class EventsCalendarService {

    private final EventsService eventsService;

    private EventsCalendarYear eventsCalendarYear;

    @PostConstruct
    public void init() {
        eventsCalendarYear = new EventsCalendarYear();
        updateEvents();
    }

    public EventsCalendarYear getEventsCalendarYear() {
        return eventsCalendarYear;
    }

    public EventsCalendarYear getEventsCalendarYearByYear(Integer year) {
        EventsCalendarYear eventsCalendarYearByYear = new EventsCalendarYear(year);
        updateEvents();
        return eventsCalendarYearByYear;
    }

    public EventsCalendarWeek getEventsCalendarWeekByNumber(Integer weekNumber) {
        updateEvents();
        return eventsCalendarYear.getEventCalendarWeekByNumber(weekNumber);
    }

    public Paged<EventsCalendarWeek> getPagedEventsCalendarWeek(int weekNumber, int size) {
        try {
            updateEvents();
            List<EventsCalendarWeek> listOfWeeks = eventsCalendarYear.getListOfWeeks();

            List<EventsCalendarWeek> paged = listOfWeeks.stream()
                    .skip(weekNumber)
                    .limit(size) //size = 1
                    .collect(Collectors.toList());

            int totalPages = 52; // 52 because this count of weeks in year
            return new Paged<>(new Page<>(paged, totalPages), Paging.of(totalPages, weekNumber, size));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Paged<>();
    }

    private void updateEvents() {
        eventsCalendarYear.insertEvents(toEventTos(eventsService.getAllEvents()));
    }
}

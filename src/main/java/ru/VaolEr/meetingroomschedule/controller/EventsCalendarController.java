package ru.VaolEr.meetingroomschedule.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.VaolEr.meetingroomschedule.service.EventsCalendarService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static ru.VaolEr.meetingroomschedule.util.EventsUtil.toEventTos;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class EventsCalendarController {

    private final EventsCalendarService eventsCalendarService;

    @GetMapping
    public String eventsList(Model model){
        //log.info(eventsCalendarService.getEventsCalendarWeekByNumber(26).getEventsPerHours().get(12).toString());
        model.addAttribute("weekDates", eventsCalendarService.getEventsCalendarWeekByNumber(26).getWeekDates());
        model.addAttribute("eventsPerHoursWeek", eventsCalendarService.getEventsCalendarWeekByNumber(26).getEventsPerHours());

        return "index";
    }

}

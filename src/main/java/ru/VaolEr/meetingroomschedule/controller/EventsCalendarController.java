package ru.VaolEr.meetingroomschedule.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.VaolEr.meetingroomschedule.service.EventsCalendarService;

@Slf4j
@Controller
@RequestMapping("/timetable")
@RequiredArgsConstructor
public class EventsCalendarController {

    private final EventsCalendarService eventsCalendarService;

//    @GetMapping
//    public String eventsList(Model model){
//        //log.info(eventsCalendarService.getEventsCalendarWeekByNumber(26).getEventsPerHours().get(12).toString());
//        model.addAttribute("weekDates", eventsCalendarService.getEventsCalendarWeekByNumber(26).getWeekDates());
//        model.addAttribute("eventsPerHoursWeek", eventsCalendarService.getEventsCalendarWeekByNumber(26).getEventsPerHours());
//
//        return "index";
//    }
    @GetMapping
    public String eventsList(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                             @RequestParam(value = "size", required = false, defaultValue = "1") int size,
                             Model model){
        log.info(eventsCalendarService.getEventsCalendarWeekByNumber(26).getEventsPerHours().get(12).toString());
        model.addAttribute("weekDates", eventsCalendarService.getEventsCalendarWeekByNumber(pageNumber).getWeekDates());
        model.addAttribute("eventsPerHoursWeek", eventsCalendarService.getEventsCalendarWeekByNumber(pageNumber).getEventsPerHours());
        model.addAttribute("eventsPerHoursWeekPaged", eventsCalendarService.getPagedEventsCalendarWeek(pageNumber,1));

        return "timetable";
    }

}

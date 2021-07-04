package ru.VaolEr.meetingroomschedule.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.VaolEr.meetingroomschedule.dto.EventTo;
import ru.VaolEr.meetingroomschedule.model.User;
import ru.VaolEr.meetingroomschedule.service.EventsCalendarService;
import ru.VaolEr.meetingroomschedule.service.EventsService;
import ru.VaolEr.meetingroomschedule.service.UsersService;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

import static ru.VaolEr.meetingroomschedule.util.EventsUtil.toEventTo;

@Slf4j
@Controller
@RequestMapping("/timetable")
@RequiredArgsConstructor
public class EventsCalendarController {

    private final EventsCalendarService eventsCalendarService;
    private final EventsService eventsService;
    private final UsersService usersService;

    private Integer countOfControllerCalls = 0;

    private HashMap<String, Integer> loggedUsers = new HashMap<>();

    @GetMapping
    public String eventsList(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                             @RequestParam(value = "size", required = false, defaultValue = "1") int size,
                             Model model){

        //TODO do better current week page display after login. This method is not working correct for multiply users.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        loggedUsers.computeIfAbsent(currentUserName, k -> countOfControllerCalls);

        if(loggedUsers.get(currentUserName) == 0){
            Calendar calendar = Calendar.getInstance();
            Integer currentWeekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
            model.addAttribute("weekDates", eventsCalendarService.getEventsCalendarWeekByNumber(currentWeekNumber).getWeekDates());
            model.addAttribute("eventsPerHoursWeek", eventsCalendarService.getEventsCalendarWeekByNumber(currentWeekNumber).getPerHourEvents()); // returns map<Integer, EventTo[]>
            model.addAttribute("eventsPerHoursWeekPaged", eventsCalendarService.getPagedEventsCalendarWeek(currentWeekNumber,1));
            model.addAttribute("userName", currentUserName);
            loggedUsers.put(currentUserName, 1);
        } else{
            model.addAttribute("weekDates", eventsCalendarService.getEventsCalendarWeekByNumber(pageNumber).getWeekDates());
            model.addAttribute("eventsPerHoursWeek", eventsCalendarService.getEventsCalendarWeekByNumber(pageNumber).getPerHourEvents()); // returns map<Integer, EventTo[]>
            model.addAttribute("eventsPerHoursWeekPaged", eventsCalendarService.getPagedEventsCalendarWeek(pageNumber,1));
            model.addAttribute("userName", currentUserName);
        }

//        log.info(Arrays.toString(eventsCalendarService.getEventsCalendarWeekByNumber(26).getPerHourEvents().get(12)));
//        log.info(String.valueOf(eventsCalendarService.getEventsCalendarWeekByNumber(26).getPerHourEvents().get(12)[0].getId()));

        return "timetable";
    }

    @GetMapping("/events/{id}")
    public String getEventDetailInfo(@PathVariable("id") int eventId, Model model){
        EventTo eventTo = toEventTo(eventsService.getById(eventId));
        model.addAttribute("eventName", eventTo.getName());
        model.addAttribute("eventDescription", eventTo.getDescription());
        model.addAttribute("startTime", eventTo.getStartTime());
        model.addAttribute("endTime", eventTo.getEndTime());
        User user = usersService.getById(eventTo.getCreatorId());
        model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        return "eventInfo";
    }

}

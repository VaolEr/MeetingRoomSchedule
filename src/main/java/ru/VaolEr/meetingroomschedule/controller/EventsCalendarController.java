package ru.VaolEr.meetingroomschedule.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.VaolEr.meetingroomschedule.dto.EventTo;
import ru.VaolEr.meetingroomschedule.model.User;
import ru.VaolEr.meetingroomschedule.service.EventsCalendarService;
import ru.VaolEr.meetingroomschedule.service.EventsService;
import ru.VaolEr.meetingroomschedule.service.UsersService;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;

import static ru.VaolEr.meetingroomschedule.util.EventsUtil.toEventTo;
import static ru.VaolEr.meetingroomschedule.util.EventsUtil.validateEventTo;

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

    @GetMapping("/newEvent")
    public String getNewEventPage(Model model){
        model.addAttribute("event", new EventTo());
        return "newEvent";
    }

    @PostMapping("/newEvent")
    public String getNewEvent(EventTo event, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName().split("#")[0];
        Integer currentUserId = Integer.valueOf(authentication.getName().split("#")[1]);
//        log.info(currentUserName +" /--/ " + currentUserId);
        String result;
        if(eventsService.getOverlapsedEventIds(event).size() != 0) result = "There is another event in selected time period. Pick new time, please!";
        result =  validateEventTo(event);
        //TODO add validation with database existing events.
//        log.info(event.getName()+ " " + result + " " + event.getStartTime() + " " + event.getEndTime());
//        log.info(String.valueOf(eventsService.getOverlapsedEventIds(event)));
        event.setCreatorId(currentUserId);
        event.setDate(new java.sql.Date(event.getStartTime().getTime()));
        event.setMeetingRoomId(1);
//        log.info(event.toString());

        if(result.equals("passed")){
            eventsService.create(event);
            return "redirect:/timetable?pageNumber="+Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        } else {
            model.addAttribute("description", result);
            return "newEventValidateErrorPage";
        }
    }

}

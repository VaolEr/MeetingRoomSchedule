package ru.VaolEr.meetingroomschedule.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import ru.VaolEr.meetingroomschedule.dto.EventTo;
import ru.VaolEr.meetingroomschedule.model.Event;
import ru.VaolEr.meetingroomschedule.model.EventDate;
import ru.VaolEr.meetingroomschedule.model.MeetingRoom;
import ru.VaolEr.meetingroomschedule.model.User;
import ru.VaolEr.meetingroomschedule.service.EventsDatesService;
import ru.VaolEr.meetingroomschedule.service.EventsService;
import ru.VaolEr.meetingroomschedule.service.MeetingsRoomsService;
import ru.VaolEr.meetingroomschedule.service.UsersService;

import javax.annotation.PostConstruct;
import java.util.List;

import static ru.VaolEr.meetingroomschedule.util.EventsUtil.toEventTos;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TestController {

    private final UsersService usersService;
    private final EventsService eventsService;
    private final EventsDatesService eventsDatesService;
    private final MeetingsRoomsService meetingsRoomsService;

    @PostConstruct
    void test(){
        Event event = eventsService.getById(1);
        User user = usersService.getById(1);
        EventDate eventDate = eventsDatesService.getById(1);
        MeetingRoom meetingRoom = meetingsRoomsService.getById(1);
        List<EventTo> eventTos = toEventTos(eventsService.getAllEvents());
        log.info(event.toString());
        log.info(user.toString());

        log.info(eventDate.toString());
        log.info(meetingRoom.toString());
        log.info("--------->>> " + String.valueOf(eventTos.get(0).getDayOfWeek()) + " " + eventTos.get(0).getHourOfDay());
    }

}

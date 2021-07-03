package ru.VaolEr.meetingroomschedule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.VaolEr.meetingroomschedule.service.EventsService;

import static ru.VaolEr.meetingroomschedule.util.EventsUtil.toEventTos;

//@Slf4j
//@Controller
//@RequestMapping("/")
@RequiredArgsConstructor
public class EventController {

    private final EventsService eventsService;

    @GetMapping
    public String eventsList(Model model){
        model.addAttribute("eventsTos", toEventTos(eventsService.getAllEvents()));
        return "timetable";
    }
}

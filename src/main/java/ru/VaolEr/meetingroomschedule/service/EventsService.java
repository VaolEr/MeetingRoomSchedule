package ru.VaolEr.meetingroomschedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.VaolEr.meetingroomschedule.model.Event;
import ru.VaolEr.meetingroomschedule.repository.EventsRepository;

import java.util.List;

import static ru.VaolEr.meetingroomschedule.util.ValidationUtil.addMessageDetails;
import static ru.VaolEr.meetingroomschedule.util.ValidationUtil.checkNotFound;

@Service
@RequiredArgsConstructor
public class EventsService {

    private final EventsRepository eventsRepository;

    public Event getById(Integer eventId){
        return checkNotFound(eventsRepository.findById(eventId),
                addMessageDetails(Event.class.getSimpleName(), eventId));
    }

    public List<Event> getAllEvents(){
        return eventsRepository.findAll();
    }
}

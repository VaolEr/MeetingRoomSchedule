package ru.VaolEr.meetingroomschedule.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.VaolEr.meetingroomschedule.dto.EventTo;
import ru.VaolEr.meetingroomschedule.model.Event;
import ru.VaolEr.meetingroomschedule.model.EventDate;
import ru.VaolEr.meetingroomschedule.model.MeetingRoom;
import ru.VaolEr.meetingroomschedule.model.User;
import ru.VaolEr.meetingroomschedule.repository.EventsDatesRepository;
import ru.VaolEr.meetingroomschedule.repository.EventsRepository;
import ru.VaolEr.meetingroomschedule.repository.MeetingRoomsRepository;
import ru.VaolEr.meetingroomschedule.repository.UsersRepository;

import java.util.List;

import static ru.VaolEr.meetingroomschedule.util.EventsUtil.fromEventTo;
import static ru.VaolEr.meetingroomschedule.util.ValidationUtil.addMessageDetails;
import static ru.VaolEr.meetingroomschedule.util.ValidationUtil.checkNotFound;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventsService {

    private final EventsRepository eventsRepository;
    private final UsersRepository usersRepository;
    private final MeetingRoomsRepository meetingRoomsRepository;
    private final EventsDatesRepository eventsDatesRepository;

    public Event getById(Integer eventId){
        return checkNotFound(eventsRepository.findById(eventId),
                addMessageDetails(Event.class.getSimpleName(), eventId));
    }

    public List<Event> getAllEvents(){
        return eventsRepository.findAll();
    }

    public List<Integer> getOverlapsedEventIds(EventTo event){
        return eventsRepository.checkOverlapsBetweenDatesAndReturnEventIds(event.getStartTime(), event.getEndTime());
    }

    public Event create(EventTo eventTo){

        EventDate eventDate = new EventDate();
        eventDate.setDate(eventTo.getDate());
        eventDate.setStartTime(eventTo.getStartTime());
        eventDate.setEndTime(eventTo.getEndTime());
        eventDate.setMeetingRoom(checkNotFound(meetingRoomsRepository.findById(eventTo.getMeetingRoomId()), addMessageDetails(MeetingRoom.class.getSimpleName(), eventTo.getMeetingRoomId())));
//        eventsDatesRepository.save(eventDate);

        Event event = fromEventTo(eventTo);
        event.setCreatorUser(checkNotFound(usersRepository.findById(eventTo.getCreatorId()), addMessageDetails(User.class.getSimpleName(), eventTo.getCreatorId())));
//        event.setEventDate(eventDate);
        Event savedEvent = eventsRepository.save(event);
//        log.info(savedEvent.toString());
        eventDate.setEvent(savedEvent);
        eventsDatesRepository.save(eventDate);
        return savedEvent;
    }
}

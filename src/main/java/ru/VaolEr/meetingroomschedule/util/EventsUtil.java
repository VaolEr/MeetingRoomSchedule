package ru.VaolEr.meetingroomschedule.util;

import ru.VaolEr.meetingroomschedule.dto.EventTo;
import ru.VaolEr.meetingroomschedule.model.Event;

import java.util.List;
import java.util.stream.Collectors;

public class EventsUtil {

    public static EventTo toEventTo(Event event){
        return EventTo.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .date(event.getEventDate().getDate())
                .startTime(event.getEventDate().getStartTime())
                .endTime(event.getEventDate().getEndTime())
                .creatorId(event.getCreatorUser().getId())
                .meetingRoomId(event.getEventDate().getMeetingRoom().getId())
                .build();
    }

    public static List<EventTo> toEventTos (List<Event> events){
        return events.stream().map(EventsUtil::toEventTo).collect(Collectors.toList());
    }

    public static Event fromEventTo(EventTo eventTo){
        Event newEvent = new Event();
        newEvent.setName(eventTo.getName());
        newEvent.setDescription(eventTo.getDescription());
        return newEvent;
    }
}

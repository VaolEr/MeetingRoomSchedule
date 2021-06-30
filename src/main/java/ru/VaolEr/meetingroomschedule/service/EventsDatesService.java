package ru.VaolEr.meetingroomschedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.VaolEr.meetingroomschedule.model.EventDate;
import ru.VaolEr.meetingroomschedule.repository.EventsDatesRepository;

import static ru.VaolEr.meetingroomschedule.util.ValidationUtil.addMessageDetails;
import static ru.VaolEr.meetingroomschedule.util.ValidationUtil.checkNotFound;

@Service
@RequiredArgsConstructor
public class EventsDatesService {

    private final EventsDatesRepository eventsDatesRepository;

    public EventDate getById(Integer eventDateId){
        return checkNotFound(eventsDatesRepository.findById(eventDateId),
                addMessageDetails(EventDate.class.getSimpleName(), eventDateId));
    }
}

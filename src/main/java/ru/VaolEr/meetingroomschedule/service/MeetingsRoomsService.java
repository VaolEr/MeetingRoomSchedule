package ru.VaolEr.meetingroomschedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.VaolEr.meetingroomschedule.model.MeetingRoom;
import ru.VaolEr.meetingroomschedule.repository.MeetingRoomsRepository;

import static ru.VaolEr.meetingroomschedule.util.ValidationUtil.addMessageDetails;
import static ru.VaolEr.meetingroomschedule.util.ValidationUtil.checkNotFound;

@Service
@RequiredArgsConstructor
public class MeetingsRoomsService {

    private final MeetingRoomsRepository meetingRoomsRepository;

    public MeetingRoom getById(Integer meetingRoomId){
        return checkNotFound(meetingRoomsRepository.findById(meetingRoomId),
                addMessageDetails(MeetingRoom.class.getSimpleName(), meetingRoomId));
    }

}

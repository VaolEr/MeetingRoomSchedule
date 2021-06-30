package ru.VaolEr.meetingroomschedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.VaolEr.meetingroomschedule.dto.UserTo;
import ru.VaolEr.meetingroomschedule.model.User;
import ru.VaolEr.meetingroomschedule.repository.UsersRepository;

import static ru.VaolEr.meetingroomschedule.util.UsersUtil.fromUserTo;
import static ru.VaolEr.meetingroomschedule.util.UsersUtil.prepareToSave;
import static ru.VaolEr.meetingroomschedule.util.ValidationUtil.*;


@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public User getById(Integer userId){
        return checkNotFound(usersRepository.findById(userId),
                addMessageDetails(User.class.getSimpleName(), userId));
    }

    @Transactional
    public User create(UserTo userTo) {
        User newUser = fromUserTo(userTo);
        return usersRepository.save(prepareToSave(newUser));
    }

    @Transactional
    public User update(UserTo userTo, Integer id) {
        User updatedUser = fromUserTo(userTo);
        //TODO переделать проверку через HasId
        assureIdConsistent(updatedUser, id);
        return usersRepository.save(prepareToSave(updatedUser));
    }

    @Transactional
    public void delete(Integer id) {
        usersRepository.deleteById(id);
    }
}

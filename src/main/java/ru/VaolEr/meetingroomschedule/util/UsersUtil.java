package ru.VaolEr.meetingroomschedule.util;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.VaolEr.meetingroomschedule.dto.UserTo;
import ru.VaolEr.meetingroomschedule.model.User;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;

public class UsersUtil {

    public static final PasswordEncoder PASSWORD_ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public static UserTo toUserTo(User user) {
        return UserTo
                .builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }

    public static List<UserTo> toUserTos(List<User> users) {
        return users.stream().map(UsersUtil::toUserTo).collect(Collectors.toList());
    }

    public static User fromUserTo(UserTo userTo) {
        User newUser = new User();
        newUser.setId(userTo.getId());
        newUser.setFirstName(userTo.getFirstName());
        newUser.setLastName(userTo.getLastName());
        newUser.setLogin(userTo.getLogin());
        newUser.setPassword(userTo.getPassword());
        return newUser;
    }

    public static User prepareToSave(User user) {
        String password = user.getPassword();
        user.setLogin(user.getLogin());
        //user.setPassword(hasText(password) ? PASSWORD_ENCODER.encode(password) : password);
        user.setPassword(password);
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        return user;
    }
}

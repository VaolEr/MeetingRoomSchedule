package ru.VaolEr.meetingroomschedule.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.VaolEr.meetingroomschedule.model.User;
import ru.VaolEr.meetingroomschedule.repository.UsersRepository;

@Service("userDetailsServiceImplementation")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserDetailsServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = usersRepository.findByLogin(login).orElseThrow(() ->
                new UsernameNotFoundException("User does not exists"));
        return SecurityUser.fromUser(user);
    }
}

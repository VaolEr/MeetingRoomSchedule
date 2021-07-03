package ru.VaolEr.meetingroomschedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.VaolEr.meetingroomschedule.model.User;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UsersRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);
}

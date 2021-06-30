package ru.VaolEr.meetingroomschedule.model;

import lombok.NoArgsConstructor;
import ru.VaolEr.meetingroomschedule.model.abstractentity.AbstractBaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
@Table(name = "Users")
@NoArgsConstructor
public class User extends AbstractBaseEntity {

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "login")
    private String login;

    // TODO change password from String to array of char
    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "creatorUser")
    private List<Event> events; //as event_id

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<Event> getEvents() {
        return events;
    }

    public User setEvents(List<Event> eventList) {
        this.events = eventList;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", eventList=" + events.size() +
                '}';
    }
}

package ru.VaolEr.meetingroomschedule.model;

import lombok.NoArgsConstructor;
import ru.VaolEr.meetingroomschedule.model.abstractentity.AbstractEntity;
import ru.VaolEr.meetingroomschedule.model.abstractentity.AbstractNamedEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
@Table(name = "Users")
@NoArgsConstructor
public class User extends AbstractEntity {

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "login")
    private String login;

    // TODO change password from String to array of char
    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Event> eventList; //as event_id

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

    public List<Event> getEventList() {
        return eventList;
    }

    public User setEventList(List<Event> eventList) {
        this.eventList = eventList;
        return this;
    }
}

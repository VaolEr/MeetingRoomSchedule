package ru.VaolEr.meetingroomschedule.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserTo {

    private Integer id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;

}

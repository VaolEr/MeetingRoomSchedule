-- liquibase formatted sql
-- Format: --changeset author:id attribute1:value1 attribute2:value2 [...]

-- changeset VaolEr:Create_Users_table
CREATE TABLE Users
(
    id                  integer PRIMARY KEY NOT NULL,
    firstname           VARCHAR(64) NOT NULL,
    lastname            VARCHAR(64) NOT NULL,
    login               VARCHAR(255) NOT NULL,
    password            VARCHAR(255) NOT NULL
);

-- changeset VaolEr:Create_Events_table
CREATE TABLE Events
(
    id                  integer PRIMARY KEY NOT NULL,
    name                VARCHAR(64) NOT NULL,
    description         VARCHAR(255),
    creatorUser_id      integer NOT NULL
);

-- changeset VaolEr:Create_MeetingRooms_table
CREATE TABLE MeetingRooms
(
    id                  integer PRIMARY KEY NOT NULL,
    name                VARCHAR(64) NOT NULL
);

-- changeset VaolEr:Create_Events_ScheduleDates_table
CREATE TABLE Events_ScheduleDates
(
    id                  integer PRIMARY KEY NOT NULL,
    scheduleDate        date NOT NULL,
    event_id            integer NOT NULL,
    startTime           timetz,
    endTime             timetz,
    meetingRoom_id      integer NOT NULL
);

-- changeset VaolEr:Create_ScheduleDates_table
CREATE TABLE ScheduleDates
(
    scheduleDate        date PRIMARY KEY NOT NULL
);

-- changeset VaolEr:Alter_Events_table_fks
ALTER TABLE Events
    ADD CONSTRAINT Events_fk0 FOREIGN KEY (creatorUser_id) REFERENCES Users (id);

-- changeset VaolEr:Alter_Events_ScheduleDates_table_fks
ALTER TABLE Events_ScheduleDates
    ADD CONSTRAINT Events_ScheduleDates_fk0 FOREIGN KEY (event_id) REFERENCES Events (id);
ALTER TABLE Events_ScheduleDates
    ADD CONSTRAINT Events_ScheduleDates_fk1 FOREIGN KEY (scheduleDate) REFERENCES ScheduleDates (scheduleDate);
ALTER TABLE Events_ScheduleDates
    ADD CONSTRAINT Events_ScheduleDates_fk2 FOREIGN KEY (meetingRoom_id) REFERENCES MeetingRooms (id);

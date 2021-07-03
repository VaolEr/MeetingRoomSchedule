-- liquibase formatted sql
-- Format: --changeset author:id attribute1:value1 attribute2:value2 [...]

-- changeset VaolEr:Add_monday_events_to_events_table
INSERT INTO events (name, description, creatoruser_id)
VALUES ('JohnDoe Monday event', 'Description text for JohnDoe Monday event.', (SELECT id FROM users WHERE login = 'JohnDoe')),
       ('Admin Monday event', 'Description text for Admin Monday event.', (SELECT id FROM users WHERE login = 'Admin')),
       ('User Monday event', 'Description text for User Monday event.', (SELECT id FROM users WHERE login = 'User'));

-- changeset VaolEr:Add_monday_events_dates_to_events_dates_table
INSERT INTO events_dates (date, event_id, starttime, endtime, meetingroom_id)
VALUES ('2021-06-28', (SELECT id FROM events WHERE name = 'JohnDoe Monday event'),'2021-06-28 12:15:00','2021-06-30 12:45:00',(SELECT id FROM meetingrooms WHERE name = 'Test Room')),
       ('2021-06-28', (SELECT id FROM events WHERE name = 'Admin Monday event'),'2021-06-28 08:00:00','2021-06-30 08:45:00',(SELECT id FROM meetingrooms WHERE name = 'Test Room')),
       ('2021-06-28', (SELECT id FROM events WHERE name = 'User Monday event'),'2021-07-28 13:30:00','2021-07-02 13:30:00',(SELECT id FROM meetingrooms WHERE name = 'Test Room'));

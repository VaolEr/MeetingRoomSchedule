-- liquibase formatted sql
-- Format: --changeset author:id attribute1:value1 attribute2:value2 [...]

-- changeset VaolEr:Add_friday_and_saturday_events_to_events_table
INSERT INTO events (name, description, creatoruser_id)
VALUES ('JohnDoe Friday event', 'Description text for JohnDoe Monday event.', (SELECT id FROM users WHERE login = 'JohnDoe')),
       ('Admin Friday event', 'Description text for Admin Monday event.', (SELECT id FROM users WHERE login = 'Admin')),
       ('User Friday event', 'Description text for User Monday event.', (SELECT id FROM users WHERE login = 'User')),
       ('JohnDoe Saturday event', 'Description text for JohnDoe Monday event.', (SELECT id FROM users WHERE login = 'JohnDoe')),
       ('Admin Saturday event', 'Description text for Admin Monday event.', (SELECT id FROM users WHERE login = 'Admin')),
       ('User Saturday event', 'Description text for User Monday event.', (SELECT id FROM users WHERE login = 'User'));

-- changeset VaolEr:Add_friday_and_saturday_events_dates_to_events_dates_table
INSERT INTO events_dates (date, event_id, starttime, endtime, meetingroom_id)
VALUES ('2021-07-02', (SELECT id FROM events WHERE name = 'JohnDoe Friday event'),'2021-07-02 06:15:00','2021-07-02 07:45:00',(SELECT id FROM meetingrooms WHERE name = 'Test Room')),
       ('2021-07-02', (SELECT id FROM events WHERE name = 'Admin Friday event'),'2021-07-02 08:00:00','2021-07-02 08:50:00',(SELECT id FROM meetingrooms WHERE name = 'Test Room')),
       ('2021-07-02', (SELECT id FROM events WHERE name = 'User Friday event'),'2021-07-02 19:30:00','2021-07-02 21:00:00',(SELECT id FROM meetingrooms WHERE name = 'Test Room')),
       ('2021-07-03', (SELECT id FROM events WHERE name = 'JohnDoe Saturday event'),'2021-07-03 05:15:00','2021-07-03 07:45:00',(SELECT id FROM meetingrooms WHERE name = 'Test Room')),
       ('2021-07-03', (SELECT id FROM events WHERE name = 'Admin Saturday event'),'2021-07-03 07:00:00','2021-07-03 08:50:00',(SELECT id FROM meetingrooms WHERE name = 'Test Room')),
       ('2021-07-03', (SELECT id FROM events WHERE name = 'User Saturday event'),'2021-07-03 16:30:00','2021-07-03 21:00:00',(SELECT id FROM meetingrooms WHERE name = 'Test Room'));

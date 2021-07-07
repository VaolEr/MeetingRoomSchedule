-- liquibase formatted sql
-- Format: --changeset author:id attribute1:value1 attribute2:value2 [...]

-- changeset VaolEr:Populate_users_table
INSERT INTO users (firstname, lastname, login, password)
VALUES ('John', 'Doe', 'JohnDoe','{noop}JohnDoe-password'),
       ('AdminFN', 'AdminLN', 'Admin','{noop}admin-password'),
       ('UserFN', 'UserLN', 'User','{noop}user-password');
-- Real passwords be encrypted by Bcrypt with 12 passes https://bcrypt-generator.com/.

-- changeset VaolEr:Populate_meetingrooms_table
INSERT INTO meetingrooms(name)
VALUES ('Test Room');

-- changeset VaolEr:Populate_events_table
INSERT INTO events (name, description, creatoruser_id)
VALUES ('JohnDoe First event', 'Description text for JohnDoe first event.', (SELECT id FROM users WHERE login = 'JohnDoe')),
       ('JohnDoe Second event', 'Description text for JohnDoe second event.', (SELECT id FROM users WHERE login = 'JohnDoe')),
       ('JohnDoe Third event', 'Description text for JohnDoe third event.', (SELECT id FROM users WHERE login = 'JohnDoe')),
       ('Admin First event', 'Description text for Admin first event.', (SELECT id FROM users WHERE login = 'Admin')),
       ('Admin Second event', 'Description text for Admin second event.', (SELECT id FROM users WHERE login = 'Admin')),
       ('Admin Third event', 'Description text for Admin third event.', (SELECT id FROM users WHERE login = 'Admin')),
       ('Admin Fourth event', 'Description text for Admin fourth event.', (SELECT id FROM users WHERE login = 'Admin')),
       ('User First event', 'Description text for User first event.', (SELECT id FROM users WHERE login = 'User')),
       ('User Second event', 'Description text for User second event.', (SELECT id FROM users WHERE login = 'User')),
       ('User third event', 'Description text for User third event.', (SELECT id FROM users WHERE login = 'User'));

-- changeset VaolEr:Populate_events_dates_table
INSERT INTO events_dates (date, event_id, starttime, endtime, meetingroom_id)
VALUES ('2021-06-30', (SELECT id FROM events WHERE name = 'Admin First event'),'2021-06-30 08:00:00','2021-06-30 08:45:00',(SELECT id FROM meetingrooms WHERE name = 'Test Room')),
       ('2021-06-30', (SELECT id FROM events WHERE name = 'Admin Third event'),'2021-06-30 09:35:00','2021-06-30 10:05:00',(SELECT id FROM meetingrooms WHERE name = 'Test Room')),
       ('2021-06-30', (SELECT id FROM events WHERE name = 'JohnDoe Second event'),'2021-06-30 10:45:00','2021-06-30 11:45:00',(SELECT id FROM meetingrooms WHERE name = 'Test Room')),
       ('2021-06-30', (SELECT id FROM events WHERE name = 'JohnDoe First event'),'2021-06-30 12:15:00','2021-06-30 12:45:00',(SELECT id FROM meetingrooms WHERE name = 'Test Room')),
       ('2021-06-30', (SELECT id FROM events WHERE name = 'User First event'),'2021-06-30 13:30:00','2021-06-30 15:30:00',(SELECT id FROM meetingrooms WHERE name = 'Test Room')),
       ('2021-06-30', (SELECT id FROM events WHERE name = 'User Second event'),'2021-06-30 18:30:00','2021-06-30 19:30:00',(SELECT id FROM meetingrooms WHERE name = 'Test Room')),
       ('2021-06-30', (SELECT id FROM events WHERE name = 'User third event'),'2021-06-30 19:40:00','2021-06-30 21:45:00',(SELECT id FROM meetingrooms WHERE name = 'Test Room')),
       ('2021-07-01', (SELECT id FROM events WHERE name = 'Admin Second event'),'2021-07-01 11:15:00','2021-07-01 11:45:00',(SELECT id FROM meetingrooms WHERE name = 'Test Room')),
       ('2021-07-01', (SELECT id FROM events WHERE name = 'JohnDoe Third event'),'2021-07-01 12:15:00','2021-07-01 12:45:00',(SELECT id FROM meetingrooms WHERE name = 'Test Room')),
       ('2021-07-01', (SELECT id FROM events WHERE name = 'Admin Fourth event'),'2021-07-01 12:50:00','2021-07-01 13:20:00',(SELECT id FROM meetingrooms WHERE name = 'Test Room'));

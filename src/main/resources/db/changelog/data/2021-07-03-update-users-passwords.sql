-- liquibase formatted sql
-- Format: --changeset author:id attribute1:value1 attribute2:value2 [...]

-- changeset VaolEr:Update_users_passwords_in_users_table
UPDATE users SET password = '$2y$12$2TI728DD.0W.2LF7AWvc/ewzpWL.TP1UGmcko/oM5/DxJMw5imUXa' where password = '{noop}admin-password';
UPDATE users SET password = '$2y$12$q8zwqS9F8Ffpsy/B0h1E1.uuEYaE5MyUQk6PPVIsf0VSELVD9QKr2' where password = '{noop}user-password';
UPDATE users SET password = '$2y$12$aTxX6kBJJ1RwnHTRcgjK2.U0Yhc6PLg/fZT14kGl3.wFc4XQGDPdq' where password = '{noop}JohnDoe-password';



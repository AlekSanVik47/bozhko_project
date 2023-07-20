CREATE TABLE user_announcement (
  user_id smallint NOT NULL,
  announcement_id smallint NOT NULL
);
ALTER TABLE user_announcement ADD CONSTRAINT FK_USER_ANNOUNCEMENT_TBL_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id);
ALTER TABLE user_announcement ADD CONSTRAINT FK_ANNOUNCEMENT_TBL_ON_ANNOUNCEMENT FOREIGN KEY (announcement_id) REFERENCES announcement(id);

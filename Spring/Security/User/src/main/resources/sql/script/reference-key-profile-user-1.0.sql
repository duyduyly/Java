ALTER TABLE profile
    ADD CONSTRAINT fk_profile_user
        FOREIGN KEY (user_id) REFERENCES user(id);
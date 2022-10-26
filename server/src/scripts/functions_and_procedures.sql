CREATE OR REPLACE PROCEDURE add_sub_to_user(user_id BIGINT, sub_id BIGINT)
    LANGUAGE plpgsql AS
$$
BEGIN
    UPDATE uzer
    SET sub_start = current_timestamp,
        id_sub    = sub_id
    WHERE (id = user_id);
END
$$;

CREATE OR REPLACE PROCEDURE get_song_for_users()
    LANGUAGE plpgsql AS
$$
BEGIN
    SELECT * FROM song
    where (id_admin IS NOT NULL);
END
$$;

CREATE OR REPLACE PROCEDURE get_song_for_admins()
    LANGUAGE plpgsql AS
$$
BEGIN
    SELECT * FROM song
    where (id_admin IS NULL);
END
$$;

CREATE OR REPLACE PROCEDURE add_song_to_user_playlist(user_id INTEGER, song_id INTEGER)
    LANGUAGE plpgsql AS
$$
BEGIN
    INSERT INTO uzer_play_list(id_uzer, id_song)
    VALUES (user_id, song_id);
END
$$;

CREATE OR REPLACE PROCEDURE reset_user_sub(user_id INTEGER)
    LANGUAGE plpgsql AS
$$
BEGIN
    UPDATE uzer
    SET id_sub = null,
        sub_start = null
    WHERE id = user_id;
END
$$;

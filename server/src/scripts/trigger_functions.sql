CREATE OR REPLACE FUNCTION set_album_type_after_insert() RETURNS trigger AS
$$
declare
    COUNT INT;
BEGIN
    SELECT COUNT(*)
    INTO COUNT
    FROM song
    WHERE id_album = NEW.id_album;
    UPDATE album SET type = 'Сингл' WHERE (id = NEW.id_album AND COUNT = 1);
    UPDATE album SET type = 'EP' WHERE (id = NEW.id_album AND COUNT > 1 AND COUNT <= 4);
    UPDATE album SET type = 'Альбом' WHERE (id = NEW.id_album AND COUNT > 4);
    return NEW;
END
$$
    LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION set_album_type_after_delete() RETURNS trigger AS
$$
declare
    COUNT INT;
BEGIN
    SELECT COUNT(*)
    INTO COUNT
    FROM song
    WHERE id_album = OLD.id_album;
    UPDATE album SET type = 'Сингл' WHERE (id = OLD.id_album AND COUNT = 1);
    UPDATE album SET type = 'EP' WHERE (id = OLD.id_album AND COUNT > 1 AND COUNT <= 4);
    UPDATE album SET type = 'Альбом' WHERE (id = OLD.id_album AND COUNT > 4);
    return OLD;
END
$$
    LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION set_song_add_date() RETURNS trigger AS
$$
BEGIN
    UPDATE song SET last_change = current_timestamp WHERE (id = NEW.id);
    return NEW;
END
$$
    LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION check_admin_is_not_updated() RETURNS trigger AS
$$
BEGIN
    IF (NEW.id_admin != OLD.id_admin)
    then
        RAISE EXCEPTION 'Невозможно изменить поле id_admin у объекта song!';
    end if;
    RETURN NEW;
END
$$
    LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION check_artist_does_not_have_album_with_that_name() RETURNS trigger AS
$$
BEGIN

    IF (SELECT name
        FROM album
        WHERE album.id = NEW.id_album) IN (SELECT name
                                           FROM artists_albums
                                                    INNER JOIN album on album.id = artists_albums.id_album
                                           WHERE id_artist = NEW.id_artist)
    THEN
        RAISE EXCEPTION 'У исполнителя % уже есть альбом с таким названием.', NEW.id_artist;
    end if;
    RETURN NEW;
END
$$
    LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION check_user_has_sub() RETURNS trigger AS
$$
BEGIN
    IF ((SELECT id_sub
         FROM uzer
         WHERE id = NEW.id_uzer) IS NULL)
        OR
       ((SELECT sub_start
         FROM uzer
         WHERE id = NEW.id_uzer) + interval '30 days' < current_timestamp)
    then
        RAISE EXCEPTION 'У пользователя нет подписки.';
    end if;
    RETURN NEW;
END
$$
    LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION correct_update_user_sub() RETURNS trigger AS
$$
BEGIN
    --Юзер обновил подписку. обновляем и дату подписки
    IF (NEW.id_sub != OLD.id_sub) AND (NEW.id_sub IS NOT NULL)
    then
        NEW.sub_start = current_timestamp;
        RETURN NEW;
    end if;

    -- Юзеру удалили подписку. удаляем и дату.
    IF (NEW.id_sub IS NULL)
    then
        NEW.sub_start := null;
        RETURN NEW;
    end if;

    -- юзеру удалили дату подписки. удаляем и подписку
    IF (NEW.sub_start is NULL)
    then
        NEW.id_sub = null;
        RETURN NEW;
    end if;

    RETURN NEW;
END
$$
    LANGUAGE plpgsql;
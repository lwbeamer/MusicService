DROP TRIGGER IF EXISTS set_album_type ON song;
CREATE TRIGGER set_album_type
    AFTER INSERT OR DELETE
    ON song
    FOR EACH ROW EXECUTE PROCEDURE set_album_type();


DROP TRIGGER IF EXISTS set_song_add_date ON song;
CREATE TRIGGER set_song_add_date
    AFTER INSERT
    ON song
    FOR EACH ROW EXECUTE PROCEDURE set_song_add_date();


DROP TRIGGER IF EXISTS check_admin_is_not_updated ON song;
CREATE TRIGGER check_admin_is_not_updated
    BEFORE UPDATE
    ON song
    FOR EACH ROW EXECUTE PROCEDURE check_admin_is_not_updated();


DROP TRIGGER IF EXISTS check_artist_does_not_have_album_with_that_name ON artists_albums;
CREATE TRIGGER check_artist_does_not_have_album_with_that_name
    BEFORE INSERT
    ON artists_albums
    FOR EACH ROW EXECUTE PROCEDURE check_artist_does_not_have_album_with_that_name();

DROP TRIGGER IF EXISTS check_user_has_sub ON uzerplaylist;
CREATE TRIGGER check_user_has_sub
    BEFORE INSERT
    ON uzerplaylist
    FOR EACH ROW EXECUTE PROCEDURE check_user_has_sub();


DROP TRIGGER IF EXISTS correct_update_user_sub ON uzer;
CREATE TRIGGER correct_update_user_sub
    BEFORE UPDATE
    ON uzer
    FOR EACH ROW EXECUTE PROCEDURE correct_update_user_sub();


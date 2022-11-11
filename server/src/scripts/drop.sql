drop table admin, song, artists_song,
    genre, artists_albums, artist, album,
    uzer, organisation, country, subscription,roles,user_albums,uzer_albums_songs;
-- drop routine add_song_to_user_playlist(user_id integer, song_id integer);
drop routine  set_song_add_date() cascade;
drop routine set_album_type_after_insert() cascade;
drop routine set_album_type_after_delete() cascade;
-- drop routine reset_user_sub(user_id integer);
-- drop routine get_artist_album(artist_id bigint, albumname varchar);
drop routine correct_update_user_sub() cascade;
drop routine check_user_has_sub() cascade;
drop routine check_artist_does_not_have_album_with_that_name() cascade;
drop routine check_admin_is_not_updated() cascade;
-- drop routine add_sub_to_user(user_id bigint, sub_id bigint);
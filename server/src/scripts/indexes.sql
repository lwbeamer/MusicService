CREATE INDEX Album_idx ON song USING hash(id_album);

CREATE INDEX Sub_idx ON uzer USING hash(id_sub);
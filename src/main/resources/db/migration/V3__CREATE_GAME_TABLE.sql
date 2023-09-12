CREATE TABLE game
(
    id                  BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    player_character_id BIGINT NOT NULL,
    cpu_character_id    BIGINT NOT NULL,
    CONSTRAINT pk_game PRIMARY KEY (id)
);

ALTER TABLE game
    ADD CONSTRAINT FK_GAME_ON_CPUCHARACTER FOREIGN KEY (cpu_character_id) REFERENCES character (id);

ALTER TABLE game
    ADD CONSTRAINT FK_GAME_ON_PLAYERCHARACTER FOREIGN KEY (player_character_id) REFERENCES character (id);
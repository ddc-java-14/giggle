CREATE TABLE IF NOT EXISTS `user`
(
    `user_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `created` INTEGER                           NOT NULL,
    `name`    TEXT,
    `oauth`   TEXT
);

CREATE INDEX IF NOT EXISTS `index_user_created` ON `user` (`created`);

CREATE INDEX IF NOT EXISTS `index_user_name` ON `user` (`name`);

CREATE INDEX IF NOT EXISTS `index_user_oauth` ON `user` (`oauth`);

CREATE TABLE IF NOT EXISTS `joke`
(
    `joke_id`     INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `created`     INTEGER,
    `search_word` TEXT                              NOT NULL,
    `content`     TEXT,
    `user_id`     INTEGER,
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS `index_joke_created` ON `joke` (`created`);

CREATE INDEX IF NOT EXISTS `index_joke_search_word` ON `joke` (`search_word`);

CREATE INDEX IF NOT EXISTS `index_joke_content` ON `joke` (`content`);

CREATE INDEX IF NOT EXISTS `index_joke_user_id` ON `joke` (`user_id`);


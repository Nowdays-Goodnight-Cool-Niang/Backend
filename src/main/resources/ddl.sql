DROP DATABASE IF EXISTS share_me;

CREATE DATABASE share_me;

USE share_me;

CREATE TABLE `accounts`
(
    `account_id`    BIGINT PRIMARY KEY NOT NULL,
    `name`          VARCHAR(255)       NULL,
    `email`         VARCHAR(320)       NULL,
    `linkedin_url`  VARCHAR(255)       NULL,
    `github_url`    VARCHAR(255)       NULL,
    `instagram_url` VARCHAR(255)       NULL
);

CREATE TABLE `found_accounts`
(
    `first_account_id`  BIGINT NOT NULL,
    `second_account_id` BIGINT NOT NULL,
    PRIMARY KEY (first_account_id, second_account_id),
    CONSTRAINT fk_first_account FOREIGN KEY (`first_account_id`) REFERENCES `accounts` (`account_id`),
    CONSTRAINT fk_second_account FOREIGN KEY (`second_account_id`) REFERENCES `accounts` (`account_id`)
);

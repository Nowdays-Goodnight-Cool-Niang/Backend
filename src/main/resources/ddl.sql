DROP DATABASE IF EXISTS share_me;

CREATE DATABASE share_me;

USE share_me;

CREATE TABLE `accounts`
(
    `account_id`        BINARY(16) PRIMARY KEY NOT NULL,
    `kakao_oauth_id`    BIGINT UNIQUE          NOT NULL,
    `name`              VARCHAR(255)           NULL,
    `email`             VARCHAR(320)           NULL,
    `linkedin_url`      VARCHAR(255)           NULL,
    `github_url`        VARCHAR(255)           NULL,
    `instagram_url`     VARCHAR(255)           NULL,
    `team_name`         VARCHAR(255)           NULL,
    `position`          VARCHAR(255)           NULL,
    `introduction_text` VARCHAR(255)           NULL,
    `created_at`        timestamp              not NULL,
    `updated_at`        timestamp              not NULL
);

CREATE TABLE `social_dex`
(
    `first_account_id`  BINARY(16) NOT NULL,
    `second_account_id` BINARY(16) NOT NULL,
    `created_at`        timestamp  not NULL,
    `updated_at`        timestamp  not NULL,
    PRIMARY KEY (first_account_id, second_account_id),
    CONSTRAINT fk_first_account FOREIGN KEY (`first_account_id`) REFERENCES `accounts` (`account_id`),
    CONSTRAINT fk_second_account FOREIGN KEY (`second_account_id`) REFERENCES `accounts` (`account_id`)
);

INSERT INTO `accounts`(`account_id`, `kakao_oauth_id`, `name`, `email`, `team_name`, `position`, `introduction_text`,
                       `created_at`, `updated_at`)
VALUES (0x4BDE22D531254F01882A866108BF2DA0, 3873563414, '김주호', 'eora21@naver.com', '쿨냥이', 'BE', '팀원',
        '2025-04-09 22:25:28',
        '2025-04-09 22:25:28');

INSERT INTO `accounts`(`account_id`, `kakao_oauth_id`, `name`, `email`, `team_name`, `position`, `introduction_text`,
                       `created_at`, `updated_at`)
VALUES (0x4BDE22D531254F01882A866108BF2DB0, 3874233205, '이유진', 'test@test', '쿨냥이', 'FE', '리더', '2025-04-09 22:25:28',
        '2025-04-09 22:25:28');

INSERT INTO `accounts`(`account_id`, `kakao_oauth_id`, `name`, `email`, `team_name`, `position`, `introduction_text`,
                       `created_at`, `updated_at`)
VALUES (0x4BDE22D531254F01882A866108BF2DB1, 1, '1번 유저', 'test1@test.com', '팀명', '포지션', '팀원', '2025-04-09 22:25:28',
        '2025-04-09 22:25:28');

INSERT INTO `accounts`(`account_id`, `kakao_oauth_id`, `name`, `email`, `team_name`, `position`, `introduction_text`,
                       `created_at`, `updated_at`)
VALUES (0x4BDE22D531254F01882A866108BF2DB2, 2, '2번 유저', 'test2@test.com', '안녕', '포지션', '이히히', '2025-04-09 22:25:28',
        '2025-04-09 22:25:28');

INSERT INTO `accounts`(`account_id`, `kakao_oauth_id`, `name`, `email`, `team_name`, `position`, `introduction_text`,
                       `created_at`, `updated_at`)
VALUES (0x4BDE22D531254F01882A866108BF2DB3, 3, '3번 유저', 'test3@test.com', '이야', '힘들다', '자기소개', '2025-04-09 22:25:28',
        '2025-04-09 22:25:28');

INSERT INTO `accounts`(`account_id`, `kakao_oauth_id`, `name`, `email`, `team_name`, `position`, `introduction_text`,
                       `created_at`, `updated_at`)
VALUES (0x4BDE22D531254F01882A866108BF2DB4, 4, '4번 유저', 'test4@test.com', '으음', '토마토', '맛있다', '2025-04-09 22:25:28',
        '2025-04-09 22:25:28');

INSERT INTO `accounts`(`account_id`, `kakao_oauth_id`, `name`, `email`, `team_name`, `position`, `introduction_text`,
                       `created_at`, `updated_at`)
VALUES (0x4BDE22D531254F01882A866108BF2DB5, 5, '5번 유저', 'test5@test.com', '나는야', '케첩될거야', '멋쟁이', '2025-04-09 22:25:28',
        '2025-04-09 22:25:28');

INSERT INTO `accounts`(`account_id`, `kakao_oauth_id`, `name`, `email`, `team_name`, `position`, `introduction_text`,
                       `created_at`, `updated_at`)
VALUES (0x4BDE22D531254F01882A866108BF2DB6, 6, '6번 유저', 'test6@test.com', '네이버', '부스트캠프', '10기모집',
        '2025-04-09 22:25:28', '2025-04-09 22:25:28');

INSERT INTO `accounts`(`account_id`, `kakao_oauth_id`, `name`, `email`, `team_name`, `position`, `introduction_text`,
                       `created_at`, `updated_at`)
VALUES (0x4BDE22D531254F01882A866108BF2DB7, 7, '7번 유저', 'test7@test.com', '운영', '어때', '인터뷰', '2025-04-09 22:25:28',
        '2025-04-09 22:25:28');

INSERT INTO `accounts`(`account_id`, `kakao_oauth_id`, `name`, `email`, `team_name`, `position`, `introduction_text`,
                       `created_at`, `updated_at`)
VALUES (0x4BDE22D531254F01882A866108BF2DB8, 8, '8번 유저', 'test8@test.com', '챌린지', '체크포인트', '첫날', '2025-04-09 22:25:28',
        '2025-04-09 22:25:28');

INSERT INTO `accounts`(`account_id`, `kakao_oauth_id`, `name`, `email`, `team_name`, `position`, `introduction_text`,
                       `created_at`, `updated_at`)
VALUES (0x4BDE22D531254F01882A866108BF2DB9, 9, '9번 유저', 'test9@test.com', '나머지', 'FE', '다른날', '2025-04-09 22:25:28',
        '2025-04-09 22:25:28');

INSERT INTO `accounts`(`account_id`, `kakao_oauth_id`, `name`, `email`, `team_name`, `position`, `introduction_text`,
                       `created_at`, `updated_at`)
VALUES (0x4BDE22D531254F01882A866108BF2DC0, 10, '10번 유저', 'test10@test.com', 'CS', '지식', '부스트캠프', '2025-04-09 22:25:28',
        '2025-04-09 22:25:28');

INSERT INTO `accounts`(`account_id`, `kakao_oauth_id`, `name`, `email`, `team_name`, `position`, `introduction_text`,
                       `created_at`, `updated_at`)
VALUES (0x4BDE22D531254F01882A866108BF2DC1, 11, '11번 유저', 'test11@test.com', 'CS', '리팩토링', '루시', '2025-04-09 22:25:28',
        '2025-04-09 22:25:28');

INSERT INTO `accounts`(`account_id`, `kakao_oauth_id`, `name`, `email`, `team_name`, `position`, `introduction_text`,
                       `created_at`, `updated_at`)
VALUES (0x4BDE22D531254F01882A866108BF2DC2, 12, '12번 유저', 'test12@test.com', '운영진', '멤버십', '고민', '2025-04-09 22:25:28',
        '2025-04-09 22:25:28');

INSERT INTO `social_dex`(`first_account_id`, `second_account_id`, `created_at`, `updated_at`)
VALUES (0x4BDE22D531254F01882A866108BF2DA0, 0x4BDE22D531254F01882A866108BF2DB0, '2025-04-09 22:25:28',
        '2025-04-09 22:25:28');

INSERT INTO `social_dex`(`first_account_id`, `second_account_id`, `created_at`, `updated_at`)
VALUES (0x4BDE22D531254F01882A866108BF2DB0, 0x4BDE22D531254F01882A866108BF2DB1, '2025-04-09 22:25:28',
        '2025-04-09 22:25:28');

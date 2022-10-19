INSERT INTO subscription(name, description, price)
VALUES ('Базовая', 'Базовая подписка на одного человека.', 199),
       ('Студентческая', 'Подписка для студентов.', 89),
       ('Семейная', 'Подписка на группу человек.' ||
                    'Могуть использовать до 4 устройств', 399);


INSERT INTO country(name)
VALUES ('Китай'),
       ('Великобритания'),
       ('Нигер'),
       ('Россия'),
       ('Испания');

INSERT INTO Uzer(name, login, password, id_sub, id_country)
VALUES ('Кошкарбаев Никита',
        'sullensoul',
        '12876641b3772023168cf601c72a81008ac77bc0a59918f526338247564507fd',
        2,
        4),
       ('Нуруллаев Даниил',
        'nureasy',
        'dac6c150f6f2f17c9df1b63ef4502213c6257c6fd5ea1a94f73074a6dd972cbd',
        2,
        4);

INSERT INTO organisation(name, description, id_country)
VALUES ('Warner Music',
        'Третий по величине концерн и семейство студий грамзаписи, ' ||
        'входящих в «большую тройку компаний звукозаписи»',
        2),
       ('Sony Music',
        'Вторая по величине звукозаписывающая компания ' ||
        '«Большой тройки» после Universal Music Group и ' ||
        'Warner Music Group.',
        2),
       ('Rhymes Music',
        'Музыкальный лейбл.',
        4),
       ('IDK Label',
        'Неизвестный музыкальный лейбл.',
        4);

INSERT INTO artist(login, password, name, description, id_org, id_country)
VALUES ('sqwr',
        '8395ba6440698d06556bb48687f2aa6ea98ebeb3be70e68e4ab0f25012e855b3',
        'Sqwore',
        'Российский хайперпоп-исполнитель. Занимается музыкой с 2010 года, ' ||
        'но свою активность под никнеймом Sqwore начал 4 декабря 2019 года.' ||
        'Первую большую популярность получил после завирусившегося трека ' ||
        '«Холодное оружие» с rizza',
        4,
        4),
       ('dltzk',
        'cdb57e2ec4bb416526f327d6ac6f364791c42bf7fd45bd11251092cb6d23b5dd',
        'dltzk',
        'Джейн Ремовер, ранее известная как dltzk (р. 2003), - ' ||
        'американский продюсер, автор песен и артистка из Нью-Джерси.',
        4,
        2),
       ('tommyhellatrigger',
        '70e872986ed1d5ed8de6ebbf6110787cc7ef0e43f277f147a542e0bffc48d6ee',
        'kizaru',
        'Российский и испанский хип-хоп-исполнитель родом из Санкт-Петербурга. ' ||
        'Проживает в Барселоне. Участник и основатель творческого объединения ' ||
        '«HAUNTED FAMILY»',
        1,
        5),
       ('smokepurpp',
        '7ef0e43f277f147a542e0bffc48d6ee70e872986ed1d5ed8de6ebbf6110787cc',
        'Smokepurpp',
        null,
        2,
        2);

INSERT INTO album(name, description)
VALUES ('Eve', 'Released November 26, 2021'),
       ('Born To Trap', 'Пятый полноформатный студийный ' ||
                        'альбом российского рэпера Kizaru. ' ||
                        'Пластинка включает в себя 18 работ, ' ||
                        'две из которых стали синглами, ' ||
                        'а гостевыми куплетами отметились ' ||
                        'Tory Lanez, Smokepurpp и Hoodrich ' ||
                        'Pablo Juan.');

INSERT INTO artists_albums(id_artist, id_album)
VALUES (1, 1),
       (3, 2);

INSERT INTO genre(name)
VALUES ('Hyperpop'),
       ('Trap');

INSERT INTO admin(login, password, name)
VALUES ('admin',
        '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918',
        'admin'),
       ('sullensoul',
        '3b792dec2fd2feced16a76a5f42230a3740f503ce063b8aaf7eaa7a883d85bbc',
        'sullensoul');


INSERT INTO song(name, duration, id_album, id_admin, id_genre)
VALUES ('Intro',
        89,
        2,
        2,
        2),
       ('Что за бизнес?',
        109,
        2,
        2,
        2),
       ('Block Baby',
        145,
        2,
        2,
        2),
       ('G shit',
        123,
        2,
        2,
        2),
       ('Plug',
        113,
        2,
        2,
        2),
       ('Изи Арифметика',
        165,
        2,
        2,
        2),
       ('Bad Blood',
        173,
        2,
        2,
        2),
       ('Газ в пол',
        149,
        2,
        2,
        2),
       ('Ice Cream',
        185,
        2,
        2,
        2),
       ('Whistle Song',
        161,
        2,
        2,
        2),
       ('Trapazoid',
        193,
        2,
        2,
        2),
       ('You and mee',
        111,
        2,
        2,
        2),
       ('Oh Whou',
        110,
        2,
        2,
        2),
       ('Wassup homie',
        194,
        2,
        2,
        2),
       ('Honey''s Kettle',
        161,
        2,
        2,
        2),
       ('Mr. Slime',
        123,
        2,
        2,
        2),
       ('Keep Straight',
        168,
        2,
        2,
        2),
       ('Narcos',
        145,
        2,
        2,
        2);

INSERT INTO artistssong(id_artist, id_song)
VALUES (3, 1),
       (3, 2),
       (3, 3),
       (3, 4),
       (3, 5),
       (3, 6),
       (3, 7),
       (3, 8),
       (3, 9),
       (3, 10),
       (3, 11),
       (3, 12),
       (3, 13),
       (3, 14),
       (3, 15),
       (3, 16),
       (3, 17),
       (3, 18);

INSERT INTO song(name, duration, id_album, id_admin, id_genre)
VALUES ('Сладких Снов', 98, 1, 2, 1);
INSERT INTO artistssong(id_artist, id_song)
VALUES (1, 19);

INSERT INTO song(name, duration, id_album, id_admin, id_genre)
VALUES ('Звезда Упала', 96, 1, 2, 1);
INSERT INTO artistssong(id_artist, id_song)
VALUES (1, 20);

INSERT INTO song(name, duration, id_album, id_admin, id_genre)
VALUES ('Давай Сбежим', 101, 1, 2, 1);
INSERT INTO artistssong(id_artist, id_song)
VALUES (1, 21);

INSERT INTO song(name, duration, id_album, id_admin, id_genre)
VALUES ('Ненавижу Порядок', 76, 1, 2, 1);
INSERT INTO artistssong(id_artist, id_song)
VALUES (1, 22);

INSERT INTO song(name, duration, id_album, id_admin, id_genre)
VALUES ('Навечно', 132, 1, 2, 1);
INSERT INTO artistssong(id_artist, id_song)
VALUES (1, 23);

INSERT INTO song(name, duration, id_album, id_admin, id_genre)
VALUES ('Пластиковые Воспоминания', 132, 1, 2, 1);
INSERT INTO artistssong(id_artist, id_song)
VALUES (1, 24);

INSERT INTO song(name, duration, id_album, id_admin, id_genre)
VALUES ('Тут Кто-Нибудь Есть?', 101, 1, 2, 1);
INSERT INTO artistssong(id_artist, id_song)
VALUES (1, 25);

INSERT INTO song(name, duration, id_album, id_admin, id_genre)
VALUES ('Аквариум', 107, 1, 2, 1);
INSERT INTO artistssong(id_artist, id_song)
VALUES (1, 26);


INSERT INTO uzerplaylist(id_uzer, id_song)
VALUES (1, 1),
       (1, 6),
       (1, 15);




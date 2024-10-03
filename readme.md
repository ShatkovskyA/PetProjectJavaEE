# PetProjectJavaEE

Это Pet-проект для освоения и понимания основных технологий JavaEE.

# Описание

1. Пакет "socket"
1.1. SocketRunner(клиент) + ServerSocketRunner (сервер) - это реализация технлогии отправки сообщений через TCP соединение 
(посредством сокетов)
1.2. DatagramRunner(клиент) + DatagramServerRunner(сервер) - это реализация технлогии отправки сообщений через UDP соединение
TODO: для лучшей читаемости можно разбить на два подпакета - srver (где будут серверы) и client (где будут находится клиенты)

2. Пакет "client"
2.1. UrlExample - реализует класс для реализации методов по протоколоу HTTP для обращения через URL
2.2. HttpClientExample - реализует класс для реализации роли клиента к вэб-серверу

3. Пакет "server"
3.1. HttpServer - реализует HTTP сервер (типа кок TomCat)
3.2. HttpClientRunner - реализует HTTP отправку запроса на сервер, т. е. это HTTP - клиент
3.3. HttpServerRunner - реализует проверку сервера HttpServer

4. Оборачиваем в web контекст, т. е. проект у нас становится веб-приложением - создается артифакт и появляется папка "web"
с соответсвующей папкой "WEB-INF"
6. Подключаем Tomcat9 (в нашем случае) - формируем в сервере папку для запуска приложения и тестовой страницы index.html

7. Пакет "servlet"
PreFirstServlet - класс начального уровня для запуска и демонстрации работы servletsa.
* Дублирует код класса FirstServlet до добавления туда дополнительного функционала

FlightServlet - который будет работать с сущностями Flight, т. е. обрабатывать запросы

8. Пакет "entity"
8.1. Flight - реализует сущность с полями из СУБД
8.2. FlightStatus - реализует перечисления из таблицы для сущности

9. Пакет "util"
ConnectionManager - утилитный класс, реализующий соединение
PropertiesUtil - утилитный класс

10. Пакет "service"
10.1. FlightService -

11. Пакет "dao"
10.1. FlightDao - для работы с таблицей.
10.2. Dao - общий интерфейс с методами для всех классов в слое DAO с пераметрами айдишник + тип сущности

12. Пакет "dto"
12.1. FlightDto - реализует шаблон проектирования DTO служащий промежуточным слоем передачи данных из одного слоя в другой

## Установка СУБД

Без установки СУБД проект не запустится.
У вас должны быть установлены следующие зависимости:
- Java Corretto - 15
- SQl script для создания и развертывания СУБД:
CREATE DATABASE flight_repository;

CREATE TABLE airport
(
code CHAR(3) PRIMARY KEY ,
country VARCHAR(256) NOT NULL ,
city VARCHAR(128) NOT NULL
);

CREATE TABLE aircraft
(
id SERIAL PRIMARY KEY ,
model VARCHAR(128) NOT NULL,
image BYTEA NOT NULL
);

CREATE TABLE seat
(
aircraft_id INT REFERENCES aircraft (id),
seat_no VARCHAR(4) NOT NULL ,
PRIMARY KEY (aircraft_id, seat_no)
);

CREATE TABLE flight
(
id BIGSERIAL PRIMARY KEY ,
flight_no VARCHAR(16) NOT NULL ,
departure_date TIMESTAMP NOT NULL ,
departure_airport_code CHAR(3) REFERENCES airport(code) NOT NULL ,
arrival_date TIMESTAMP NOT NULL ,
arrival_airport_code CHAR(3) REFERENCES airport(code) NOT NULL ,
aircraft_id INT REFERENCES aircraft (id) NOT NULL ,
status VARCHAR(32) NOT NULL
);

CREATE TABLE ticket
(
id BIGSERIAL PRIMARY KEY ,
passenger_no VARCHAR(32) NOT NULL ,
passenger_name VARCHAR(128) NOT NULL ,
flight_id BIGINT REFERENCES flight (id) NOT NULL ,
seat_no VARCHAR(4) NOT NULL,
cost NUMERIC(8, 2) NOT NULL
-- UNIQUE (flight_id, seat_no)
);

CREATE UNIQUE INDEX unique_flight_id_seat_no_idx ON ticket (flight_id, seat_no);
-- flight_id + seat_no

insert into airport (code, country, city)
values ('MNK', 'Беларусь', 'Минск'),
('LDN', 'Англия', 'Лондон'),
('MSK', 'Россия', 'Москва'),
('BSL', 'Испания', 'Барселона');

insert into aircraft (model)
values ('Боинг 777-300'),
('Боинг 737-300'),
('Аэробус A320-200'),
('Суперджет-100');

insert into seat (aircraft_id, seat_no)
select id, s.column1
from aircraft
cross join (values ('A1'), ('A2'), ('B1'), ('B2'), ('C1'), ('C2'), ('D1'), ('D2') order by 1) s;

insert into flight (flight_no, departure_date, departure_airport_code, arrival_date, arrival_airport_code, aircraft_id,
status)
values
('MN3002', '2020-06-14T14:30', 'MNK', '2020-06-14T18:07', 'LDN', 1, 'ARRIVED'),
('MN3002', '2020-06-16T09:15', 'LDN', '2020-06-16T13:00', 'MNK', 1, 'ARRIVED'),
('BC2801', '2020-07-28T23:25', 'MNK', '2020-07-29T02:43', 'LDN', 2, 'ARRIVED'),
('BC2801', '2020-08-01T11:00', 'LDN', '2020-08-01T14:15', 'MNK', 2, 'DEPARTED'),
('TR3103', '2020-05-03T13:10', 'MSK', '2020-05-03T18:38', 'BSL', 3, 'ARRIVED'),
('TR3103', '2020-05-10T07:15', 'BSL', '2020-05-10T012:44', 'MSK', 3, 'CANCELLED'),
('CV9827', '2020-09-09T18:00', 'MNK', '2020-09-09T19:15', 'MSK', 4, 'SCHEDULED'),
('CV9827', '2020-09-19T08:55', 'MSK', '2020-09-19T10:05', 'MNK', 4, 'SCHEDULED'),
('QS8712', '2020-12-18T03:35', 'MNK', '2020-12-18T06:46', 'LDN', 2, 'ARRIVED');

insert into ticket (passenger_no, passenger_name, flight_id, seat_no, cost)
values ('112233', 'Иван Иванов', 1, 'A1', 200),
('23234A', 'Петр Петров', 1, 'B1', 180),
('SS988D', 'Светлана Светикова', 1, 'B2', 175),
('QYASDE', 'Андрей Андреев', 1, 'C2', 175),
('POQ234', 'Иван Кожемякин', 1, 'D1', 160),
('898123', 'Олег Рубцов', 1, 'A2', 198),
('555321', 'Екатерина Петренко', 2, 'A1', 250),
('QO23OO', 'Иван Розмаринов', 2, 'B2', 225),
('9883IO', 'Иван Кожемякин', 2, 'C1', 217),
('123UI2', 'Андрей Буйнов', 2, 'C2', 227),
('SS988D', 'Светлана Светикова', 2, 'D2', 277),
('EE2344', 'Дмитрий Трусцов', 3, 'А1', 300),
('AS23PP', 'Максим Комсомольцев', 3, 'А2', 285),
('322349', 'Эдуард Щеглов', 3, 'B1', 99),
('DL123S', 'Игорь Беркутов', 3, 'B2', 199),
('MVM111', 'Алексей Щербин', 3, 'C1', 299),
('ZZZ111', 'Денис Колобков', 3, 'C2', 230),
('234444', 'Иван Старовойтов', 3, 'D1', 180),
('LLLL12', 'Людмила Старовойтова', 3, 'D2', 224),
('RT34TR', 'Степан Дор', 4, 'A1', 129),
('999666', 'Анастасия Шепелева', 4, 'A2', 152),
('234444', 'Иван Старовойтов', 4, 'B1', 140),
('LLLL12', 'Людмила Старовойтова', 4, 'B2', 140),
('LLLL12', 'Роман Дронов', 4, 'D2', 109),
('112233', 'Иван Иванов', 5, 'С2', 170),
('NMNBV2', 'Лариса Тельникова', 5, 'С1', 185),
('DSA586', 'Лариса Привольная', 5, 'A1', 204),
('DSA583', 'Артур Мирный', 5, 'B1', 189),
('DSA581', 'Евгений Кудрявцев', 6, 'A1', 204),
('EE2344', 'Дмитрий Трусцов', 6, 'A2', 214),
('AS23PP', 'Максим Комсомольцев', 6, 'B2', 176),
('112233', 'Иван Иванов', 6, 'B1', 135),
('309623', 'Татьяна Крот', 6, 'С1', 155),
('319623', 'Юрий Дувинков', 6, 'D1', 125),
('322349', 'Эдуард Щеглов', 7, 'A1', 69),
('DIOPSL', 'Евгений Безфамильная', 7, 'A2', 58),
('DIOPS1', 'Константин Швец', 7, 'D1', 65),
('DIOPS2', 'Юлия Швец', 7, 'D2', 65),
('1IOPS2', 'Ник Говриленко', 7, 'C2', 73),
('999666', 'Анастасия Шепелева', 7, 'B1', 66),
('23234A', 'Петр Петров', 7, 'C1', 80),
('QYASDE', 'Андрей Андреев', 8, 'A1', 100),
('1QAZD2', 'Лариса Потемнкина', 8, 'A2', 89),
('5QAZD2', 'Карл Хмелев', 8, 'B2', 79),
('2QAZD2', 'Жанна Хмелева', 8, 'С2', 77),
('BMXND1', 'Светлана Хмурая', 8, 'В2', 94),
('BMXND2', 'Кирилл Сарычев', 8, 'D1', 81),
('SS988D', 'Светлана Светикова', 9, 'A2', 222),
('SS978D', 'Андрей Желудь', 9, 'A1', 198),
('SS968D', 'Дмитрий Воснецов', 9, 'B1', 243),
('SS958D', 'Максим Гребцов', 9, 'С1', 251),
('112233', 'Иван Иванов', 9, 'С2', 135),
('NMNBV2', 'Лариса Тельникова', 9, 'B2', 217),
('23234A', 'Петр Петров', 9, 'D1', 189),
('123951', 'Полина Зверева', 9, 'D2', 234);

- credls для подключения СУБД в файле
- необходимо также скачать и копировать postgresql-42.5.0.jar (либо другой актуальный, более поздней версии), разместить его в папке "lib",
где содержатся все актуальные библиотеки. После копирования в папку - включить его в проект

## Стек технологий, который используется в проекте

- Lombok
- PostgreSQL
- Java-15
- 

## Library
все библиотеки находятся в пакете "lib". В случае, если что то не запустится, то лучше - заново их переустанвоить.
servlet-api - это библиотека для взаимодейтсвия с ServletAPI, ее лучше брать из lib ApacheTomCat, той версии, которая применяется
ждя запуска проекта (в данном случае использовался Tomcat 9, но можно и другой версии не ранее 9). Блиотека ставилась после начала изучения работы с ServletAPI.

1. Клонирование репозитория

```------```
## Документация

Пользовательскую документацию можно получить по [//TODO: прописать адрес,
куда будет положена документация, либо ссылка на HELP]

<!--Поддержка-->
## Поддержка
Если у вас возникли сложности или вопросы по использованию пакета,
//TODO: прописать куда обращаться

## Зависимости
Эта программа зависит от интепретатора JRE версии 15 или выше, JDK версии 15 или выше.
Если вы заметили, что он данное ПО можно запустить на версии ниже, или он не работает на какой-либо версии,
то напишите в [поддержку](//TODO: прописать адрес,
куда будет положена документация, либо ссылка на HELP)

## Возможные проблемы и пути решения
Если СУБД не отоброжается при первом запуске сервлета @WebServlet("/flights"),
то это значит что библиотека postgresql-42.2.16 и пр. где то не подхватились.
Следует проверить корректность расположения библиотек в формируемом артефакте.
Для этого следует зайти в IDEa:
File далее Project Structure далее Artifact далее сравнить вкладки Output Layout и Avialable Elements,
если в Avialable Elements присутствуют библиотеки postgresql-42.2.16 и пр, то если по ним щелкнуть два раза -
они переместятся в WEB-INF, в появившуюся папку lib. Эта же папке есть как отдельная папка в пакете.
Тогда все заработает и СУБД "подхватится"!

<!--описание коммитов-->
## Описание коммитов
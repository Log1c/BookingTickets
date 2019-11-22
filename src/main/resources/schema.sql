--https://www.javacodegeeks.com/2016/03/springboot-working-jdbctemplate.html
CREATE TABLE ticket
(
    id VARCHAR(7) NOT NULL,
    title VARCHAR(7),
    date Date, --maybe wrong
    ticketCategory VARCHAR(7), --todo wrong
    place int(3),
    PRIMARY KEY (id)
);

CREATE TABLE bookedTicket
(
    bookedTicketId VARCHAR(7) NOT NULL,
    userId int(3),
    VARCHAR(7) ticketId NOT NULL UNIQUE,
    PRIMARY KEY (bookedTicketId)
);
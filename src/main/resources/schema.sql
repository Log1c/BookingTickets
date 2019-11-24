--https://www.javacodegeeks.com/2016/03/springboot-working-jdbctemplate.html
CREATE TABLE ticket
(
    id VARCHAR(7) NOT NULL,
    title VARCHAR(7),
    date DATE, --maybe wrong
    ticketCategory VARCHAR(7), --todo wrong
    place INT,
    PRIMARY KEY (id)
);

CREATE TABLE bookedTicket
(
    bookedTicketId VARCHAR(7) NOT NULL,
    userId INT,
    ticketId VARCHAR(7) NOT NULL UNIQUE,
    PRIMARY KEY (bookedTicketId)
);
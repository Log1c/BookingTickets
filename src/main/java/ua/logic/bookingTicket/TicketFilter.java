package ua.logic.bookingTicket;

import java.util.Date;
import java.util.Optional;

public class TicketFilter {
    private String id;
    private String title;
    private Date date;    // Date when this film is demonstrated
    private TicketCategory category;
    private Integer place; // Place number

    private TicketFilter(Builder builder) {
        id = builder.id;
        title = builder.title;
        date = builder.date;
        category = builder.category;
        place = builder.place;
    }

    public static class Builder {
        private String id;
        private String title;
        private Date date;    // Date when this film is demonstrated
        private TicketCategory category;
        private Integer place; // Place number

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder date(Date val) {
            date = val;
            return this;
        }

        public Builder ticketCategory(TicketCategory val) {
            category = val;
            return this;
        }

        public Builder place(Integer val) {
            place = val;
            return this;
        }

        public TicketFilter build() {
            return new TicketFilter(this);
        }
    }

    public Optional<String> getId() {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.of(id);
    }

    public Optional<String> getTitle() {
        if (title == null) {
            return Optional.empty();
        }
        return Optional.of(title);
    }

    public Optional<Date> getDate() {
        if (date == null) {
            return Optional.empty();
        }
        return Optional.of(date);
    }

    public Optional<TicketCategory> getCategory() {
        if (category == null) {
            return Optional.empty();
        }
        return Optional.of(category);
    }

    public Optional<Integer> getPlace() {
        if (place == null) {
            return Optional.empty();
        }
        return Optional.of(place);
    }
}

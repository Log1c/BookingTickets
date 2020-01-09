package ua.logic.bookingTicket.controller;

import org.springframework.web.bind.annotation.*;
import ua.logic.bookingTicket.entity.Movie;
import ua.logic.bookingTicket.entity.User;
import ua.logic.bookingTicket.repository.MovieRepository;
import ua.logic.bookingTicket.repository.UserRepository;

import java.util.Collection;

@RestController
@RequestMapping(value = "/recommendation")
public class MovieController {
    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public Collection<Movie> getRecommendation(@RequestParam(name = "userName") String userName) {
        Collection<Movie> recommendation = movieRepository.recommendation(userName);
        return recommendation;
    }
}

package ua.logic.bookingTicket.controller;

import org.springframework.web.bind.annotation.*;
import ua.logic.bookingTicket.entity.Movie;
import ua.logic.bookingTicket.repository.MovieRepository;

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
        return movieRepository.recommendation(userName);
    }
}

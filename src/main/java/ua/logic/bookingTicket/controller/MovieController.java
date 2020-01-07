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
    private final UserRepository userRepository;

    public MovieController(MovieRepository movieRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public Collection<Movie> getRecommendation(@RequestParam(name = "userName") String userName) {
        Collection<Movie> recommendation = movieRepository.recommendation(userName);
        return recommendation;
    }

    //TODO only for test, delete it
    @PutMapping("/fill")
    public void fill() {
        //user
        User mila = new User("Mila");
        userRepository.save(mila);
        User kay = new User("Kay");
        userRepository.save(kay);
        User jade = new User("Jade");
        userRepository.save(jade);

        //movie
        Movie bookOfEli = new Movie("Book of Eli");
        movieRepository.save(bookOfEli);
        Movie wallE = new Movie("WallE");
        movieRepository.save(wallE);
        Movie exmachine = new Movie("Exmachine");
        movieRepository.save(exmachine);
        Movie aboutTime = new Movie("About time");
        movieRepository.save(aboutTime);

        //watched
        mila.watch(bookOfEli);
        mila.watch(wallE);
        userRepository.save(mila);

        kay.watch(wallE);
        kay.watch(exmachine);
        userRepository.save(kay);

        jade.watch(exmachine);
        jade.watch(aboutTime);
        userRepository.save(jade);
    }
}

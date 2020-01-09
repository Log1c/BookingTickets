package ua.logic.bookingTicket.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.logic.bookingTicket.entity.Movie;
import ua.logic.bookingTicket.repository.MovieRepository;

import java.util.Collection;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional(readOnly = true)
    public Movie findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Transactional(readOnly = true)
    public Collection<Movie> findByTitleLike(String title) {
        return movieRepository.findByTitleLike(title);
    }

    public Movie getOrPersistMovie(String title) {
        Movie movie = movieRepository.findByTitle(title);
        if (movie == null) {
            movie = movieRepository.save(new Movie(title));
        }
        return movie;
    }
}

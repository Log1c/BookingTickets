package ua.logic.bookingTicket.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.logic.bookingTicket.entity.Movie;
import ua.logic.bookingTicket.entity.User;

import java.util.Collection;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class MovieRepositoryTest {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() {
        fillDB();
    }

    @Test
    public void testFindByTitle() {
        String title = "Exmachine";
        Movie result = movieRepository.findByTitle(title);
        assertNotNull(result);
    }

    @Test
    public void testRecommendation() {
        Collection<Movie> recommendations = movieRepository.recommendation("Mila");

        assertEquals(recommendations.size(), 1);
        assertTrue(recommendations.contains(movieRepository.findByTitle("Exmachine")));
    }

    /*
    CREATE (Mila:User {name: 'Mila'})
    CREATE (Kay:User {name: 'Kay'})
    CREATE (Jade:User {name: 'Jade'})

    CREATE (BookOfEli:Movie {title:'Book of Eli'})
    CREATE (WallE:Movie {title:'WallE'})
    CREATE (Exmachine:Movie {title:'Exmachine'})
    CREATE (AboutTime:Movie {title:'About time'})

    CREATE (Mila)-[:WATCHED]->(BookOfEli)
    CREATE (Mila)-[:WATCHED]->(WallE)

    CREATE (Kay)-[:WATCHED]->(WallE)
    CREATE (Kay)-[:WATCHED]->(Exmachine)

    CREATE (Jade)-[:WATCHED]->(Exmachine)
    CREATE (Jade)-[:WATCHED]->(AboutTime)
    */
    private void fillDB() {
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
package src.main.java.com.nnk.springboot.repositories;

import src.main.java.com.nnk.springboot.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

}

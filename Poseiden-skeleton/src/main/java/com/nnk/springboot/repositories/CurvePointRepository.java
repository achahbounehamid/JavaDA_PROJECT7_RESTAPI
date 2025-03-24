package src.main.java.com.nnk.springboot.repositories;

import src.main.java.com.nnk.springboot.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}

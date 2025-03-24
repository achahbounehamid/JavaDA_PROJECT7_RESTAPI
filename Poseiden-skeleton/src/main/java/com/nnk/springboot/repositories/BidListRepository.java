package src.main.java.com.nnk.springboot.repositories;

import src.main.java.com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BidListRepository extends JpaRepository<BidList, Integer> {

}

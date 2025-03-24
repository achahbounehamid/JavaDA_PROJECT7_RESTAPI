package src.main.java.com.nnk.springboot.repositories;

import src.main.java.com.nnk.springboot.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TradeRepository extends JpaRepository<Trade, Integer> {
}

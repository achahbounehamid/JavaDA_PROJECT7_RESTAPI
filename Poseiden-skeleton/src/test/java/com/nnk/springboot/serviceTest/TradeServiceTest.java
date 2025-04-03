package com.nnk.springboot.serviceTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;
public class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeService tradeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        // Arrange
        Trade trade1 = new Trade();
        Trade trade2 = new Trade();
        List<Trade> expectedTrades = Arrays.asList(trade1, trade2);
        when(tradeRepository.findAll()).thenReturn(expectedTrades);

        // Act
        List<Trade> actualTrades = tradeService.findAll();

        // Assert
        assertEquals(expectedTrades, actualTrades);
        verify(tradeRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        // Arrange
        Integer id = 1;
        Trade expectedTrade = new Trade();
        when(tradeRepository.findById(id)).thenReturn(Optional.of(expectedTrade));

        // Act
        Optional<Trade> actualTrade = tradeService.findById(id);

        // Assert
        assertTrue(actualTrade.isPresent());
        assertEquals(expectedTrade, actualTrade.get());
        verify(tradeRepository, times(1)).findById(id);
    }

    @Test
    public void testSave() {
        // Arrange
        Trade trade = new Trade();
        when(tradeRepository.save(trade)).thenReturn(trade);

        // Act
        Trade savedTrade = tradeService.save(trade);

        // Assert
        assertEquals(trade, savedTrade);
        verify(tradeRepository, times(1)).save(trade);
    }

    @Test
    public void testDeleteById() {
        // Arrange
        Integer id = 1;

        // Act
        tradeService.deleteById(id);

        // Assert
        verify(tradeRepository, times(1)).deleteById(id);
    }
}

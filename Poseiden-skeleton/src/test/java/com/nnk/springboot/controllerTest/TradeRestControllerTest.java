package com.nnk.springboot.controllerTest;

import com.nnk.springboot.controller.rest.TradeRestController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.security.SecurityConfig;
import com.nnk.springboot.service.TradeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TradeRestController.class)
@Import(SecurityConfig.class)
class TradeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeService tradeService;

    private Trade createTrade(String account, String type, double quantity) {
        Trade trade = new Trade();
        trade.setAccount(account);
        trade.setType(type);
        trade.setBuyQuantity(quantity);
        return trade;
    }

    @Test
    void testGetAllTrades() throws Exception {
        List<Trade> trades = Arrays.asList(
                createTrade("Account1", "Type1", 100.0),
                createTrade("Account2", "Type2", 200.0)
        );

        Mockito.when(tradeService.findAll()).thenReturn(trades);

        mockMvc.perform(get("/api/trade/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(trades.size()));
    }

    @Test
    void testAddTrade() throws Exception {
        Trade trade = createTrade("AccountTest", "TypeTest", 300.0);
        Mockito.when(tradeService.save(Mockito.any())).thenReturn(trade);

        mockMvc.perform(post("/api/trade/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"account\":\"AccountTest\",\"type\":\"TypeTest\",\"buyQuantity\":300.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.account").value("AccountTest"))
                .andExpect(jsonPath("$.type").value("TypeTest"))
                .andExpect(jsonPath("$.buyQuantity").value(300.0));
    }

    @Test
    void testUpdateTrade() throws Exception {
        Trade trade = createTrade("AccountUpdated", "TypeUpdated", 150.0);
        Mockito.when(tradeService.save(Mockito.any())).thenReturn(trade);

        mockMvc.perform(put("/api/trade/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"account\":\"AccountUpdated\",\"type\":\"TypeUpdated\",\"buyQuantity\":150.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.account").value("AccountUpdated"))
                .andExpect(jsonPath("$.buyQuantity").value(150.0));
    }

    @Test
    void testDeleteTrade() throws Exception {
        mockMvc.perform(delete("/api/trade/delete/1"))
                .andExpect(status().isNoContent());
    }
}

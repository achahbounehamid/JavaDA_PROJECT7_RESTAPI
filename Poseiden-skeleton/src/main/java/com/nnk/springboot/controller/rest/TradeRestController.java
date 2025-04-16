package com.nnk.springboot.controller.rest;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/trade")
public class TradeRestController {

    @Autowired
    private TradeService tradeService;

    @GetMapping("/list")
    public List<Trade> getAllTrades() {
        return tradeService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Trade> addTrade(@RequestBody @Valid Trade trade) {
        return ResponseEntity.ok(tradeService.save(trade));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Trade> updateTrade(@PathVariable Integer id, @RequestBody @Valid Trade trade) {
        trade.setTradeId(id);
        return ResponseEntity.ok(tradeService.save(trade));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTrade(@PathVariable Integer id) {
        tradeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


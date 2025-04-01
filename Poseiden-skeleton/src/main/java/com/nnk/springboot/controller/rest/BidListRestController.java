package com.nnk.springboot.controller.rest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/bidList")
public class BidListRestController {

    @Autowired
    private BidListService bidListService;

    @GetMapping("/list")
    public List<BidList> getAll() {
        return bidListService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BidList> getById(@PathVariable Integer id) {
        return bidListService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<BidList> add(@RequestBody @Valid BidList bidList) {
        return ResponseEntity.ok(bidListService.save(bidList));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BidList> update(@PathVariable Integer id, @RequestBody @Valid BidList bidList) {
        bidList.setBidListId(id);
        return ResponseEntity.ok(bidListService.save(bidList));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        bidListService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

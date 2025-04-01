package com.nnk.springboot.controller.rest;

import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ruleName")
public class RuleRestController {

    @Autowired
    private RuleService ruleService;

    @GetMapping("/list")
    public List<Rule> getAllRules() {
        return ruleService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Rule> addRule(@RequestBody @Valid Rule rule) {
        return ResponseEntity.ok(ruleService.save(rule));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Rule> updateRule(@PathVariable Integer id, @RequestBody @Valid Rule rule) {
        rule.setId(id);
        return ResponseEntity.ok(ruleService.save(rule));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRule(@PathVariable Integer id) {
        ruleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


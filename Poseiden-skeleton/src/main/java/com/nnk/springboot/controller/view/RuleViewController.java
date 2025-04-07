package com.nnk.springboot.controller.view;

import org.springframework.ui.Model;
import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.service.RuleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rule")
public class RuleViewController {

    @Autowired
    private RuleService ruleService;

    @GetMapping("/list")
    public String home(Model model) {
        model.addAttribute("rules", ruleService.findAll());
        return "rule/list";
    }

    @GetMapping("/add")
    public String showAddForm(Rule rule) {
        return "rule/add";
    }

    @PostMapping("/validate")
    public String validate(@Valid Rule rule, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rule/add";
        }
        ruleService.save(rule);
        return "redirect:/rule/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Rule rule = ruleService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rule ID: " + id));
        model.addAttribute("rule", rule);
        return "rule/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Integer id, @Valid Rule rule, BindingResult result) {
        if (result.hasErrors()) {
            return "rule/update";
        }
        rule.setId(id);
        ruleService.save(rule);
        return "redirect:/rule/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        ruleService.deleteById(id);
        return "redirect:/rule/list";
    }
}


package com.nnk.springboot.controller.view;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDeniedController {

    @GetMapping("/403")
    public String error403(Model model) {
        model.addAttribute("errorMsg", "You are not authorized to access this page.");
        return "403";
    }
}


package com.nnk.springboot.controller.view;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
@Controller
public class ErrorController {
    @GetMapping("/app/error")
    public String error403(Model model, HttpServletRequest request) {
        model.addAttribute("errorMsg", "You are not authorized to access this page.");
        return "403";
    }
}


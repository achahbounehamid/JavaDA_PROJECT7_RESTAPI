package com.nnk.springboot.controller.view;
import org.springframework.ui.Model;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

@Controller
@RequestMapping("/bidList")
public class BidListViewController {

    @Autowired
    private BidListService bidListService;

    @GetMapping("/list")
    public String home(Model model, Principal principal) {
        model.addAttribute("bidLists", bidListService.findAll());
        model.addAttribute("username", principal.getName());
        return "bidList/list";
    }

    @GetMapping("/add")
    public String showAddForm(BidList bidList) {
        return "bidList/add";
    }

    @PostMapping("/validate")
    public String validate(@Valid BidList bidList, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/add";
        }
        bidListService.save(bidList);
        return "redirect:/bidList/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        BidList bidList = bidListService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID"));
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Integer id, @Valid BidList bidList, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/update";
        }
        bidList.setBidListId(id);
        bidListService.save(bidList);
        return "redirect:/bidList/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        bidListService.deleteById(id);
        return "redirect:/bidList/list";
    }
}

package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


//@Controller
@RestController
@RequestMapping("/bidList")
public class BidListController
{
    // TODO: Inject Bid service
    @Autowired
    private BidListService bidListService;

    // Affichage de la liste des BidList
    @GetMapping("/list")
    public String home(Model model)
    {
      model.addAttribute("bidList", bidListService.findAll());
        return "bidList/list";
    }

    //Formulaire d'ajout
    @GetMapping("/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    //Ajouter une nouvelle BidList
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
        if (result.hasErrors()) {
            return "bidList/add";
        }
        bidListService.save(bid);
        return "redirect:/bidList/list";
    }
//Affichage du formulaire de mise à jour
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
        Optional<BidList> bidOptional = bidListService.findById(id);
        if (bidOptional.isPresent()) {
            model.addAttribute("bidList", bidOptional.get());
            return "bidList/update";
        } else {
            return "bidList/update";
        }
    }
//Miss à jour d'une BidList
    @PostMapping("update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid
        if (result.hasErrors()) {
            return "bidList/update";
        }
        bidList.setBidListId(id);
        bidListService.save(bidList);
        return "redirect:/bidList/list";
    }
    //Supprimer une BidList
    @GetMapping("delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        bidListService.deleteById(id);
        return "redirect:/bidList/list";
    }
}

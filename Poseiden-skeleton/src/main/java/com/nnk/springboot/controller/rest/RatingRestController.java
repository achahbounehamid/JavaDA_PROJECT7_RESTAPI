package com.nnk.springboot.controller.rest;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

    @RestController
    @RequestMapping("/api/rating")
    public class RatingRestController {

        @Autowired
        private RatingService ratingService;

        // GET: Liste de tous les ratings
        @GetMapping("/list")
        public List<Rating> getAllRatings() {
            return ratingService.findAll();
        }

        // POST: Ajouter un rating
        @PostMapping("/add")
        public ResponseEntity<Rating> addRating(@RequestBody @Valid Rating rating) {
            Rating savedRating = ratingService.save(rating);
            return ResponseEntity.ok(savedRating);
        }

        // PUT: Modifier un rating existant
        @PutMapping("/update/{id}")
        public ResponseEntity<Rating> updateRating(@PathVariable Integer id, @RequestBody @Valid Rating rating) {
            rating.setId(id);
            Rating updatedRating = ratingService.save(rating);
            return ResponseEntity.ok(updatedRating);
        }

        // DELETE: Supprimer un rating par ID
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Void> deleteRating(@PathVariable Integer id) {
            ratingService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }


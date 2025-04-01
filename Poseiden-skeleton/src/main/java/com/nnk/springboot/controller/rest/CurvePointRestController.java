package com.nnk.springboot.controller.rest;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curvePoint")
public class CurvePointRestController {

    @Autowired
    private CurvePointService curvePointService;

    @GetMapping("/list")
    public List<CurvePoint> getAll() {
        return curvePointService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<CurvePoint> add(@RequestBody CurvePoint curvePoint) {
        CurvePoint saved = curvePointService.save(curvePoint);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CurvePoint> update(@PathVariable Integer id, @RequestBody CurvePoint curvePoint) {
        curvePoint.setId(id);
        return ResponseEntity.ok(curvePointService.save(curvePoint));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        curvePointService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurvePointService {

    @Autowired
    private CurvePointRepository curvePointRepository;

    /**
     * Sauvegarde ou met à jour un CurvePoint
     */
    public CurvePoint save(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    /**
     * Récupère tous les CurvePoints
     */
    public List<CurvePoint> findAll() {
        return curvePointRepository.findAll();
    }

    /**
     * Trouve un CurvePoint par son ID
     */
    public Optional<CurvePoint> findById(Integer id) {
        return curvePointRepository.findById(id);
    }

    /**
     * Supprime un CurvePoint par son ID
     */
    public void deleteById(Integer id) {
        curvePointRepository.deleteById(id);
    }
}


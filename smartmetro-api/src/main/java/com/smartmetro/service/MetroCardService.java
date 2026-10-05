package com.smartmetro.service;

import com.smartmetro.exception.MetroCardNotFoundException;
import com.smartmetro.model.MetroCardTO;

import java.util.List;

public interface MetroCardService {
    List<MetroCardTO> findAllMetroCards() throws MetroCardNotFoundException;
    public MetroCardTO findMetroCardById(Long id) throws MetroCardNotFoundException;
    public String deleteMetroCardById(Long id) throws MetroCardNotFoundException;

}

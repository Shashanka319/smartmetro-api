package com.smartmetro.service;

import com.smartmetro.exception.MetroCardNotFoundException;
import com.smartmetro.exception.PaymentNotFoundException;
import com.smartmetro.model.MetroCardTO;

import java.util.List;

public interface MetroCardService {
    List<MetroCardTO> findAllMetroCards() throws MetroCardNotFoundException;

}

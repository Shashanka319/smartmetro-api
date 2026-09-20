package com.smartmetro.service;

import com.smartmetro.repository.MetroCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetroCardServiceImpl implements MetroCardService {
    @Autowired
    private MetroCardRepository metroCardRepository;
}

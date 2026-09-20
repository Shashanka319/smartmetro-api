package com.smartmetro.service;

import com.smartmetro.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl implements TripService{
    @Autowired
    private TripRepository tripRepository;
}

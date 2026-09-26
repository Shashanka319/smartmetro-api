package com.smartmetro.service;

import com.smartmetro.entity.MetroCard;
import com.smartmetro.exception.MetroCardNotFoundException;
import com.smartmetro.model.MetroCardTO;
import com.smartmetro.model.UserTO;
import com.smartmetro.repository.MetroCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MetroCardServiceImpl implements MetroCardService {
    @Autowired
    private MetroCardRepository metroCardRepository;

    @Override
    public List<MetroCardTO> findAllMetroCards() throws MetroCardNotFoundException {
        log.info("Inside the MetroCardServiceImpl.findAllUsers");
        List<MetroCard> metroCards = metroCardRepository.findAll();
        if(metroCards.isEmpty()) {
            log.error("Users are not Found");
            throw new MetroCardNotFoundException("Users are Empty");
        }
        List<MetroCardTO> metroCardTOS = metroCards.stream().map(metroCard -> {
            MetroCardTO metroCardTO = new MetroCardTO();
            metroCardTO.setCardId(metroCard.getCardId());
            metroCardTO.setCardNumber(metroCard.getCardNumber());
            metroCardTO.setBalance(metroCard.getBalance());
            metroCardTO.setIssuedDate(metroCard.getIssuedDate());
            metroCardTO.setIssuedDate(metroCard.getIssuedDate());
            return metroCardTO;

        }).toList();
        log.info("Total Users Found: {}", metroCardTOS.size());
        return metroCardTOS;
    }
}

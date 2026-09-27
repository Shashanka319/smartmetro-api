package com.smartmetro.service;

import com.smartmetro.entity.MetroCard;
import com.smartmetro.exception.MetroCardNotFoundException;
import com.smartmetro.model.MetroCardTO;
import com.smartmetro.model.TransactionHistoryTO;
import com.smartmetro.model.TripTO;
import com.smartmetro.repository.MetroCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MetroCardServiceImpl implements MetroCardService {

    @Autowired
    private MetroCardRepository metroCardRepository;

    @Override
    public List<MetroCardTO> findAllMetroCards() throws MetroCardNotFoundException {
        log.info("Inside the MetroCardServiceImpl.findAllMetroCards");
        List<MetroCard> metroCards = metroCardRepository.findAll();

        if (metroCards.isEmpty()) {
            log.error("No metro cards found in the system");
            throw new MetroCardNotFoundException("No metro cards found");
        }

        List<MetroCardTO> metroCardTOS = metroCards.stream()
                .map(this::mapEntityToTO)
                .toList();

        log.info("Total MetroCards Found: {}", metroCardTOS.size());
        return metroCardTOS;
    }

    @Override
    public MetroCardTO findMetroCardById(Long id) throws MetroCardNotFoundException {
        log.info("Inside the MetroCardServiceImpl.findMetroCardById with id: {}", id);
        MetroCard metroCard = metroCardRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("MetroCard not found for id: {}", id);
                    return new MetroCardNotFoundException("MetroCard not found with ID: " + id);
                });

        return mapEntityToTO(metroCard);
    }

    private MetroCardTO mapEntityToTO(MetroCard metroCard) {
        MetroCardTO metroCardTO = new MetroCardTO();
        metroCardTO.setCardId(metroCard.getCardId());
        metroCardTO.setCardNumber(metroCard.getCardNumber());
        metroCardTO.setBalance(metroCard.getBalance());
        metroCardTO.setIssuedDate(metroCard.getIssuedDate());
        metroCardTO.setStatus(metroCard.getStatus());

        // Map Trips safely from entity
        if (metroCard.getTrips() != null) {
            Set<TripTO> tripTOSet = metroCard.getTrips().stream().map(trip -> {
                TripTO tripTO = new TripTO();
                tripTO.setTripId(trip.getTripId());
                tripTO.setStartTime(trip.getStartTime());
                tripTO.setEndTime(trip.getEndTime());
                tripTO.setFareAmount(trip.getFareAmount());
                return tripTO;
            }).collect(Collectors.toSet());
            metroCardTO.setTrips(tripTOSet);
        }

        // Map Transaction Histories safely from entity
        if (metroCard.getTransactionHistories() != null) {
            Set<TransactionHistoryTO> transactionHistoryTOS = metroCard.getTransactionHistories().stream().map(th -> {
                TransactionHistoryTO thTO = new TransactionHistoryTO();
                thTO.setTransactionId(th.getTransactionId());
                thTO.setAmount(th.getAmount());
                thTO.setTransactionDate(th.getTransactionDate());
                thTO.setTransactionType(th.getTransactionType());
                return thTO;
            }).collect(Collectors.toSet());
            metroCardTO.setTransactionHistories(transactionHistoryTOS);
        }

        return metroCardTO;
    }
}
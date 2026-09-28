package com.smartmetro.service;

import com.smartmetro.entity.MetroCard;
import com.smartmetro.exception.MetroCardNotFoundException;
import com.smartmetro.model.MetroCardTO;
import com.smartmetro.model.PaymentTO;
import com.smartmetro.model.TransactionHistoryTO;
import com.smartmetro.model.TripTO;
import com.smartmetro.repository.MetroCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MetroCardServiceImpl implements MetroCardService {

    @Autowired
    private MetroCardRepository metroCardRepository;

    @Override
    public List<MetroCardTO> findAllMetroCards() throws MetroCardNotFoundException {
        log.info("Inside the MetroCardServiceImpl.findAllMetroCards");
        List<MetroCard> metroCards = metroCardRepository.findAll();
        if (metroCards.isEmpty()) {
            log.error("MetroCards are not Found");
            throw new MetroCardNotFoundException("MetroCards are Empty");
        }

        List<MetroCardTO> metroCardTOS = metroCards.stream().map(metroCard -> {
            MetroCardTO metroCardTO = new MetroCardTO();
            metroCardTO.setCardId(metroCard.getCardId());
            metroCardTO.setCardNumber(metroCard.getCardNumber());
            metroCardTO.setBalance(metroCard.getBalance());
            metroCardTO.setIssuedDate(metroCard.getIssuedDate());
            metroCardTO.setStatus(metroCard.getStatus());

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
            if(metroCard.getPayments() != null) {
                Set<PaymentTO> paymentTOS = metroCard.getPayments().stream().map(payment ->  {
                    PaymentTO paymentTO = new PaymentTO();
                    paymentTO.setPaymentId(payment.getPaymentId());
                    paymentTO.setAmount(payment.getAmount());
                    paymentTO.setPaymentType(payment.getPaymentType());
                    paymentTO.setPaymentStatus(payment.getPaymentStatus());
                    paymentTO.setPaymentDate(payment.getPaymentDate());
                    return paymentTO;
                }).collect(Collectors.toSet());
                metroCardTO.setPayments(paymentTOS);
            }
            return metroCardTO;
        }).toList();

        log.info("Total MetroCards Found: {}", metroCardTOS.size());
        return metroCardTOS;
    }

    @Override
    public MetroCardTO findMetroCardById(Long id) throws MetroCardNotFoundException {
        log.info("Inside the MetroCardServiceImpl.findMetroCardById");
        Optional<MetroCard> metroCards = metroCardRepository.findById(id);
        if (metroCards.isEmpty()) {
            log.error("MetroCard not Found");
            throw new MetroCardNotFoundException("MetroCard is Empty");
        }
        MetroCard metroCard = metroCards.get();

        MetroCardTO metroCardTO = new MetroCardTO();
        metroCardTO.setCardId(metroCard.getCardId());
        metroCardTO.setCardNumber(metroCard.getCardNumber());
        metroCardTO.setBalance(metroCard.getBalance());
        metroCardTO.setIssuedDate(metroCard.getIssuedDate());
        metroCardTO.setStatus(metroCard.getStatus());

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
        if(metroCard.getPayments() != null) {
            Set<PaymentTO> paymentTOS = metroCard.getPayments().stream().map(payment ->  {
                PaymentTO paymentTO = new PaymentTO();
                paymentTO.setPaymentId(payment.getPaymentId());
                paymentTO.setAmount(payment.getAmount());
                paymentTO.setPaymentType(payment.getPaymentType());
                paymentTO.setPaymentStatus(payment.getPaymentStatus());
                paymentTO.setPaymentDate(payment.getPaymentDate());
                return paymentTO;
            }).collect(Collectors.toSet());
            metroCardTO.setPayments(paymentTOS);
        }

        return metroCardTO;
    }
}
package com.smartmetro.service;

import com.smartmetro.entity.MetroCard;
import com.smartmetro.exception.MetroCardNotFoundException;
import com.smartmetro.model.MetroCardTO;
import com.smartmetro.repository.MetroCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MetroCardServiceImplTest {

    @Mock
    private MetroCardRepository metroCardRepository;

    @InjectMocks
    private MetroCardServiceImpl metroCardServiceImpl;

    @Test
    public void testGetAllMetroCards_whenCardsExist_thenReturnData() throws MetroCardNotFoundException {
        List<MetroCard> cardList = new ArrayList<>();

        MetroCard card1 = new MetroCard();
        card1.setCardId(101L);
        card1.setCardNumber("MC1001");
        card1.setBalance(500.0);
        card1.setStatus("ACTIVE");
        cardList.add(card1);

        MetroCard card2 = new MetroCard();
        card2.setCardId(102L);
        card2.setCardNumber("MC1002");
        card2.setBalance(250.0);
        card2.setStatus("ACTIVE");
        cardList.add(card2);

        when(metroCardRepository.findAll()).thenReturn(cardList);

        List<MetroCardTO> cardTOS = metroCardServiceImpl.findAllMetroCards();
        assertEquals(2, cardTOS.size());
    }

    @Test
    public void testGetAllMetroCards_whenCardsNotExist_thenThrowException() {
        List<MetroCard> cardList = null;
        when(metroCardRepository.findAll()).thenReturn(cardList);

        assertThrows(MetroCardNotFoundException.class, () -> metroCardServiceImpl.findAllMetroCards());
    }
}
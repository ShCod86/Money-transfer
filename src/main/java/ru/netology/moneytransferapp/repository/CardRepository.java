package ru.netology.moneytransferapp.repository;

import org.springframework.stereotype.Repository;
import ru.netology.moneytransferapp.model.Amount;
import ru.netology.moneytransferapp.model.Card;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CardRepository {
    private final Map<String, Card> cardsList = new ConcurrentHashMap<>();

    public CardRepository() {
        final String cardNumber1 = "1234123412341234";
        final String cardNumber2 = "4321432143214321";
        final String cardNumber3 = "2345234523452345";
        final String cardNumber4 = "5678567856785678";
        cardsList.put(cardNumber1, new Card(cardNumber1, "12/23", "123", new Amount(20000, "RUB")));
        cardsList.put(cardNumber2, new Card(cardNumber2, "01/24", "321", new Amount(1111, "RUB")));
        cardsList.put(cardNumber3, new Card(cardNumber3, "10/33", "098", new Amount(1000, "RUB")));
        cardsList.put(cardNumber4, new Card(cardNumber4, "02/25", "456", new Amount(30545, "RUB")));
    }

    public Card getCard(String number) {
        return cardsList.get(number);
    }


}

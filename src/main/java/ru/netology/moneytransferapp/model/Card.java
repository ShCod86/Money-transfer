package ru.netology.moneytransferapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Card {
    private String cardNumber;
    private String cardValidTill;
    private String cardCVV;
    private Amount balance;
}

package ru.netology.moneytransferapp.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Amount {

    @NotNull
    @Positive
    private int value;

    private String currency;
}

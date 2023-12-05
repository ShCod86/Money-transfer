package ru.netology.moneytransferapp.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transfer {
    @NotEmpty
    @Size(max = 16, min = 16)
    @Pattern(regexp = "(?<!\\d)\\d{16}(?!\\d)")
    private String cardFromNumber;

    @NotEmpty
    @Size(min = 5, max = 5)
    @Pattern(regexp = "(0[1-9]|1[0-2])/\\d{2}")
    private String cardFromValidTill;

    @NotEmpty
    @Size(min = 3, max = 3)
    @Pattern(regexp = "(?<!\\d)\\d{3}(?!\\d)")
    private String cardFromCVV;

    @NotEmpty
    @Size(max = 16, min = 16)
    @Pattern(regexp = "(?<!\\d)\\d{16}(?!\\d)")
    private String cardToNumber;

    private Amount amount;
}

package com.eldar.challenge.model;

import com.eldar.challenge.enums.CreditBrand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class CreditCard extends EntityAbs {

    private CreditBrand brand;
    private String cardNumber;
    private String cardHolder;
    private LocalDate expirationDate;

    public CreditCard(CreditBrand brand, String cardNumber, String cardHolder, LocalDate expirationDate) {
        this.brand = brand;
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
    }
}

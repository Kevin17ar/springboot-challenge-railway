package com.eldar.challenge;

import com.eldar.challenge.enums.CreditBrand;
import com.eldar.challenge.model.CreditCard;
import com.eldar.challenge.util.CardOperationUtil;
import com.eldar.challenge.util.StringUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class ChallengeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CreditCard card1 = new CreditCard(CreditBrand.VISA, "1234567890123456", "Kevin Estrada", LocalDate.of(2023,12, 31));
		CreditCard card2 = new CreditCard(CreditBrand.NARA, "2345678901234567", "Marcos Quispe", LocalDate.of(2021, 10, 15));
		CreditCard card3 = new CreditCard(CreditBrand.NARA, "3456789012345678", "Carlos Gustavo", LocalDate.of(2026, 6, 17));

		// Invocar un método que devuelva toda la información de una tarjeta.
		System.out.println(CardOperationUtil.getInfoCard(card1));
		// Informar si una operación es válida.
		System.out.println(CardOperationUtil.isOperationValid(467.00));
		// Informar si una tarjeta es válida para operar.
		System.out.println(CardOperationUtil.isNotCardExpiration(card2));
		// Identificar si una tarjeta es distinto a otra.
		System.out.println(CardOperationUtil.areCardsEquals(card2, card3));
		// Obtener por medio de un método la tasa de una operación informando marca e importe
		System.out.println(CardOperationUtil.getOperationInfoRate(442.00, card1.getBrand(), LocalDate.now()));

		// Ejercicio 5)
		System.out.println(StringUtil.joinStrings(List.of("kevin", "Fernando", "Felipe", "plato")));
		System.out.println(StringUtil.joinStrings(List.of("kevin", "Fer1232ndo", "Fe{+", "plato developer")));

	}
}

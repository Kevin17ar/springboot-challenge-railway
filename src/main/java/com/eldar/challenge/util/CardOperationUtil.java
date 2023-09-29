package com.eldar.challenge.util;

import com.eldar.challenge.enums.CreditBrand;
import com.eldar.challenge.model.CreditCard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class CardOperationUtil {
    /**
     * Get information complete of credit card
     * @param card
     * @return "CardHolder: Kevin Estrada, Number: 1234567...."
     */
    public static String getInfoCard(CreditCard card) {
        return "CardHolder: " + card.getCardHolder() +
                ", Number: " + card.getCardNumber() +
                ", Brand: " + card.getBrand() +
                ", Expiration: " + card.getExpirationDate();
    }

    /**
     * Check if operation (amount) is less than a thousand.
     * @param amount
     * @return true (valid), false (not valid)
     */
    public static boolean isOperationValid(Double amount) {
        return amount < 1000 && amount > 0;
    }

    /**
     * Check if card expired
     * @param card
     * @return true (not expired), false (expired)
     */
    public static boolean isNotCardExpiration(CreditCard card) {
        return !card.getExpirationDate().isAfter(LocalDate.now());
    }

    /**
     * Check if cards are equals
     * @param card1
     * @param card2
     * @return true (card are equals), false (cards are not equals)
     */
    public static boolean areCardsEquals(CreditCard card1, CreditCard card2) {
        return card1.getCardNumber().equals(card2.getCardHolder());
    }

    /**
     *
     * @param amount
     * @param brand
     * @param date
     * @return
     */
    public static String getOperationInfoRate(Double amount, CreditBrand brand, LocalDate date) {
        if (amount <= 0) throw new IllegalArgumentException("amount must be positive");

        String infoRate;
        Double rate;
        double rateFinal;
        Double amountService;
        switch (brand.label) {
            case "VISA":
                String yearStr = Integer.toString(date.getYear());
                String yearStrLast2Num = yearStr.substring(yearStr.length() - 2);
                int yearLast2Num = Integer.parseInt(yearStrLast2Num);
                rate = (double) yearLast2Num / (double) date.getMonthValue();
                rateFinal = getRateLimit(rate);

                amountService = roundToTwoDecimals(amount * rateFinal / 100);
                infoRate = toStringOperationRate(brand.label, rateFinal, amountService);
                break;
            case "NARA":
                rate = date.getDayOfMonth() * 0.5;
                rateFinal = getRateLimit(rate);
                amountService = roundToTwoDecimals(amount * rateFinal / 100);
                infoRate = toStringOperationRate(brand.label, rateFinal, amountService);
                break;
            case "AMEX":
                rate = date.getMonthValue() * 0.1;
                rateFinal = getRateLimit(rate);
                amountService = roundToTwoDecimals(amount * rateFinal / 100);
                infoRate = toStringOperationRate(brand.label, rateFinal, amountService);
                break;
            default:
                throw new IllegalArgumentException("Brand is not supported");
        }

        return infoRate;
    }

    /**
     * Get info rate
     * @param brand
     * @param rate
     * @param amountService
     * @return "Brand: VISA| Rate : 2.55%| Amount (rate x amount): 11.27"
     */
    public static String toStringOperationRate(String brand, Double rate, Double amountService) {
        return "Brand: " + brand + "| Rate : " + rate + "%| Amount (rate x amount): " + amountService ;
    }

    /**
     * Return the rate in a specified range
     * @param rate
     * @return rate between 0.3% and 5%
     */
    public static double getRateLimit(double rate) {
        if (rate < 0.3) return 0.3;
        if (rate > 5) return 5;

        double result = roundToTwoDecimals(rate);
        return result;
    }

    /**
     * Round to two decimal places, 2.343434 to 2.34
     * @param value
     * @return 4.34
     */
    public static double roundToTwoDecimals(double value) {
        BigDecimal valueBD = new BigDecimal(value);
        valueBD = valueBD.setScale(2, RoundingMode.DOWN);
        double result = valueBD.doubleValue();
        return result;
    }
}

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
     * Calculates and returns the operation rate information based on the provided amount, credit brand, and date.
     * The rate calculation varies depending on the credit brand:
     * - For VISA: It's determined by the last two digits of the year divided by the month number.
     * - For NARA: It's based on the day of the month multiplied by 0.5.
     * - For AMEX: It's based on the month number multiplied by 0.1.
     *
     * the method will get the final rate by applying any necessary limits (0.3% - 5%).
     * The service amount is calculated as a percentage of the provided amount based on this final rate (4%).
     *
     * @param amount The transaction amount. Must be a positive value.
     * @param brand (e.g., VISA, NARA, AMEX).
     * @param date The transaction date, used to determine parts of the rate calculation.
     * @return A string representation of the operation rate information.
     * @throws IllegalArgumentException If the provided amount is not positive or if the credit brand is not supported.
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
     * Get info rate and other parameters
     * @param brand (e.g., VISA, NARA, AMEX)
     * @param rate 2.55
     * @param amountService 450.34
     * @return "Brand: VISA| Rate : 2.55%| Amount (rate x amount): 11.27"
     */
    public static String toStringOperationRate(String brand, Double rate, Double amountService) {
        return "Brand: " + brand + "| Rate : " + rate + "%| Amount (rate x amount): " + amountService ;
    }

    /**
     * Return the rate in a specified range
     * @param rate 0.1
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
     * @param value 4.345656
     * @return 4.34
     */
    public static double roundToTwoDecimals(double value) {
        BigDecimal valueBD = new BigDecimal(value);
        valueBD = valueBD.setScale(2, RoundingMode.DOWN);
        double result = valueBD.doubleValue();
        return result;
    }
}

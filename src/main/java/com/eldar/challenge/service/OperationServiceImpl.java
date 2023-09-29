package com.eldar.challenge.service;

import com.eldar.challenge.enums.CreditBrand;
import com.eldar.challenge.util.CardOperationUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OperationServiceImpl implements OperationService{
    /**
     * Service, which is responsible for obtaining today's rate information
     * @param brand
     * @param amount
     * @return
     */
    @Override
    public String getInfoRateOperation(String brand, Double amount) {
        return CardOperationUtil.getOperationInfoRate(amount, CreditBrand.valueOf(brand),LocalDate.now());
    }
}

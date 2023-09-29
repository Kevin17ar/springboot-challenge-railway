package com.eldar.challenge.controller;

import com.eldar.challenge.dto.InfoOperationDTO;
import com.eldar.challenge.dto.RateInfoBodyDTO;
import com.eldar.challenge.enums.CreditBrand;
import com.eldar.challenge.service.OperationService;
import com.eldar.challenge.util.CardOperationUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/operation")
public class OperationController {

    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping("/rate-info")
    public ResponseEntity<?> getRateOperation(@RequestBody RateInfoBodyDTO body) {
        try {
            List<String> brandList = Arrays.stream(CreditBrand.values()).map(e -> e.label).collect(Collectors.toList());
            if (body.getBrand() == null || body.getBrand().isEmpty() || !brandList.contains(body.getBrand()))
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Brand not exist"));
            if (body.getAmount() <= 0 || !CardOperationUtil.isOperationValid(body.getAmount()))
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Amount must be greater than 0 or is null"));

            String rate_info = operationService.getInfoRateOperation(body.getBrand(), body.getAmount());
            return ResponseEntity.ok(new InfoOperationDTO(true, rate_info));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(Collections.singletonMap("error", "An error occurred while calculating the rate."));
        }
    }
}

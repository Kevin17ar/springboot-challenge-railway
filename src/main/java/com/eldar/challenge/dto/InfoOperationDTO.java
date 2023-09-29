package com.eldar.challenge.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoOperationDTO {
    private boolean success;
    private String data;

    public InfoOperationDTO(boolean success, String data) {
        this.success = success;
        this.data = data;
    }
}

package com.eriegarbage.garbageapp.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentDto implements Serializable {
    private String cardType;
    private String cardNumber;
    private double paymentAmount;
}

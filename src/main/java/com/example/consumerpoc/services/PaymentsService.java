package com.example.consumerpoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class PaymentsService {

    public void triggerInvoicePayment(Integer invoiceId) {
        //Todo: implement payment
    }
}

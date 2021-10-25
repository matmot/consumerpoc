package com.example.consumerpoc.events;

import lombok.Data;

@Data
public class PendingPaymentEvent {
    private int invoiceId;
}

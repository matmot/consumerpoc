package com.example.consumerpoc.consumers;

import com.example.consumerpoc.events.PendingPaymentEvent;
import com.example.consumerpoc.services.PaymentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@Transactional
@RequiredArgsConstructor
@Component
public class functionalPayment implements Consumer<PendingPaymentEvent> {
    private final PaymentsService paymentsService;
    private final StreamBridge streamBridge;

    public void accept(PendingPaymentEvent event) {
        paymentsService.triggerInvoicePayment(event.getInvoiceId());

        streamBridge.send("log-out-0",event);
        throw new RuntimeException("Test exception to rollback message from log-out-0");
        // something strange happens after this exception
        // The attempt is retried, although according to the configuration it shouldn't be.
        // After the second exception, the message goes to log_queue, although it should be
        // rolled back
    }
}

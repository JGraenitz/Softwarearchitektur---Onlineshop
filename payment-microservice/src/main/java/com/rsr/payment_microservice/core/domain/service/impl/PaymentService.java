package com.rsr.payment_microservice.core.domain.service.impl;

import com.rsr.payment_microservice.core.domain.model.RSRPayment;
import com.rsr.payment_microservice.core.domain.service.interfaces.IPaymentRepository;
import com.rsr.payment_microservice.core.domain.service.interfaces.IPaymentService;
import com.rsr.payment_microservice.port.utils.exceptions.NoSuchPaymentException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class PaymentService implements IPaymentService {

    private final IPaymentRepository paymentRepository;

    @Override
    public void createPayment(RSRPayment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public RSRPayment getPaymentByOrderIdAndUserId(UUID orderId, UUID userId) throws NoSuchPaymentException {
        RSRPayment payment = paymentRepository.findByOrderId(orderId);
        if (payment == null || !Objects.equals(payment.getUserId().toString(), userId.toString())) {
            throw new NoSuchPaymentException();
        }
        return payment;
    }
}

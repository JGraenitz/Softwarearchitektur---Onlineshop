package com.rsr.payment_microservice.core.domain.service.interfaces;

import com.rsr.payment_microservice.core.domain.model.RSRPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPaymentRepository extends JpaRepository<RSRPayment, UUID> {
    RSRPayment findByOrderId(UUID orderId);
}

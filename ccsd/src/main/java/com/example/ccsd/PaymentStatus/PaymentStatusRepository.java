package com.example.ccsd.PaymentStatus;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentStatusRepository extends MongoRepository<PaymentStatus, String> {

}

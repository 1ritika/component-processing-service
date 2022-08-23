package com.roms.componentprocessing.repositories;

import com.roms.componentprocessing.entity.PaymentInformation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentReturnRepository extends JpaRepository<PaymentInformation, Integer> {
}

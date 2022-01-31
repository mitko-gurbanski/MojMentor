package com.finki.mojmentor.repository;

import com.finki.mojmentor.Model.Purchase;
import com.finki.mojmentor.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Purchase save(Purchase purchase);
}

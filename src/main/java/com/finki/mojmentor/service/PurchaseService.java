package com.finki.mojmentor.service;

import com.finki.mojmentor.Model.Purchase;
import com.finki.mojmentor.Model.User;

public interface PurchaseService {

    Purchase savePurchase(Purchase purchase);

    void addPurchaseToMentor(Purchase purchase, User buyer);
}

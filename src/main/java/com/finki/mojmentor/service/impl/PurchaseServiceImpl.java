package com.finki.mojmentor.service.impl;

import com.finki.mojmentor.Model.Notification;
import com.finki.mojmentor.Model.Purchase;
import com.finki.mojmentor.Model.User;
import com.finki.mojmentor.Model.enumerations.NotificationStatus;
import com.finki.mojmentor.Model.enumerations.NotificationType;
import com.finki.mojmentor.repository.MentorRepository;
import com.finki.mojmentor.repository.PurchaseRepository;
import com.finki.mojmentor.service.MentorService;
import com.finki.mojmentor.service.PurchaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final MentorService mentorService;
    private final MentorRepository mentorRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, MentorService mentorService, MentorRepository mentorRepository) {
        this.purchaseRepository = purchaseRepository;
        this.mentorService = mentorService;
        this.mentorRepository = mentorRepository;
    }

    @Override
    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public void addPurchaseToMentor(Purchase purchase, User mentor) {

        //Save purchase to mentor
        List<Purchase> currentList = mentor.getPurchaseList();
        currentList.add(purchase);
        mentor.setPurchaseList(currentList);

        //Create Notification
        List<Notification> currentNotifications = mentor.getNotifications();
        Notification notification = new Notification();
        notification.setNotificationStatus(NotificationStatus.UNREAD);
        notification.setNotificationType(NotificationType.PAYMENT);
        notification.setTitle("New Purchased program!");
        notification.setDescription("You have new Program Purchase. Go to Dashboard to see more details!");
        currentNotifications.add(notification);

        //Save mentor
        mentor.setNotifications(currentNotifications);

        mentorRepository.save(mentor);


    }
}

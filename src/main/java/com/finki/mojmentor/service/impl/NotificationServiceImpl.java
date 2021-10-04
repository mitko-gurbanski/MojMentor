package com.finki.mojmentor.service.impl;

import com.finki.mojmentor.Model.Notification;
import com.finki.mojmentor.Model.enumerations.NotificationStatus;
import com.finki.mojmentor.repository.NotificationRepository;
import com.finki.mojmentor.service.NotificationService;

import java.util.List;

public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> getAllUnreadNotifications() {
        return notificationRepository.getNotificationsByNotificationStatus(NotificationStatus.UNREAD);
    }
}

package com.finki.mojmentor.repository;

import com.finki.mojmentor.Model.Notification;
import com.finki.mojmentor.Model.enumerations.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> getNotificationsByNotificationStatus(NotificationStatus notificationStatus);
}

package com.finki.mojmentor.service;

import com.finki.mojmentor.Model.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NotificationService {
    List<Notification> getAllUnreadNotifications();
}

package com.airlife.wsAirLife.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airlife.wsAirLife.model.Notification;


@Repository
public interface NotificationDao extends JpaRepository<Notification, Integer>{
	public List<Notification> findAll();
	public Notification findById(int id);
}

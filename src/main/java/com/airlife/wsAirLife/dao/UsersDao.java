package com.airlife.wsAirLife.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.airlife.wsAirLife.model.Users;

@Repository
public interface UsersDao extends JpaRepository<Users, Integer>{
	public List<Users> findAll();
	public Users findById(int id);
	public Users findByConfirmationToken(String confirmation_token);
	
	
	public Users findByEmailAndPassword(String email, String password);
	public Users findByLoginAndPassword(String login, String password);
	
	public Users findByEmail(String username);
	public Users findByLogin(String username);
	
}

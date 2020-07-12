package com.carregal.jpa.restjpaudemyin28.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.carregal.jpa.restjpaudemyin28.entity.User;

@Repository
@Transactional
public class UserDAOService {

	@PersistenceContext
	private EntityManager entityManager;

	public Integer insert(User user) {

		entityManager.persist(user);
		return user.getId();
	}
}

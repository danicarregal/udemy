package com.carregal.jpa.restjpaudemyin28.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carregal.jpa.restjpaudemyin28.entity.User;

public interface SpringDataUserRepository extends JpaRepository<User, Integer> {

}

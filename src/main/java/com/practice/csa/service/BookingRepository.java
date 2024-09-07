package com.practice.csa.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.csa.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

}

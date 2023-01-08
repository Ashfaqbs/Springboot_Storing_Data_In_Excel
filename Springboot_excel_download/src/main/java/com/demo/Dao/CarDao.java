package com.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.model.Car;

public interface CarDao extends JpaRepository<Car, Integer>  {

}

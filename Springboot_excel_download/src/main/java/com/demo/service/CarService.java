package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Dao.CarDao;
import com.demo.model.Car;

@Service
public class CarService {

	@Autowired
	private CarDao dao;
	
	
	public List<Car> getCars()
	{
		return dao.findAll();
	}
	
	

	public Car getCars(int id)
	{
		return dao.findById(id).get();
	}
	
	

public void addCars(Car car)
{
	this.dao.save(car);
}


public void updateCar(int id, Car car)
{
	
	
	this.dao.save(car);
}

public void deleteCar(int id)
{
	this.dao.deleteById(id);
}


}

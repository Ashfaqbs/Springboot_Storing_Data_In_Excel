package com.demo.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.excelConfig.CarsExcelExporter;
import com.demo.excelConfig.ExcelExportService;
import com.demo.model.Car;
import com.demo.service.CarService;

import jakarta.servlet.http.HttpServletResponse;



@RestController
@RequestMapping("/cars")
public class CarController {

	//using log42j 
	private static final Logger LOGGER = LogManager.getLogger(CarController.class);

	private static final List<Car> XSSFWorkbook = null;

	@Autowired
	private CarService carService;




	@GetMapping("/")
	public List<Car> getCars()
	{
		LOGGER.info("Info level log message");
		LOGGER.debug("Debug level log message");
		LOGGER.error("Error level log message");
		return carService.getCars();
	}


	@GetMapping("/{id}")
	public Car Cars(@PathVariable("id") int id)
	{

		Car cars = carService.getCars(id);	
		LOGGER.info("Info level log message"+ cars);
		LOGGER.debug("Debug level log message"+ cars);
		LOGGER.error("Error level log message"+ cars);

		return cars;		
	}



	@PutMapping("/{id}")
	public void updateCars(@PathVariable("id") int id, @RequestBody Car car)
	{
		carService.updateCar(id, car);
	}

	@DeleteMapping("/{id}")
	public void deleteCar(@PathVariable("id") int id)
	{
		carService.deleteCar(id);
	}



	@PostMapping("/")
	public void saveCar(@RequestBody Car car)
	{
		carService.addCars(car);
	}

	//saving excel in target folder
	@GetMapping("/excel")
	public void exportexcel() throws IOException
	{
		//HttpServletResponse response adding this in parameter for this method settig values as below for it , will also save the file in target but also will download in browser\
		//when called so removing it  as we want to just save in target folder
//		response.setContentType("application/octet-stream");
//		String headerKey ="Content-Disposition";
//		String headerValue="attachement; filename=cars1.xlsx";
//		response.setHeader(headerKey, headerValue);
		List<Car> cars = carService.getCars();
		CarsExcelExporter exporter= new CarsExcelExporter();
		exporter.writeDataToExcel(cars);



	}


	//downloading excel
	@GetMapping("/download")
	public ResponseEntity<byte[]> downloadExcelFile() throws IOException {
		ExcelExportService excelExportService = new ExcelExportService();
		List<Car> cars = carService.getCars();
		// retrieve data from database or some other source


		org.apache.poi.xssf.usermodel.XSSFWorkbook workbook = excelExportService.generateExcelFile(cars);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", "data.xlsx");
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		workbook.write(baos);
		byte[] bytes = baos.toByteArray();

		return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
	}









}

package com.vti.testing.DATN.controler;

import com.vti.testing.DATN.dto.CarDTO;
import com.vti.testing.DATN.form.Account.CreatingAccountForm;
import com.vti.testing.DATN.form.Account.UpdatingAccountForm;
import com.vti.testing.DATN.form.Car.CreatingCarForm;
import com.vti.testing.DATN.form.Car.UpdatingCarForm;
import com.vti.testing.DATN.service.ICarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Cars")
public class CarControler {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ICarService carService;

	@GetMapping
	public Page<CarDTO> getAllCar(Pageable pageable) {
		return carService.getAllCar(pageable);
	}
	@GetMapping("/{id}")
	public CarDTO getCarbyid(@PathVariable int id){
		return carService.getCarbyid(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updatingCar(@PathVariable(name="id") int id, @RequestBody UpdatingCarForm form) {
		try {
			carService.updateCar(form, id);
			return new ResponseEntity<>("Update successfully", HttpStatus.OK);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("fail");
		}
	}

	@PostMapping()
	public ResponseEntity<?> createCar(@RequestBody CreatingCarForm form) {
		try {
			carService.createCar(form);
			return new ResponseEntity<>("Create successfully", HttpStatus.CREATED);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("fail");
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCarById(@PathVariable(name = "id") int id) {
		carService.deleteCar(id);
		return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
	}

}

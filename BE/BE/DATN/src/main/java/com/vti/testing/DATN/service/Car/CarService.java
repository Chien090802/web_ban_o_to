package com.vti.testing.DATN.service.Car;

import com.vti.testing.DATN.dto.CarDTO;
import com.vti.testing.DATN.entity.Account;
import com.vti.testing.DATN.entity.Car;
import com.vti.testing.DATN.form.Account.UpdatingAccountForm;
import com.vti.testing.DATN.form.Car.CreatingCarForm;
import com.vti.testing.DATN.form.Car.UpdatingCarForm;
import com.vti.testing.DATN.repository.ICarRepository;
import com.vti.testing.DATN.service.ICarService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService implements ICarService {
    @Autowired
    private ICarRepository carRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Page<CarDTO> getAllCar(Pageable pageable) {
        Page<Car> cars = carRepository.findAll(pageable);
        List<CarDTO> carDTOList = cars.stream().map(car -> modelMapper.map(car, CarDTO.class))
                .collect(Collectors.toList());
        return new PageImpl<>(carDTOList, pageable, cars.getTotalPages());
    }

    @Override
    public CarDTO getCarbyid(int id) {
        Car car = carRepository.findById(id).get();
        CarDTO carDTO = modelMapper.map(car, CarDTO.class);
        return carDTO;
    }

    @Override
    public void createCar(CreatingCarForm form) {
        Car car = new Car();
        car.setName(form.getName());
        car.setPrice(form.getPrice());
        car.setImage(form.getImage());
        car.setColor(form.getColor());
        carRepository.save(car);
    }


    @Override
    public void updateCar(UpdatingCarForm form, int id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException(" no find "));

        car.setName(form.getName());
        car.setColor(form.getColor());
        car.setImage(form.getImage());
        car.setPrice(form.getPrice());
        carRepository.save(car);
    }

    @Override
    public void deleteCar(int id) {
        carRepository.deleteById(id);
    }
}

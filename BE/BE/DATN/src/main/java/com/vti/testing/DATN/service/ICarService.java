package com.vti.testing.DATN.service;

import com.vti.testing.DATN.dto.AccountDTO;
import com.vti.testing.DATN.dto.CarDTO;
import com.vti.testing.DATN.form.Account.CreatingAccountForm;
import com.vti.testing.DATN.form.Account.UpdatingAccountForm;
import com.vti.testing.DATN.form.Car.CreatingCarForm;
import com.vti.testing.DATN.form.Car.UpdatingCarForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICarService {
    Page<CarDTO> getAllCar(Pageable pageable);
//    public void createCar(CreatingCarForm form);
    CarDTO getCarbyid (int id);
    void createCar(CreatingCarForm form);

    public void updateCar(UpdatingCarForm form, int id);

    public void deleteCar(int id);
}

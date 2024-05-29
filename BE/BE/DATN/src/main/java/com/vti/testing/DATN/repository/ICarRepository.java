package com.vti.testing.DATN.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vti.testing.DATN.entity.Car;

@Repository
public interface ICarRepository extends JpaRepository<Car,Integer>, JpaSpecificationExecutor<Car> {

}

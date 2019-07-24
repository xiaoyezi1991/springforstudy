package com.yezi.springforstudy.ioc.beans;

import org.springframework.beans.factory.FactoryBean;

public class CarFactoryBean implements FactoryBean<Car> {
    private  String carInfo ;
    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        String[] carInfos = carInfo.split(",");
        car.setBrand(carInfos[0]);
        car.setMaxSpeed(Integer.valueOf(carInfos[1]));
        car.setPrice(Double.valueOf(carInfos[2]));
        return car;
    }

    @Override
    public Class<Car> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }
}

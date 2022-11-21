package com.epam.multithreading.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

public class Pier {
    static final Logger logger = LogManager.getLogger();
    private PierStatus status;
    private int number;
    private Ship ship;

    public Pier( int number) {
        this.status = PierStatus.FREE;
        this.number = number;
    }
    public void unload(Ship ship) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(50);
        logger.log(Level.INFO,"Ship {} is unloaded",ship.getShipId());
        this.setStatus(PierStatus.FREE);
    }
    public PierStatus getStatus() {
        return status;
    }

    public void setStatus(PierStatus status) {
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

}

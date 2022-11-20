package com.epam.multithreading.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.StringJoiner;

public class Ship extends Thread{
    static final Logger logger = LogManager.getLogger();
    private int shipId;
    private ShipStatus status;

    public Ship(int id){
        this.shipId=id;
        this.status=ShipStatus.WAITING;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public ShipStatus getStatus() {
        return status;
    }

    public void setStatus(ShipStatus status) {
        this.status = status;
    }

    @Override
    public void run() {
        Port port=Port.getInstance();
        logger.log(Level.INFO,"The ship number {} is create",this.shipId);
        try {
          Pier pier=port.getPier(this);
          pier.unload(this);
          logger.log(Level.INFO,"Ship number {} leave",this.getShipId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Ship.class.getSimpleName() + "[", "]")
                .add("shipId=" + shipId)
                .add("status=" + status)
                .toString();
    }
}

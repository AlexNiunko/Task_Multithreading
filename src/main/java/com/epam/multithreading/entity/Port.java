package com.epam.multithreading.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    static final Logger logger = LogManager.getLogger();
    private static Lock locker = new ReentrantLock();
    private static Port portInstance;
    private static final int NUMBER_OF_PIERS = 2;
    private List<Pier> piers = new ArrayList<>();


    private Port() {
        for (int i = 0; i < NUMBER_OF_PIERS; i++) {
            piers.add(new Pier(i));
        }
        logger.log(Level.INFO, "Port is create");
    }

    public List<Pier> getPiers() {
        return piers;
    }

    public static Port getInstance() {
        if (portInstance == null) {
            locker.lock();
            if (portInstance == null) {
                portInstance = new Port();
            }
            locker.unlock();
        }
        return portInstance;
    }
    public synchronized Pier getPier(Ship ship) throws InterruptedException {
        while (true){
            notify();
            for (Pier current:this.piers) {
                if (current.getStatus().equals(PierStatus.FREE)){
                    logger.log(Level.INFO,"Pier number {} took ship {}  ",current.getNumber(),ship.getShipId());
                    current.setStatus(PierStatus.BUSY);
                    return current;
                }
            }
            logger.log(Level.WARN,"All piers are busy, wait");
            wait();
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Port.class.getSimpleName() + "[", "]")
                .add("piers=" + piers)
                .toString();
    }
}

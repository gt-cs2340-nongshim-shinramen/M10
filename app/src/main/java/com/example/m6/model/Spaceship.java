package com.example.m6.model;

/**
 * The enum class which have spaceship information
 */
public enum Spaceship {
    GNAT(15, 0.7),
    FLEA(20, 0.9);

    private final int bay;
    private final double efficiency;

    Spaceship(int bay, double efficiency) {
        this.bay = bay;
        this.efficiency = efficiency;
    }

    /**
     * the method to get the maximum bay
     * @return the maximum bay
     */
    public int getBay(){
        return bay;
    }

    /**
     * the method to get the efficiency of the space
     * @return the efficiency of the space
     */
    public double getEfficiency(){return efficiency;}
}


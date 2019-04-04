package com.example.m6.model;

import java.io.Serializable;

/**
 * The Solar System Class
 */
public class SolarSystem implements Serializable {
    private String name;
    private int coordinate_x;
    private int coordinate_y;
    private int techLevel;
    private int resource;
    private final TechLevel techLevelValue;
    private final Resource resourceValue;
    private final Planet planet;
    SolarSystem(String name, int x, int y, int techLevel, int resource) {
        this.planet = new Planet(name, x, y, techLevel, resource);
        this.name = name;
        coordinate_x = x;
        coordinate_y = y;
        this.techLevel = techLevel;
        this.resource = resource;
        resourceValue = Resource.values()[resource];
        techLevelValue = TechLevel.values()[techLevel];
    }

    /**
     * the method getting name
     * @return the name of solar system
     */
    public String getName() {
        return name;
    }

    /**
     * the method setting name
     * @param name the name which you want to set as a name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * the method getting coordinate x
     * @return the coordinate x
     */
    public int getCoordinate_x() {
        return coordinate_x;
    }

    /**
     * the method setting coordinate x
     * @param coordinate_x the coordinate x you want to set
     */
    public void setCoordinate_x(int coordinate_x) {
        this.coordinate_x = coordinate_x;
    }

    /**
     * the method getting coordinate y
     * @return the coordinate y
     */
    public int getCoordinate_y() {
        return coordinate_y;
    }

    /**
     * the method setting coordinate y
     * @param coordinate_y the coordinate y you want to set
     */
    public void setCoordinate_y(int coordinate_y) {
        this.coordinate_y = coordinate_y;
    }

    /**
     *the method getting Tech level
     * @return the tech level
     */
    public int getTechLevel() {
        return techLevel;
    }

    /**
     * the method setting tech level
     * @param techLevel the tech level you want to set
     */
    public void setTechLevel(int techLevel) {
        this.techLevel = techLevel;
    }

    /**
     * the method getting tech level value
     * @return tech level value
     */
    public TechLevel getTechLevelValue() {
        return techLevelValue;
    }

    /**
     * the method getting resource
     * @return the resource
     */
    public int getResource() {
        return resource;
    }

    /**
     * the method setting resource
     * @param resource the resource you want to set
     */
    public void setResource(int resource) {
        this.resource = resource;
    }

    /**
     * the method getting resource value
     * @return the resource value
     */
    public Resource getResourceValue() {
        return resourceValue;
    }

    /**
     * the method getting planet
     * @return planet
     */
    public Planet getPlanet() {
        return planet;
    }

}

package com.example.m6.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class Universe implements Serializable {

    //Acamar is initial solar system when new player is created.
    String[] nameList = {"Acamar", "Baratas", "Calondia", "Daled", "Exo", "Ferris", "Hades",
            "Kira", "Lave", "Kimchi"};

    boolean[][] coordinate = new boolean[150][100];
    private List<SolarSystem> system = new ArrayList<>();
    public Universe(){
        int systemCount = 0;
        while(systemCount < 10) {
            int x = (int) (Math.random() * 150);
            int y = (int) (Math.random() * 100);
            if(!coordinate[x][y]) {
                system.add(new SolarSystem(nameList[systemCount], x, y, (int)(Math.random()*7),
                        (int)(Math.random()*12)));
                coordinate[x][y]=true;
                systemCount++;
            }
        }
    }

    public List<SolarSystem> getSystem() {
        return system;
    }
    public boolean[][] getCoordinate(){
        return coordinate;
    }

}

package com.example.m6.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
public class Player implements Serializable {

    private static final long serialVersionUID= 1L;
    public static List<String> validDifficulty = Arrays.asList("Beginner", "Easy", "Normal",
            "Hard", "Impossible");
    private boolean warped = false;
    private String name, difficulty;
    private Spaceship spaceship;
    private int pilot, fighter, trader, engineer, credit;
    private int cargo;
    private int fuel;
    private Map<String, Integer> inven;
    private Universe system;
    private Planet currentplanet;
    public static int findIndexofDifficulty(String difficulty) {
        int i = 0;
        while(i<4) {
            if(difficulty.equals(validDifficulty.get(i))) {return i;}
            i++;
        }
        return 0;
    }
    public Player(String name, int pilot, int fighter, int trader, int engineer, String difficulty,
                  Universe universe, Map<String, Integer> map) {
        this.name = name;
        this.pilot = pilot;
        this.fighter = fighter;
        this.trader = trader;
        this.engineer = engineer;
        this.difficulty = difficulty;
        this.system = universe;
        inven = map;

        //this will be acamar solarsystem with acamar planet.
        currentplanet = system.getSystem().get(0).getPlanet();
        credit = 1000;
        spaceship = Spaceship.GNAT;
        cargo = 0;
        fuel = 80;
    }

    public void setFuel(int fuel){
        this.fuel = fuel;
    }
    public int getFuel(){return fuel;}

    public String getName() {
        return name;
    }

    public Universe getUniverse(){return system;}

    public void setCurrentplanet(Planet planet){
        currentplanet = planet;
    }
    public Planet getCurrentplanet(){return currentplanet;}

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getPilot() {
        return pilot;
    }

    public void setPilot(int pilot) {
        this.pilot = pilot;
    }

    public int getFighter() {
        return fighter;
    }

    public void setFighter(int fighter) {
        this.fighter = fighter;
    }

    public int getTrader() {
        return trader;
    }

    public void setTrader(int trader) {
        this.trader = trader;
    }

    public int getEngineer() {
        return engineer;
    }

    public void setEngineer(int engineer) {
        this.engineer = engineer;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    public int getCargo(){

        return cargo;
    }
    public void setCargo(int cargo){
        this.cargo = cargo;
    }
    public Map<String, Integer> getInven(){
        return inven;
    }

    public boolean getWarped() {
        return warped;
    }

    public void setWarped(boolean warped) {
        this.warped = warped;
    }

}

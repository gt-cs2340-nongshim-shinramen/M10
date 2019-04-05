package com.example.m6.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Represents a Universe
 * @author Juntae Kim
 * @author Woonglae Cho
 * @author Jinhyung Park
 * @author Yeonjoo Yoo
 * @author Semin Choi
 */
@SuppressWarnings("ALL")
public class Universe implements Serializable {

    private List<SolarSystem> system = new ArrayList<>();

    /**
     *
     */
    public Universe(){
        int systemCount = 0;
        while(systemCount < 10) {
            final int universeWidth = 150;
            int x = (int) (Math.random() * universeWidth);
            final int universeLength = 100;
            int y = (int) (Math.random() * universeLength);
            boolean[][] coordinate = new boolean[universeWidth][universeLength];
            if(!coordinate[x][y]) {
                final int multiplyNum = 12;
                //Acamar is initial solar system when new player is created.
                String[] nameList = {"Acamar", "Baratas", "Calondia", "Daled", "Exo",
                        "Ferris", "Hades", "Kira", "Lave", "Kimchi"};
                system.add(new SolarSystem(nameList[systemCount], x, y, (int)(Math.random()*7),
                        (int)(Math.random()* multiplyNum)));
                coordinate[x][y]=true;
                systemCount++;
            }
        }
    }

    /**
     *
     * @return List of Solar Systems
     */
    public List<SolarSystem> getSystem() {
        return Collections.unmodifiableList(system);
    }

}

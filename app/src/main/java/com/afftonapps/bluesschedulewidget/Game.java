package com.afftonapps.bluesschedulewidget;

import org.joda.time.*;

public class Game {

    public DateTime gameTime;
    public String opponent;

    public Game(){

    }

    public Game(DateTime gt, String opp){
        gameTime = gt;
        opponent = opp;

    }
}
package com.afftonapps.bluesschedulewidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimeZone;

//import org.joda.time.DateTime;

public class SimpleWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int count = appWidgetIds.length;
        JodaTimeAndroid.init(context );
        Log.d("myTag","onEnabled Called");

        for (int i = 0; i < count; i++) {
            int widgetId = appWidgetIds[i];
            String number = String.format("%03d", (new Random().nextInt(900) + 100));

            //get the local time
            DateTime localTime = DateTime.now();

            //convert to Eastern Time Zone (America)
            DateTime nyTime = localTime.toDateTime( DateTimeZone.forTimeZone(TimeZone.getTimeZone("America/New_York")));

            //define date-time formatter for parsing
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm").withZone(DateTimeZone.forID("America/New_York"));

            //define game list, based upon New_York Time
            ArrayList<Game> gameList = new ArrayList<>();
            gameList.add(new Game(DateTime.parse("2018-09-18 20:30", formatter), "@ DAL"));
            gameList.add(new Game(DateTime.parse("2018-09-19 20:00", formatter), "@ MIN"));
            gameList.add(new Game(DateTime.parse("2018-09-21 20:00", formatter), "vs CBJ"));
            gameList.add(new Game(DateTime.parse("2018-09-23 15:00", formatter), "@ CBJ"));
            gameList.add(new Game(DateTime.parse("2018-09-25 20:00", formatter), "vs WSH"));
            gameList.add(new Game(DateTime.parse("2018-09-28 20:00", formatter), "vs DAL"));
            gameList.add(new Game(DateTime.parse("2018-09-30 15:00", formatter), "@ WSH"));
            gameList.add(new Game(DateTime.parse("2018-10-04 20:00", formatter), "vs WPG"));
            gameList.add(new Game(DateTime.parse("2018-10-06 20:00", formatter), "vs CHI"));
            gameList.add(new Game(DateTime.parse("2018-10-11 20:00", formatter), "vs CGY"));
            gameList.add(new Game(DateTime.parse("2018-10-13 20:30", formatter), "@ CHI"));
            gameList.add(new Game(DateTime.parse("2018-10-14 19:00", formatter), "vs ANA"));
            gameList.add(new Game(DateTime.parse("2018-10-17 19:00", formatter), "@ MTL"));
            gameList.add(new Game(DateTime.parse("2018-10-20 19:00", formatter), "@ TOR"));
            gameList.add(new Game(DateTime.parse("2018-10-22 20:00", formatter), "@ WPG"));
            gameList.add(new Game(DateTime.parse("2018-10-25 20:00", formatter), "vs CBJ"));
            gameList.add(new Game(DateTime.parse("2018-10-27 20:00", formatter), "vs CHI"));
            gameList.add(new Game(DateTime.parse("2018-11-01 20:00", formatter), "vs VGK"));
            gameList.add(new Game(DateTime.parse("2018-11-03 20:00", formatter), "vs MIN"));
            gameList.add(new Game(DateTime.parse("2018-11-06 20:00", formatter), "vs CAR"));
            gameList.add(new Game(DateTime.parse("2018-11-09 20:00", formatter), "vs SJS"));
            gameList.add(new Game(DateTime.parse("2018-11-11 15:00", formatter), "vs MIN"));
            gameList.add(new Game(DateTime.parse("2018-11-14 20:00", formatter), "@ CHI"));
            gameList.add(new Game(DateTime.parse("2018-11-16 22:00", formatter), "@ VGK"));
            gameList.add(new Game(DateTime.parse("2018-11-17 22:30", formatter), "@ SJS"));
            gameList.add(new Game(DateTime.parse("2018-11-19 20:00", formatter), "vs LAK"));
            gameList.add(new Game(DateTime.parse("2018-11-21 20:00", formatter), "@ NSH"));
            gameList.add(new Game(DateTime.parse("2018-11-23 20:00", formatter), "vs NSH"));
            gameList.add(new Game(DateTime.parse("2018-11-24 19:00", formatter), "vs WPG"));
            gameList.add(new Game(DateTime.parse("2018-11-28 19:00", formatter), "@ DET"));
            gameList.add(new Game(DateTime.parse("2018-11-30 21:00", formatter), "@ COL"));
            gameList.add(new Game(DateTime.parse("2018-12-01 20:00", formatter), "@ ARI"));
            gameList.add(new Game(DateTime.parse("2018-12-05 20:00", formatter), "vs EDM"));
            gameList.add(new Game(DateTime.parse("2018-12-07 20:00", formatter), "@ WPG"));
            gameList.add(new Game(DateTime.parse("2018-12-09 15:00", formatter), "vs VAN"));
            gameList.add(new Game(DateTime.parse("2018-12-11 20:00", formatter), "vs FLA"));
            gameList.add(new Game(DateTime.parse("2018-12-14 20:00", formatter), "vs COL"));
            gameList.add(new Game(DateTime.parse("2018-12-16 15:00", formatter), "vs CGY"));
            gameList.add(new Game(DateTime.parse("2018-12-18 21:00", formatter), "@ EDM"));
            gameList.add(new Game(DateTime.parse("2018-12-20 22:00", formatter), "@ VAN"));
            gameList.add(new Game(DateTime.parse("2018-12-22 16:00", formatter), "@ CGY"));
            gameList.add(new Game(DateTime.parse("2018-12-27 20:00", formatter), "vs BUF"));
            gameList.add(new Game(DateTime.parse("2018-12-29 20:00", formatter), "vs PIT"));
            gameList.add(new Game(DateTime.parse("2018-12-31 19:00", formatter), "vs NYR"));
            gameList.add(new Game(DateTime.parse("2019-01-03 20:00", formatter), "vs WSH"));
            gameList.add(new Game(DateTime.parse("2019-01-05 20:00", formatter), "vs NYI"));
            gameList.add(new Game(DateTime.parse("2019-01-07 19:00", formatter), "@ PHI"));
            gameList.add(new Game(DateTime.parse("2019-01-08 20:00", formatter), "vs DAL"));
            gameList.add(new Game(DateTime.parse("2019-01-10 20:00", formatter), "vs MTL"));
            gameList.add(new Game(DateTime.parse("2019-01-12 21:00", formatter), "@ DAL"));
            gameList.add(new Game(DateTime.parse("2019-01-14 19:00", formatter), "@ WSH"));
            gameList.add(new Game(DateTime.parse("2019-01-15 19:00", formatter), "@ NYI"));
            gameList.add(new Game(DateTime.parse("2019-01-17 19:00", formatter), "@ BOS"));
            gameList.add(new Game(DateTime.parse("2019-01-19 19:00", formatter), "vs OTT"));
            gameList.add(new Game(DateTime.parse("2019-01-21 16:00", formatter), "@ LAK"));
            gameList.add(new Game(DateTime.parse("2019-01-23 22:00", formatter), "@ ANA"));
            gameList.add(new Game(DateTime.parse("2019-02-02 19:00", formatter), "@ CBJ"));
            gameList.add(new Game(DateTime.parse("2019-02-05 19:00", formatter), "@ FLA"));
            gameList.add(new Game(DateTime.parse("2019-02-07 19:30", formatter), "@ TBL"));
            gameList.add(new Game(DateTime.parse("2019-02-09 14:00", formatter), "vs NSH"));
            gameList.add(new Game(DateTime.parse("2019-02-10 12:30", formatter), "@ NSH"));
            gameList.add(new Game(DateTime.parse("2019-02-12 20:00", formatter), "vs NJD"));
            gameList.add(new Game(DateTime.parse("2019-02-14 21:00", formatter), "@ ARI"));
            gameList.add(new Game(DateTime.parse("2019-02-16 15:00", formatter), "@ COL"));
            gameList.add(new Game(DateTime.parse("2019-02-17 15:00", formatter), "@ MIN"));
            gameList.add(new Game(DateTime.parse("2019-02-19 20:00", formatter), "vs TOR"));
            gameList.add(new Game(DateTime.parse("2019-02-21 20:30", formatter), "@ DAL"));
            gameList.add(new Game(DateTime.parse("2019-02-23 16:00", formatter), "vs BOS"));
            gameList.add(new Game(DateTime.parse("2019-02-24 19:00", formatter), "@ MIN"));
            gameList.add(new Game(DateTime.parse("2019-02-26 20:00", formatter), "vs NSH"));
            gameList.add(new Game(DateTime.parse("2019-03-01 19:30", formatter), "@ CAR"));
            gameList.add(new Game(DateTime.parse("2019-03-02 20:00", formatter), "vs DAL"));
            gameList.add(new Game(DateTime.parse("2019-03-06 22:00", formatter), "@ ANA"));
            gameList.add(new Game(DateTime.parse("2019-03-07 22:30", formatter), "@ LAK"));
            gameList.add(new Game(DateTime.parse("2019-03-09 16:00", formatter), "@ SJS"));
            gameList.add(new Game(DateTime.parse("2019-03-12 20:00", formatter), "vs ARI"));
            gameList.add(new Game(DateTime.parse("2019-03-14 19:30", formatter), "@ OTT"));
            gameList.add(new Game(DateTime.parse("2019-03-16 13:00", formatter), "@ PIT"));
            gameList.add(new Game(DateTime.parse("2019-03-17 17:00", formatter), "@ BUF"));
            gameList.add(new Game(DateTime.parse("2019-03-19 20:00", formatter), "vs EDM"));
            gameList.add(new Game(DateTime.parse("2019-03-21 20:00", formatter), "vs DET"));
            gameList.add(new Game(DateTime.parse("2019-03-23 20:00", formatter), "vs TBL"));
            gameList.add(new Game(DateTime.parse("2019-03-25 20:00", formatter), "vs VGK"));
            gameList.add(new Game(DateTime.parse("2019-03-29 19:00", formatter), "@ NYR"));
            gameList.add(new Game(DateTime.parse("2019-03-30 19:00", formatter), "@ NJD"));
            gameList.add(new Game(DateTime.parse("2019-04-01 20:00", formatter), "vs COL"));
            gameList.add(new Game(DateTime.parse("2019-04-03 20:00", formatter), "@ CHI"));
            gameList.add(new Game(DateTime.parse("2019-04-04 20:00", formatter), "vs PHI"));
            gameList.add(new Game(DateTime.parse("2019-04-06 16:00", formatter), "vs VAN"));


            //find the next game by looping through
            Game nextGame = new Game();
            Game secondNextGame = new Game();
            Game thirdNextGame = new Game();

            for (Game game : gameList) {
                if (game.gameTime.isAfter(nyTime)) {
                    nextGame = game;
                    break;
                }
            }
            for (Game game : gameList) {
                if (game.gameTime.isAfter(nextGame.gameTime)) {
                    secondNextGame = game;
                    break;
                }
            }

            for (Game game : gameList) {
                if (game.gameTime.isAfter(secondNextGame.gameTime)) {
                    thirdNextGame = game;
                    break;
                }
            }

            //declare three new game objects, to be used for local time
            Game nextGameLocalTime = new Game();
            Game secondNextGameLocalTime = new Game();
            Game thirdNextGameLocalTime = new Game();

            //if not null, set local time game objects up
            if( nextGame.gameTime       != null){
                nextGameLocalTime        =  new Game( nextGame.gameTime.toDateTime(DateTimeZone.getDefault()), nextGame.opponent);
            }
            if( secondNextGame.gameTime != null) {
                secondNextGameLocalTime  = new Game( secondNextGame.gameTime.toDateTime(DateTimeZone.getDefault()), secondNextGame.opponent);
            }
            if( thirdNextGame.gameTime  != null ){
                thirdNextGameLocalTime   =  new Game( thirdNextGame.gameTime.toDateTime(DateTimeZone.getDefault()), thirdNextGame.opponent);
            }

//            Log.d("myTag", "nextGameLocalTime: " + nextGameLocalTime.gameTime.toString("EEE h:mm aa ZZZ"));
//            Log.d("myTag", "secondNextGameLocalTime: " + secondNextGameLocalTime.gameTime.toString("EEE h:mm aa ZZZ"));
//            Log.d("myTag", "thirdNextGameLocalTime: " + thirdNextGameLocalTime.gameTime.toString("EEE h:mm aa ZZZ"));


            //if the game is today or tomorrow, use that string, or, alternatively, use the short day string (like "Tue")
            //getDayString returns a string object
            String nextGameDayString = getDayString(nextGameLocalTime);
            String secondNextGameDayString = getDayString(secondNextGameLocalTime);
            String thirdNextGameDayString = getDayString(thirdNextGameLocalTime);

            //MAKE THIS MODIFICATION BELOW TO THE OTHER TWO STRINGS TO ELIMINATE null references
            //declare the time strings
            String nextTimeString = "";
            String secondNextTimeString = "";
            String thirdNextTimeString = "";

            //make the time/opponent string
            if( nextGameLocalTime.gameTime != null){
                nextTimeString = nextGameLocalTime.gameTime.toString(" h:mm aa") + " " + nextGameLocalTime.opponent;
            }

            if( secondNextGameLocalTime.gameTime != null){
                secondNextTimeString = secondNextGameLocalTime.gameTime.toString(" h:mm aa") + " " + secondNextGameLocalTime.opponent;
            }

            if( thirdNextGameLocalTime.gameTime != null){
                thirdNextTimeString = thirdNextGameLocalTime.gameTime.toString(" h:mm aa") + " " + thirdNextGameLocalTime.opponent;
            }

            //format the string for final display
            String nextGameDisplayString =          nextGameDayString       + nextTimeString; //nextGame.gameTime.toDateTime(DateTimeZone.getDefault()).toString(" h:mm aa") + " " + nextGame.opponent;
            String secondNextGameDisplayString =    secondNextGameDayString + secondNextTimeString;//secondNextGame.gameTime.toDateTime(DateTimeZone.getDefault()).toString(" h:mm aa") + " " + secondNextGame.opponent;
            String thirdNextGameDisplayString =     thirdNextGameDayString  + thirdNextTimeString; //thirdNextGame.gameTime.toDateTime(DateTimeZone.getDefault()).toString(" h:mm aa") + " " + thirdNextGame.opponent;

            //update remote views (widgets)
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.simple_widget);
            remoteViews.setTextViewText(R.id.nextGame, nextGameDisplayString);
            remoteViews.setTextViewText(R.id.secondNextGame, secondNextGameDisplayString);
            remoteViews.setTextViewText(R.id.thirdNextGame, thirdNextGameDisplayString);

            Intent intent = new Intent(context, SimpleWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.actionButton, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }
    private String getDayString(Game g){
        //test to make sure there is a valid input, else return empty string
        if (g.gameTime == null) {
            return "";
        }

        String returnString = "";
        String stringTodayDay = DateTime.now().dayOfMonth().getAsText();
        String stringTomorrowDay = DateTime.now().plusDays(1).dayOfMonth().getAsText();
//        String greaterThan7DaysString = DateTime.now().plusDays(6).dayOfMonth().getAsText();

        //test if the date is the same as today's date, if so, "Today"
        if (        g.gameTime.toDateTime( DateTimeZone.getDefault()).dayOfMonth().getAsText().equalsIgnoreCase( stringTodayDay ) ) {
            returnString = "Today";
        //test if the date is the same as today's date plus 1, if so return "Tomorrow"
        } else if ( g.gameTime.toDateTime( DateTimeZone.getDefault()).dayOfMonth().getAsText().equalsIgnoreCase( stringTomorrowDay ) ){
            returnString = "Tomorrow";
        //if the game is neither today or tomorrow but less than a week from now, then use the day name, i.e. Wed
        } else {
            returnString = g.gameTime.toDateTime( DateTimeZone.getDefault()).toString("EEE");
        }
        //TBD need to add another line to test if the date is > 7 day away and  if so display the date as a number
//        Log.d("myTag","getDayString returning: " + returnString);
        return returnString;
    }


}
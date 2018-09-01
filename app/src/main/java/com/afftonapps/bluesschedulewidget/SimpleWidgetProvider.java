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
            gameList.add(new Game(DateTime.parse("2017-3-13 22:00", formatter), "@ Los Angeles"));
            gameList.add(new Game(DateTime.parse("2017-3-15 22:00", formatter), "@ Anaheim"));
            gameList.add(new Game(DateTime.parse("2017-3-16 22:30", formatter), "@ San Jose"));
            gameList.add(new Game(DateTime.parse("2017-3-18 21:00", formatter), "@ Arizona"));
            gameList.add(new Game(DateTime.parse("2017-3-21 21:00", formatter), "@ Colorado"));
            gameList.add(new Game(DateTime.parse("2017-3-23 20:00", formatter), "vs Vancouver"));
            gameList.add(new Game(DateTime.parse("2017-3-25 19:00", formatter), "vs Calgary"));
            gameList.add(new Game(DateTime.parse("2017-3-27 20:00", formatter), "vs Arizona"));
            gameList.add(new Game(DateTime.parse("2017-3-29 22:30", formatter), "@ Arizona"));
            gameList.add(new Game(DateTime.parse("2017-3-31 21:00", formatter), "@ Colorado"));
            gameList.add(new Game(DateTime.parse("2017-4-2 16:00", formatter), "vs Nashville"));
            gameList.add(new Game(DateTime.parse("2017-4-4 20:00", formatter), "vs Winnipeg"));
            gameList.add(new Game(DateTime.parse("2017-4-6 19:30", formatter), "@ Florida"));
            gameList.add(new Game(DateTime.parse("2017-4-8 19:00", formatter), "@ Carolina"));
            gameList.add(new Game(DateTime.parse("2017-4-9 18:00", formatter), "vs Colorado"));

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
        //if the game is neither today, tomorrow, or greater than a week from now, then use the day name, i.e. Wed
        } else {
            returnString = g.gameTime.toDateTime( DateTimeZone.getDefault()).toString("EEE");
        }
//        Log.d("myTag","getDayString returning: " + returnString);
        return returnString;
    }


}
package com.example.utilisateur.tennistracker;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.Date;

public class Game extends Service implements LocationListener {

    // Attributs
    private String player1; // Player 1 name
    private String player2; // Player 2 name
    private ArrayList<String> scoreP1; // Player 1 score
    private ArrayList<String> scoreP2; // Player 2 score
    private String ptsP1; // Player 1 points
    private String ptsP2; // Player 2 points
    private String date_S; // Start of the game
    private String date_E; // End of the game
    private int setsP1; // Nb of sets won by player 1
    private int setsP2;// NB of sets won by player 2
    private Boolean finnish; // When a player won 2 sets
    private double latitude; // location
    private double longitude; // location

    /* Constructor */
    public Game() {
        player1 = "player 1";
        player2 = "player 2";
        scoreP1 = new ArrayList<>();
        scoreP1.add(new String("0"));
        scoreP2 = new ArrayList<>();
        scoreP2.add(new String("0"));
        ptsP1 = "0";
        ptsP2 = "0";
        date_S = (new Date()).toString();
        date_E = "";
        setsP1 = 0;
        setsP2 = 0;
        latitude = 0;
        longitude = 0;
        finnish = false;

        /*
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
        Location location = locationManager.getLastKnownLocation(provider);

        latitude = location.getLatitude();
        longitude = location.getLongitude();
        */
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /* Methods */
    // Increase player 1 points
    public void increasePtsP1() {
        switch (ptsP1) {
            case "0":
                ptsP1 = "15";
                break;
            case "15":
                ptsP1 = "30";
                break;
            case "30":
                ptsP1 = "40";
                break;
            case "40":
                if (ptsP2 == "A") {
                    ptsP2 = "40";
                } else if (ptsP2 == "40") {
                    ptsP1 = "A";
                } else {
                    ptsP1 = "0";
                    ptsP2 = "0";
                    this.increaseScoreP1();
                }
                break;
            case "A":
                ptsP1 = "0";
                ptsP2 = "0";
                this.increaseScoreP1();
                break;
        }
    }

    // Increase player 2 points
    public void increasePtsP2() {
        switch (ptsP2) {
            case "0":
                ptsP2 = "15";
                break;
            case "15":
                ptsP2 = "30";
                break;
            case "30":
                ptsP2 = "40";
                break;
            case "40":
                if (ptsP1 == "A") {
                    ptsP1 = "40";
                } else if (ptsP1 == "40") {
                    ptsP2 = "A";
                } else {
                    ptsP1 = "0";
                    ptsP2 = "0";
                    this.increaseScoreP2();
                }
                break;
            case "A":
                ptsP1 = "0";
                ptsP2 = "0";
                this.increaseScoreP2();
                break;
        }
    }

    // Increase player 1 sets
    public void increaseScoreP1() {
        switch (scoreP1.get(scoreP1.size() - 1)) {
            case "0":
                scoreP1.set(scoreP1.size() - 1, "1");
                break;
            case "1":
                scoreP1.set(scoreP1.size() - 1, "2");
                break;
            case "2":
                scoreP1.set(scoreP1.size() - 1, "3");
                break;
            case "3":
                scoreP1.set(scoreP1.size() - 1, "4");
                break;
            case "4":
                scoreP1.set(scoreP1.size() - 1, "5");
                break;
            case "5":
                scoreP1.set(scoreP1.size() - 1, "6");
                setsP1++;
                if (setsP1 == 2) {
                    finnish = true;
                } else {
                    if (scoreP1.size() < 3) {
                        scoreP1.add(new String("0"));
                        scoreP2.add(new String("0"));
                    }
                }
                break;
        }
    }

    // Increase player 2 sets
    public void increaseScoreP2() {
        switch (scoreP2.get(scoreP2.size() - 1)) {
            case "0":
                scoreP2.set(scoreP2.size() - 1, "1");
                break;
            case "1":
                scoreP2.set(scoreP2.size() - 1, "2");
                break;
            case "2":
                scoreP2.set(scoreP2.size() - 1, "3");
                break;
            case "3":
                scoreP2.set(scoreP2.size() - 1, "4");
                break;
            case "4":
                scoreP2.set(scoreP2.size() - 1, "5");
                break;
            case "5":
                scoreP2.set(scoreP2.size() - 1, "6");
                setsP2++;
                if (setsP2 == 2) {
                    finnish = true;
                } else {
                    if (scoreP2.size() < 3) {
                        scoreP1.add(new String("0"));
                        scoreP2.add(new String("0"));
                    }
                }
                break;
        }
    }

    /* Getters */
    public String getPlayer1() { return player1; }
    public String getPlayer2() { return player2; }
    public String getPtsP1() { return ptsP1; }
    public String getPtsP2() { return ptsP2; }
    public ArrayList<String> getScoreP1() { return scoreP1; }
    public ArrayList<String> getScoreP2() { return scoreP2; }
    public String getDate_S() { return date_S; }
    public String getDate_E() { return date_E; }
    public Boolean getFinnish() { return finnish; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }

    /* Setters */
    public void setPlayer1(String player) { player1 = player; }
    public void setPlayer2(String player) { player2 = player; }
    public void setDate_E(){ date_E = (new Date()).toString();}
    public void setDate_S(String _date){ date_S = _date;}
    public void setDate_E(String _date){ date_E = _date;}
    public void setScoreP1(ArrayList<String> sp1) { scoreP1 = sp1; }
    public void setScoreP2(ArrayList<String> sp2) { scoreP2 = sp2; }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
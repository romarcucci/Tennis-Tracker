package com.example.utilisateur.tennistracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class FeedReaderDbHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader6.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_PLAYER1 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_PLAYER2 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_DATES + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_DATEE + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_SCOREP1S1 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_SCOREP1S2 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_SCOREP1S3 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_SCOREP2S1 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_SCOREP2S2 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_SCOREP2S3 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /* Add a game in the database table */
    public void addGame(Game game){

        // Open database
        SQLiteDatabase db = this.getWritableDatabase();

        // Prepare data to store
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PLAYER1, game.getPlayer1());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PLAYER2, game.getPlayer2());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATES, game.getDate_S());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATEE, game.getDate_E());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SCOREP1S1, game.getScoreP1().get(0));
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SCOREP2S1, game.getScoreP2().get(0));

        if(game.getScoreP1().size()>1) {
            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SCOREP1S2, game.getScoreP1().get(1));
            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SCOREP2S2, game.getScoreP2().get(1));
        }
        else{
            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SCOREP1S2, "x");
            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SCOREP2S2, "x");
        }

        if(game.getScoreP1().size()==3) {
            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SCOREP1S3, game.getScoreP1().get(2));
            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SCOREP2S3, game.getScoreP2().get(2));
        }
        else{
            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SCOREP1S3, "x");
            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SCOREP2S3, "x");
        }

        // Send data to the database
        db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
        db.close();
    }

    /* Get all the games in the database table */
    public List<Game> getAllGames() {

        // Create a List of game object
        List<Game> gameList = new ArrayList<>();

        // Ask the data to the database
        String selectQuery = "SELECT * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Put the received data to the database
        if (cursor.moveToFirst()) {
            do {
                Game game = new Game();;
                String p1 = cursor.getString(cursor.getColumnIndex("player1"));
                String p2 = cursor.getString(cursor.getColumnIndex("player2"));
                String date_s = cursor.getString(cursor.getColumnIndex("date_s"));
                String date_e = cursor.getString(cursor.getColumnIndex("date_e"));
                ArrayList<String> sp1 = new ArrayList<>();
                ArrayList<String> sp2 = new ArrayList<>();

                sp1.add(cursor.getString(cursor.getColumnIndex("scorep1s1")));
                sp1.add(cursor.getString(cursor.getColumnIndex("scorep1s2")));
                sp1.add(cursor.getString(cursor.getColumnIndex("scorep1s3")));

                sp2.add(cursor.getString(cursor.getColumnIndex("scorep2s1")));
                sp2.add(cursor.getString(cursor.getColumnIndex("scorep2s2")));
                sp2.add(cursor.getString(cursor.getColumnIndex("scorep2s3")));

                game.setPlayer1(p1);
                game.setPlayer2(p2);
                game.setDate_S(date_s);
                game.setDate_E(date_e);
                game.setScoreP1(sp1);
                game.setScoreP2(sp2);
                gameList.add(game);
            } while (cursor.moveToNext());
        }
        // return the list
        return gameList;
    }

    /* Delete a game in the database table */
    public void deleteGame(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FeedReaderContract.FeedEntry.TABLE_NAME,
                FeedReaderContract.FeedEntry._ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }
}

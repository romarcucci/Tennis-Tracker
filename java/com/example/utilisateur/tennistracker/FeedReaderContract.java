package com.example.utilisateur.tennistracker;

import android.provider.BaseColumns;

/**
 * Created by Utilisateur on 10/04/2017.
 */
public class FeedReaderContract {
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_PLAYER1 = "player1";
        public static final String COLUMN_NAME_PLAYER2 = "player2";
        public static final String COLUMN_NAME_DATES = "date_s";
        public static final String COLUMN_NAME_DATEE = "date_e";
        public static final String COLUMN_NAME_SCOREP1S1 = "scorep1s1";
        public static final String COLUMN_NAME_SCOREP1S2 = "scorep1s2";
        public static final String COLUMN_NAME_SCOREP1S3 = "scorep1s3";
        public static final String COLUMN_NAME_SCOREP2S1 = "scorep2s1";
        public static final String COLUMN_NAME_SCOREP2S2 = "scorep2s2";
        public static final String COLUMN_NAME_SCOREP2S3 = "scorep2s3";

    }
}

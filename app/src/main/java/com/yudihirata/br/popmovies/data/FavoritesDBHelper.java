package com.yudihirata.br.popmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by marco.hirata on 15/02/2018.
 */

class FavoritesDBHelper extends SQLiteOpenHelper {
    // The name of the database
    private static final String DATABASE_NAME = "favoritesDb.db";

    // If you change the database schema, you must increment the database version
    private static final int VERSION = 1;

    public FavoritesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create tasks table (careful to follow SQL formatting rules)
        final String createTable = "CREATE TABLE " + FavoritesContract.FavoritesEntry.TABLE_NAME +
                " (" +
                FavoritesContract.FavoritesEntry._ID + " INTEGER PRIMARY KEY, " +
                FavoritesContract.FavoritesEntry.COLUMN_ID + " INTEGER NOT NULL, " +
                FavoritesContract.FavoritesEntry.COLUMN_OVERVIEW + " TEXT NOT NULL, " +
                FavoritesContract.FavoritesEntry.COLUMN_ORIGINAL_LANGUAGE + " TEXT NOT NULL, " +
                FavoritesContract.FavoritesEntry.COLUMN_ORIGINAL_TITLE + " TEXT NOT NULL, " +
                FavoritesContract.FavoritesEntry.COLUMN_VIDEO + " BOOLEAN NOT NULL, " +
                FavoritesContract.FavoritesEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                FavoritesContract.FavoritesEntry.COLUMN_POSTER_PATH + " TEXT NOT NULL, " +
                FavoritesContract.FavoritesEntry.COLUMN_BACKDROP_PATH + " TEXT NOT NULL, " +
                FavoritesContract.FavoritesEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +
                FavoritesContract.FavoritesEntry.COLUMN_VOTE_AVERAGE + " REAL NOT NULL, " +
                FavoritesContract.FavoritesEntry.COLUMN_POPULARITY + " REAL NOT NULL, " +
                FavoritesContract.FavoritesEntry.COLUMN_ADULT + " BOOLEAN NOT NULL, " +
                FavoritesContract.FavoritesEntry.COLUMN_VOTE_COUNT + " INTEGER NOT NULL  );";

        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FavoritesContract.FavoritesEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

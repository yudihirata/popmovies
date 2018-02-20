package com.yudihirata.br.popmovies.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.yudihirata.br.popmovies.data.FavoritesContract.FavoritesEntry;

import java.util.ArrayList;
import java.util.List;

import models.Movie;

/**
 * Created by marco.hirata on 09/03/2018.
 */

public class Facade {
    private final Uri mUriFavorite;
    private final ContentResolver mContentResolver;

    public Facade(Context context) {
        mContentResolver = context.getContentResolver();
        mUriFavorite = Uri.parse(FavoritesContract.BASE_CONTENT_URI.toString() + "/" +
                FavoritesContract.PATH_FAVORITES);
    }

    public Boolean isFavorite(Movie movie) {
        String[] projection = {FavoritesEntry.COLUMN_ID};

        String selection = FavoritesEntry.COLUMN_ID + "= \"" + movie.getId() +
                "\"";
        Cursor cursor = mContentResolver.query(mUriFavorite, projection, selection,
                null, null);
        if (cursor == null) {
            return false;
        }
        Boolean result = cursor.getCount() == 1;
        cursor.close();
        return result;
    }

    public Boolean setFavorite(Movie movie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FavoritesEntry.COLUMN_OVERVIEW, movie.getOverview());
        contentValues.put(FavoritesEntry.COLUMN_ORIGINAL_LANGUAGE, movie.getOriginalLanguage());
        contentValues.put(FavoritesEntry.COLUMN_ORIGINAL_TITLE, movie.getOriginalTitle());
        contentValues.put(FavoritesEntry.COLUMN_VIDEO, movie.isVideo());
        contentValues.put(FavoritesEntry.COLUMN_TITLE, movie.getTitle());
        contentValues.put(FavoritesEntry.COLUMN_POSTER_PATH, movie.getPosterPath());
        contentValues.put(FavoritesEntry.COLUMN_BACKDROP_PATH, movie.getBackdropPath());
        contentValues.put(FavoritesEntry.COLUMN_RELEASE_DATE, movie.getReleaseDate());
        contentValues.put(FavoritesEntry.COLUMN_VOTE_AVERAGE,  movie.getVoteAverage());
        contentValues.put(FavoritesEntry.COLUMN_ID, movie.getId());
        contentValues.put(FavoritesEntry.COLUMN_POPULARITY, movie.getPopularity());
        contentValues.put(FavoritesEntry.COLUMN_ADULT, movie.isAdult());
        contentValues.put(FavoritesEntry.COLUMN_VOTE_COUNT, movie.getVoteCount());
        Uri uri = mContentResolver.insert(mUriFavorite, contentValues);
        return uri != null;
    }

    public Boolean removeFavorite(Movie movie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FavoritesEntry.COLUMN_ID, movie.getId());
        String selection = FavoritesEntry.COLUMN_ID + "= \"" +
                movie.getId() + "\"";
        int rowsDeleted = mContentResolver.delete(mUriFavorite,
                selection, null);
        return rowsDeleted > 0;
    }

    public List<Movie> getListFavorites() {
        List<Movie> list = new ArrayList<>();

        String[] projection = { FavoritesEntry.COLUMN_OVERVIEW,
                FavoritesEntry.COLUMN_ORIGINAL_LANGUAGE, FavoritesEntry.COLUMN_ORIGINAL_TITLE,
                FavoritesEntry.COLUMN_POSTER_PATH, FavoritesEntry.COLUMN_VIDEO,
                FavoritesEntry.COLUMN_BACKDROP_PATH, FavoritesEntry.COLUMN_RELEASE_DATE,
                FavoritesEntry.COLUMN_VOTE_AVERAGE, FavoritesEntry.COLUMN_POPULARITY,
                FavoritesEntry.COLUMN_ID, FavoritesEntry.COLUMN_ADULT,
                FavoritesEntry.COLUMN_VOTE_COUNT};


        Cursor cursor = mContentResolver.query(mUriFavorite, projection, null,
                null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    // Get version from Cursor
                    Movie movie = new Movie();

                    movie.setOverview(cursor.getString(cursor.getColumnIndex(FavoritesEntry
                            .COLUMN_OVERVIEW)));

                    movie.setOriginalLanguage(cursor.getString(cursor.getColumnIndex(FavoritesEntry
                            .COLUMN_ORIGINAL_LANGUAGE)));

                    movie.setOriginalTitle(cursor.getString(cursor.getColumnIndex(FavoritesEntry
                            .COLUMN_ORIGINAL_TITLE)));

                    movie.setPosterPath(cursor.getString(cursor.getColumnIndex(FavoritesEntry
                            .COLUMN_POSTER_PATH)));

                    movie.setVideo(cursor.getInt(cursor.getColumnIndex(FavoritesEntry
                            .COLUMN_VIDEO)) == 1);

                    movie.setBackdropPath(cursor.getString(cursor.getColumnIndex(FavoritesEntry
                            .COLUMN_BACKDROP_PATH)));

                    movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(FavoritesEntry
                            .COLUMN_RELEASE_DATE)));

                    movie.setVoteAverage(cursor.getFloat(cursor.getColumnIndex(FavoritesEntry
                            .COLUMN_VOTE_AVERAGE)));

                    movie.setPopularity(cursor.getFloat(cursor.getColumnIndex(FavoritesEntry
                            .COLUMN_POPULARITY)));

                    movie.setId(cursor.getInt(cursor.getColumnIndex(FavoritesEntry
                            .COLUMN_ID)));

                    movie.setAdult(cursor.getInt(cursor.getColumnIndex(FavoritesEntry
                            .COLUMN_ADULT)) == 1);

                    movie.setVoteCount(cursor.getInt(cursor.getColumnIndex(FavoritesEntry
                            .COLUMN_VOTE_COUNT)));

                    list.add(movie);
                    // move to next row
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        return list;
    }
}

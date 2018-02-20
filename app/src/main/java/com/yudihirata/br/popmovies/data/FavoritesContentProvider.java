package com.yudihirata.br.popmovies.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by marco.hirata on 15/02/2018.
 */

public class FavoritesContentProvider extends ContentProvider {

    private static final int FAVORITES = 100;
    private static final int FAVORITES_WITH_MOVIE_ID = 101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private FavoritesDBHelper mFavoritesDBHelper;

    private static UriMatcher buildUriMatcher() {

        // Initialize a UriMatcher with no matches by passing in NO_MATCH to the constructor
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        /*
          All paths added to the UriMatcher have a corresponding int.
          For each kind of uri you may want to access, add the corresponding match with addURI.
          The two calls below add matches for the task directory and a single item by ID.
         */
        uriMatcher.addURI(FavoritesContract.AUTHORITY, FavoritesContract.PATH_FAVORITES, FAVORITES);
        uriMatcher.addURI(FavoritesContract.AUTHORITY, FavoritesContract.PATH_FAVORITES +
                "/#", FAVORITES_WITH_MOVIE_ID);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        mFavoritesDBHelper = new FavoritesDBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = mFavoritesDBHelper.getReadableDatabase();

        // Write URI match code and set a variable to return a Cursor
        int match = sUriMatcher.match(uri);
        Cursor retCursor;

        // Query for the favorites directory and write a default case
        switch (match) {
            // Query for the favorites directory
            case FAVORITES:
                retCursor = db.query(FavoritesContract.FavoritesEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            // Default exception
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Set a notification URI on the Cursor and return that Cursor
        if (getContext() != null) {
            retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        // Return the desired Cursor
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase db = mFavoritesDBHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case FAVORITES:
                long id;
                if ((id = db.insert(FavoritesContract.FavoritesEntry.TABLE_NAME, null,
                        contentValues)) > 0) {
                    returnUri = ContentUris.withAppendedId(FavoritesContract.FavoritesEntry
                            .CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        // Notify the resolver if the uri has been changed, and return the newly inserted URI
        if (getContext() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        // Return constructed uri (this points to the newly inserted row of data)
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        // COMPLETED (1) Get access to the database and write URI matching code to recognize a
        // single item
        final SQLiteDatabase db = mFavoritesDBHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        // Keep track of the number of deleted favorites
        int favoritesDeleted; // starts as 0

        // COMPLETED (2) Write the code to delete a single row of data
        // [Hint] Use selections to delete an item by its row ID
        switch (match) {
            case FAVORITES:
                favoritesDeleted = db.delete(FavoritesContract.FavoritesEntry.TABLE_NAME, selection,
                        selectionArgs);
                break;
            // Handle the single item case, recognized by the ID included in the URI path
            case FAVORITES_WITH_MOVIE_ID:
                // Get the task ID from the URI path
                String id = uri.getPathSegments().get(1);
                // Use selections/selectionArgs to filter for this ID
                favoritesDeleted = db.delete(FavoritesContract.FavoritesEntry.TABLE_NAME,
                        "_id=?", new String[]{id});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // COMPLETED (3) Notify the resolver of a change and return the number of items deleted
        if (favoritesDeleted != 0) {
            // A task was deleted, set notification
            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
        }

        // Return the number of favorites deleted
        return favoritesDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s,
                      @Nullable String[] strings) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

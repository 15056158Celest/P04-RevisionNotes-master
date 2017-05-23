package com.myapplicationdev.android.p04_revisionnotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.R.attr.id;

public class DBHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VER = 1;
	private static final String DATABASE_NAME = "note.db";

	private static final String TABLE_TASK = "note";
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_noteContent = "noteContent";
	private static final String COLUMN_Stars = "stars";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VER);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createTableSql = "CREATE TABLE " + TABLE_TASK + "("
				+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ COLUMN_noteContent + " TEXT,"
				+ COLUMN_Stars + " INTEGER )";
		db.execSQL(createTableSql);
		Log.i("info", "created tables");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int
			newVersion) {

		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
		// Create table(s) again
		onCreate(db);

	}

	public void insertNote(String noteContent, int stars) {
		// Get an instance of the database for writing
		SQLiteDatabase db = this.getWritableDatabase();
		// We use ContentValues object to store the values for
		//  the db operation
		ContentValues values = new ContentValues();
		// Store the column name as key and the description as value
		values.put(COLUMN_noteContent, noteContent);
		// Store the column name as key and the date as value
		values.put(COLUMN_Stars, stars);
		// Insert the row into the TABLE_TASK
		db.insert(TABLE_TASK, null, values);
		// Close the database connection
		db.close();
	}

	public ArrayList<Note> getNote(){
		ArrayList<Note> notes = new ArrayList<Note>();
		String selectQuery = "SELECT " + COLUMN_ID + ", "
				+ COLUMN_noteContent + ", "
				+ COLUMN_Stars
				+ " FROM " + TABLE_TASK;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				int id = cursor.getInt(0);
				String noteContent = cursor.getString(1);
				int Stars = cursor.getInt(2);
				Note obj = new Note(id, noteContent, Stars);
				notes.add(obj);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return notes;
	}



	public ArrayList<String> getNoteContent() {
        //TODO return records in Strings

		// Create an ArrayList that holds String objects
        ArrayList<String> notes = new ArrayList<String>();
        // Select all the notes' content
        String selectQuery = "";

        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        // Run the SQL query and get back the Cursor object
        Cursor cursor = db.rawQuery(selectQuery, null);
        // moveToFirst() moves to first row
        if (cursor.moveToFirst()) {
            // Loop while moveToNext() points to next row and returns true;
            // moveToNext() returns false when no more next row to move to
            do {


            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return notes;
    }
}

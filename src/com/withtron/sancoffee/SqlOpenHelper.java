package com.withtron.sancoffee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlOpenHelper extends SQLiteOpenHelper {

	public static final String TABLE_NEWS = "news";
	public static final String NEWS_COLUMN_ID = "_id";
	public static final String NEWS_COLUMN_TEXTID = "id";
	public static final String NEWS_COLUMN_TITLE = "title";
	public static final String NEWS_COLUMN_DESCRIPTION = "description";
	public static final String NEWS_COLUMN_THUMBNAIL_URI = "thumbnailUri";
	
	
	private static final String DATABASE_NAME = "SanCoffee.db";
	private static final int DATABASE_VERSION = 1;
	
	public SqlOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		createDatabase(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	private void createDatabase(SQLiteDatabase db){
		db.execSQL("create table " + TABLE_NEWS + "(" 
				+ NEWS_COLUMN_ID + " integer primary key autoincrement not null,"
				+ NEWS_COLUMN_TEXTID + " TEXT," 
				+ NEWS_COLUMN_TITLE + " TEXT,"
				+ NEWS_COLUMN_DESCRIPTION + " TEXT,"
				+ NEWS_COLUMN_THUMBNAIL_URI + " TEXT"
				+ ");");
	}

}

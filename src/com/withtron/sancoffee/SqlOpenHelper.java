package com.withtron.sancoffee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlOpenHelper extends SQLiteOpenHelper {

	public static final String TABLE_NEWS = "news";
	public static final String NEWS_COLUMN_ID = "_id";
	public static final String NEWS_COLUMN_TITLE = "title";
	public static final String NEWS_COLUMN_DESCRIPTION = "description";
	public static final String NEWS_COLUMN_THUMBNAIL_URI = "thumbnailUri";
	public static final String NEWS_COLUMN_UPDATE_TIME= "updateTime";
	
	public static final String TABLE_NEWS_DETAIL = "news_detail";
	public static final String NEWS_DETAIL_COLUMN_ID = "_id";
	public static final String NEWS_DETAIL_COLUMN_NEWS_ID = "newsId";
	public static final String NEWS_DETAIL_COLUMN_TYPE= "type";
	public static final String NEWS_DETAIL_COLUMN_CONTENT= "content";
	public static final String NEWS_DETAIL_COLUMN_LINK= "link";

	
	
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
				+ NEWS_COLUMN_TITLE + " TEXT,"
				+ NEWS_COLUMN_DESCRIPTION + " TEXT,"
				+ NEWS_COLUMN_THUMBNAIL_URI + " TEXT,"
				+ NEWS_COLUMN_UPDATE_TIME + " TEXT"
				+ ");");
		db.execSQL("create table " + TABLE_NEWS_DETAIL + "(" 
				+ NEWS_DETAIL_COLUMN_ID + " integer primary key autoincrement not null,"
				+ NEWS_DETAIL_COLUMN_NEWS_ID + " INTEGER,"
				+ NEWS_DETAIL_COLUMN_TYPE + " INTEGER,"
				+ NEWS_DETAIL_COLUMN_CONTENT + " TEXT,"
				+ NEWS_DETAIL_COLUMN_LINK + " TEXT"
				+ ");");		
	}

}

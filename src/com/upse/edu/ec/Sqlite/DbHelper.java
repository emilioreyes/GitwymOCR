package com.upse.edu.ec.Sqlite;

import android.R.string;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "info.sqlite";
	private static final int DB_SCHEME_VERSION = 1;

	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_SCHEME_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DataBaseManager.CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
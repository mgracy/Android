/**
 * 
 */
package com.tujk.istudy.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tujk.istudy.vo.BookMarkValue;


/**
 * title  : BookNote.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-6-4
 */
public class BookNote {


	/** db fields */
	public static final String KEY_ID = "_id";
	public static final String KEY_BOOK = "book_id";
	public static final String KEY_NAME = "name";
	public static final String KEY_COMMENT = "comment";
	public static final String KEY_TIME = "time";
	public static final String KEY_PAGE = "number_of_page";
	public static final String KEY_TYPE = "type";
	public static final String KEY_BOOK_TYPE = "book_type";

	private static final int DB_VERSION = 1;

	private static final String DATABASE_CREATE = "create table booknote "
			+ "(_id integer primary key autoincrement, "
			+ "book_id text not null, name text not null, "
			+ "comment text, time integer,number_of_page integer not null,type integer not null);";

	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	/**
	 * Constructor
	 * 
	 * @param ctx
	 *            application context
	 */
	public BookNote(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	/**
	 * Database helper class
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, "istudy.db", null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}

	/**
	 * open the bookmark database
	 * 
	 * @return the Bookmark object
	 * @throws SQLException
	 */
	public BookNote open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	/**
	 * close the database
	 */
	public void close() {
		DBHelper.close();
	}

	
	public ArrayList<BookMarkValue> getBookmarks(String bookId,int type) {
		ArrayList<BookMarkValue> list = new ArrayList<BookMarkValue>();

		Cursor cur = db.query(true, "booknote", new String[] { KEY_ID,KEY_BOOK, KEY_NAME,KEY_COMMENT,KEY_TIME,KEY_PAGE,KEY_TYPE },
				KEY_BOOK + "='" + bookId + "' and " + KEY_TYPE + "=" + type, null,
				null, null, "number_of_page asc", null);
		if (cur != null) {
			if (cur.moveToFirst()) {
				do {
					list.add(new BookMarkValue(Integer.parseInt(cur.getString(0)), cur.getString(1),
							cur.getString(2),cur.getString(3),Long.parseLong(cur.getString(4)),Integer.parseInt(cur.getString(5)),
									Integer.parseInt(cur.getString(6))));
				} while (cur.moveToNext());
			}
			cur.close();
		}
		
		return list;
	}
	
	public void deleteBookmark(int id) {
		
		db.delete("booknote", KEY_ID + "=" + id, null);
	}
	
	
	public void addBookNote(String bookId, String name,String comment,int numberOfPage,int type) {
		
		ContentValues cv = new ContentValues();
		cv.put(KEY_BOOK, bookId);
		cv.put(KEY_COMMENT, comment);
		cv.put(KEY_NAME, name);
		cv.put(KEY_TIME, System.currentTimeMillis() / 1000);
		cv.put(KEY_PAGE, numberOfPage);
		cv.put(KEY_TYPE, type);
		db.insert("booknote", null, cv);
		
		Log.i("mainmenu", "add book note success.");
	}
	
}

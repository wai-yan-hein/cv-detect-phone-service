package com.cv.coreservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static  final int DATABASE_VERSION =1;
    private static  final String DATABASE_NAME = "cv";
    private static  final String TABLE_NAME = "network";
    private static  final String COLUMN_ID = "id";
    private static  final String COLUMN_IP ="ip_address";
    private static  final String COLUMN_PORT ="port";
    private static final String TAG = "DatabaseHandler";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY ,"
                + COLUMN_IP + " TEXT,"
                + COLUMN_PORT + " INTEGER "
                + ")";
        Log.i(TAG,createTable);
        sqLiteDatabase.execSQL(createTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(sqLiteDatabase);
    }
    public void save(NetworkModel network){

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, network.getId());
            values.put(COLUMN_IP, network.getIpAddress());
            values.put(COLUMN_PORT, network.getPort());
            db.insert(TABLE_NAME, null, values);
            db.close();
            Log.i(TAG, "Saved. Ip : " + network.getIpAddress() + "-" + "port :" + network.getPort());

    }
    public void update(NetworkModel network){
        Integer id = network.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IP,network.getIpAddress());
        values.put(COLUMN_PORT,network.getPort());
        db.update(TABLE_NAME,values,COLUMN_ID+ "= ?",new String[]{String.valueOf(id)});
        Log.i(TAG, "Updated. Ip : " + network.getIpAddress() + "-" + "port :" + network.getPort());

    }
    public NetworkModel getNetwork(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID, COLUMN_IP, COLUMN_PORT},
                COLUMN_IP + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        NetworkModel note = new NetworkModel(
                cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_IP)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_PORT)));

        // close the db connection
        cursor.close();

        return note;
    }
}

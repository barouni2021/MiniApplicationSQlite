package com.example.miniapplicationsqlite2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper implements Serializable {

    //Version de la BD
    private static final int DATABASE_VERSION=1;
    //Nom de la BD
    private static final String DATABASE_NAME="DBContactFavoris";
    private static final String TABLE_NAME="contact";
    private static final String KEY_ID="id" ;
    private static final String KEY_NAME="nom" ;
    private static final String KEY_NUMBER="number" ;
    private SQLiteDatabase mDb;

    //Constructeur
    public DatabaseHandler(Context context)
    {
        super( context, DATABASE_NAME,null,DATABASE_VERSION );

    }
    //Ouvrir la base en écriture
    public SQLiteDatabase open() {
        mDb = this.getWritableDatabase();
        return mDb;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement, " +
                KEY_NAME + " text not null, " +
                KEY_NUMBER + " text not null)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }

    // Définitions des méthodes CRUD



    public long addContact(Contact c)
    {
        mDb = this.open();
        ContentValues v = new ContentValues();
        v.put(KEY_NAME, c.getNom());
        v.put(KEY_NUMBER, c.getNumero());
        long i = mDb.insert(TABLE_NAME, null,v);
        mDb.close();
        return i;

    }
    public Contact searchContact(Contact c)
    {
        String countQuery = "SELECT * FROM " + TABLE_NAME + " where id=?";
        mDb = this.getReadableDatabase();
        Cursor cursor = mDb.rawQuery(countQuery, new String[] {String.valueOf(c.getId())});
        if (cursor.getCount() == 0)
            return null;
        Contact oc = new Contact();
        if(cursor.moveToFirst())
        {
            oc.setNom(cursor.getString(1));
            oc.setNumero(cursor.getString(2));

        }
        cursor.close();
        mDb.close();
        return oc;
    }


    public List<Contact> getAllContact()
    {
        List <Contact> contactList = new ArrayList<Contact>();
        mDb = this.getReadableDatabase();
        Cursor cursor = mDb.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        if (cursor.moveToFirst())
            do {
                Contact oc = new Contact();
                oc.setId(Integer.parseInt(cursor.getString(0)));
                oc.setNom(cursor.getString(1));
                oc.setNumero(cursor.getString(2));
                contactList.add(oc);

            }while (cursor.moveToNext());
        cursor.close();
        mDb.close();
        return  contactList;
    }



    public int updateContact(Contact c)
    {
        mDb=this.open();
        ContentValues v = new ContentValues();
        v.put(KEY_NAME,c.getNom());
        v.put(KEY_NUMBER,c.getNumero());
        int i = mDb.update(TABLE_NAME,v,KEY_ID + " =?", new String[] {String.valueOf(c.getId())});
        mDb.close();
        return i;
    }
    public void deleteContact(Contact c)
    {
        mDb=this.open();
        mDb.delete(TABLE_NAME,KEY_ID + " =?", new String[] {String.valueOf(c.getId())});
        mDb.close();

    }
}
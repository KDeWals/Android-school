package com.example.myapplication.BDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UserAccessBdd {
    private static final int VERSION = 1;
    private static final String NAME_DB = "User.db";

    private static final String TABLE_USER = "table_user";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_LOGIN = "LOGIN";
    private static final int NUM_COL_LOGIN = 1;
    private static final String COL_PASSWORD = "PASSWORD";
    private static final int NUM_COL_PASSWORD = 2;
    private static final String COL_EMAIL = "EMAIL";
    private static final int NUM_COL_EMAIL = 3;

    private SQLiteDatabase db;
    private UserBddSqlite userdb;

    public UserAccessBdd(Context c){
        userdb = new UserBddSqlite(c, NAME_DB, null, VERSION);
    }

    public void openForWrite(){
        db = userdb.getWritableDatabase();
    }

    public void openForRead(){
        db = userdb.getReadableDatabase();
    }

    public void Close(){
        db.close();
    }

    public long insertUser(User u){
        ContentValues content = new ContentValues();
        content.put(COL_LOGIN, u.getLogin());
        content.put(COL_PASSWORD, u.getPassword());
        content.put(COL_EMAIL, u.getEmail());

        return db.insert(TABLE_USER, null, content);
    }


    public int updateUser(int i, User u){
        ContentValues content = new ContentValues();
        content.put(COL_LOGIN, u.getLogin());
        content.put(COL_PASSWORD, u.getPassword());
        content.put(COL_EMAIL, u.getEmail());

        return db.update(TABLE_USER, content, COL_ID + " = " + i, null);
    }

    public int removeUser(String login){
        return db.delete(TABLE_USER, COL_LOGIN + " = " + login, null);
    }

    public User getUser(String login){
        Cursor c = db.query(TABLE_USER, new String[] {
                COL_ID, COL_LOGIN, COL_PASSWORD, COL_EMAIL},
                COL_LOGIN + " LIKE \"" + login + "\"",
                null, null, null, COL_LOGIN);
        return cursorToUser(c);
        }

    private User cursorToUser(Cursor c) {
        if(c.getCount() == 0) {
            c.close();
            return null;
    }
        c.moveToFirst();

        User dbUser = new User();
        dbUser.setId(c.getInt(NUM_COL_ID));
        dbUser.setLogin(c.getString(NUM_COL_LOGIN));
        dbUser.setPassword(c.getString(NUM_COL_PASSWORD));
        dbUser.setEmail(c.getString(NUM_COL_EMAIL));
        c.close();
        return dbUser;
    }

    public ArrayList<User> getAllUsers(){
        Cursor c = db.query(TABLE_USER, new String[] {
                COL_ID, COL_LOGIN, COL_PASSWORD, COL_EMAIL },
                null, null, null, null, COL_LOGIN);

        ArrayList<User> tabUser = new ArrayList<User>();

        if(c.getCount() == 0){
            c.close();
            return tabUser;
        }

        while (c.moveToNext()){
            User lineUser = new User();
            lineUser.setId(c.getInt(NUM_COL_ID));
            lineUser.setLogin(c.getString(NUM_COL_LOGIN));
            lineUser.setPassword(c.getString(NUM_COL_PASSWORD));
            lineUser.setEmail(c.getString(NUM_COL_EMAIL));
            tabUser.add(lineUser);
        }
        c.close();
        return tabUser;
    }
}

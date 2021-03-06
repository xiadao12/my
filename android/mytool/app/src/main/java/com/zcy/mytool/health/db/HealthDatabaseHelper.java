package com.zcy.mytool.health.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HealthDatabaseHelper extends SQLiteOpenHelper {

    public static SQLiteDatabase db;

    public static final String TABLE_CREATE_health_record = "create table health_record (\n" +
            "    id integer not null primary key autoincrement,\n" +
            "    health_type integer not null,  -- 类型\n" +
            "    title varchar not null,\n" +
            "    create_time timestamp) -- 记录时间";

    public static final String TABLE_CREATE_health_record_food = "create table health_record_food (\n" +
            "    id integer not null primary key autoincrement,\n" +
            "    record_id integer not null)";

    public static final String TABLE_CREATE_health_record_exercise = "create table health_record_exercise (\n" +
            "    id integer not null primary key autoincrement,\n" +
            "    record_id integer not null)";

    /**
     * @param context 上下文
     */
    public HealthDatabaseHelper(Context context) {
        // 上下文；
        // 数据库文件的名称；
        // 用来创建cursor对象，默认为null；
        // 数据库的版本号，从1开始，如果发生改变，onUpgrade方法将会调用,4.0之后只能升不能降
        super(context, "health", null, 1);

        // 删除数据库
        // context.deleteDatabase("health");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // db.openOrCreateDatabase("/sql/health/create.sql", null);
        db.execSQL(TABLE_CREATE_health_record);
        db.execSQL(TABLE_CREATE_health_record_food);
        db.execSQL(TABLE_CREATE_health_record_exercise);
    }

    /**
     * 数据库版本号发生改变时才会执行； 特别适合做表结构的修改
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

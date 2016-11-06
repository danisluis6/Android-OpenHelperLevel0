package vn.udn.dut.openhelpera.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vuongluis on 06/11/2016.
 */

public class OpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DB_USER";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_ACCOUNT = "ACCOUNT";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ACC = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_NAME = "fullname";

    private static Context context;
    static SQLiteDatabase db;
    private OpenHelper openHelper;

    public OpenHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ACCOUNT + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ACC + " TEXT NOT NULL, "
                + COLUMN_PASSWORD + " TEXT NOT NULL, "
                + COLUMN_NAME + " TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        onCreate(sqLiteDatabase);
    }
}

package vn.udn.dut.openhelpera.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import vn.udn.dut.openhelpera.bean.User;
import vn.udn.dut.openhelpera.utils.OpenHelper;

/**
 * Created by vuongluis on 06/11/2016.
 */

public class ModelDemo {

    private Context context;
    private static SQLiteDatabase db;

    private static final String TABLE_ACCOUNT = "ACCOUNT";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ACC = "tentaikhoan";
    public static final String COLUMN_PASSWORD = "matkhau";
    public static final String COLUMN_NAME = "hoten";

    private OpenHelper mConnect;

    public ModelDemo(Context context){
        this.context = context;
    }

    public ModelDemo open() throws SQLException {
        mConnect = new OpenHelper(context);
        db = mConnect.getWritableDatabase();
        return this;
    }

    public void close() {
        mConnect.close();
    }

    // function new SQLiteDatabase().insert()
    public long addData(User item) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ACC, item.getUsername());
        cv.put(COLUMN_PASSWORD, item.getPassword());
        cv.put(COLUMN_NAME, item.getFullname());
        return db.insert(TABLE_ACCOUNT, null, cv);
    }

    // function new SQLiteDatebase().query()
    public String getData() {
        String[] columns = new String[]{COLUMN_ID, COLUMN_ACC, COLUMN_PASSWORD, COLUMN_NAME};
        Cursor c = db.query(TABLE_ACCOUNT, columns, null, null, null, null, null);
        String result = "";
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + " " + c.getString(c.getColumnIndex(COLUMN_ID))
                    + " - id:" + c.getString(c.getColumnIndex(COLUMN_ACC))
                    + " - pw:" + c.getString(c.getColumnIndex(COLUMN_NAME))
                    + " - ten:" + c.getString(c.getColumnIndex(COLUMN_PASSWORD)) + "\n";
        }
        c.close();
        return result;
    }

    public int deleteAcc(String username) {
        return db.delete(TABLE_ACCOUNT, COLUMN_ACC + "='" + username + "'", null);
    }

    public int deleteAccountAll() {
        return db.delete(TABLE_ACCOUNT, null, null);
    }
}

package vn.udn.dut.openhelpera.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.udn.dut.openhelpera.bean.User;
import vn.udn.dut.openhelpera.utils.OpenHelper;

/**
 * Created by vuongluis on 06/11/2016.
 */

public class ModelDemo {

    private Context context;
    private static SQLiteDatabase db;

    private static final String TABLE_ACCOUNT = "ACCOUNT";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ACC = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_NAME = "fullname";

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

    public long addItem(User item) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ACC, item.getUsername());
        cv.put(COLUMN_PASSWORD, item.getPassword());
        cv.put(COLUMN_NAME, item.getFullname());
        return db.insert(TABLE_ACCOUNT, null, cv);
    }

    public ArrayList<User> getList(){
        ArrayList<User> alUser = new ArrayList<>();
        String[] columns = new String[]{COLUMN_ID,COLUMN_ACC,COLUMN_PASSWORD,COLUMN_NAME};
        Cursor c = db.query(TABLE_ACCOUNT,columns,null,null,null,null,null);
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            alUser.add(new User(c.getString(c.getColumnIndex(COLUMN_NAME))));
        }
        c.close();
        return alUser;
    }

    public int delItem(String username) {
        return db.delete(TABLE_ACCOUNT, COLUMN_ACC + "='" + username + "'", null);
    }

    public int delAll() {
        return db.delete(TABLE_ACCOUNT, null, null);
    }
}

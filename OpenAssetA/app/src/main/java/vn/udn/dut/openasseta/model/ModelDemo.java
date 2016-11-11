package vn.udn.dut.openasseta.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import vn.udn.dut.openasseta.bean.Dictionary;
import vn.udn.dut.openasseta.utils.OpenAssets;

/**
 * Created by vuongluis on 06/11/2016.
 */

public class ModelDemo {

    private Context context;
    private static SQLiteDatabase db;

    private static final String TABLE_ACCOUNT = "tblDictionary";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CODE = "code";
    private static final String COLUMN_CONTENT = "content";

    private OpenAssets mConnect;

    public ModelDemo(Context context){
        this.context = context;
    }

    public ModelDemo open() throws SQLException {
        mConnect = new OpenAssets(context);
        db = mConnect.getWritableDatabase();
        return this;
    }

    public void close() {
        mConnect.close();
    }

    public long addItem(Dictionary item) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, item.getId());
        cv.put(COLUMN_CODE, item.getCode());
        cv.put(COLUMN_CONTENT, item.getContent());
        return db.insert(TABLE_ACCOUNT, null, cv);
    }

    public ArrayList<Dictionary> getList(){
        ArrayList<Dictionary> alDic = new ArrayList<>();
        String[] columns = new String[]{COLUMN_ID,COLUMN_CODE,COLUMN_CONTENT};
        Cursor c = db.query(TABLE_ACCOUNT,columns,null,null,null,null,null);
        int iID = c.getColumnIndex(COLUMN_ID);
        int iCODE = c.getColumnIndex(COLUMN_CODE);
        int iCONTENT = c.getColumnIndex(COLUMN_CONTENT);
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
             alDic.add(new Dictionary(c.getInt(iID),c.getString(iCODE),c.getString(iCONTENT)));
        }
        c.close();
        return alDic;
    }

    public int delItem(String username) {
        return db.delete(TABLE_ACCOUNT, COLUMN_ID + "='" + username + "'", null);
    }

    public int delAll() {
        return db.delete(TABLE_ACCOUNT, null, null);
    }
}

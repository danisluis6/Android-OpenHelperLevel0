# OPENHELPER

    - Step 1: Create utils/OpenHelper.java
    + Create a database with two 2 line

    private static final String DATABASE_NAME = "DB_USER";
    private static final int DATABASE_VERSION = 1;

    + Create a new table in database with commands

    private static final String TABLE_ACCOUNT = "ACCOUNT";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_ACC = "tentaikhoan";
    private static final String COLUMN_PASSWORD = "matkhau";
    private static final String COLUMN_NAME = "hoten";

    + Detail coding:

     	public class OpenHelper extends SQLiteOpenHelper {

	    private static final String DATABASE_NAME = "DB_USER";
	    private static final int DATABASE_VERSION = 1;

	    private static final String TABLE_ACCOUNT = "ACCOUNT";
	    private static final String COLUMN_ID = "_id";
	    private static final String COLUMN_ACC = "tentaikhoan";
	    private static final String COLUMN_PASSWORD = "matkhau";
	    private static final String COLUMN_NAME = "hoten";

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

    - Step 2: Direction development 

    + Create button to presentation functionality:
    + Create a new object to save database
    + View result 
    + Delete All data of database

    - Step 3: Design activity_main.xml

	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:tools="http://schemas.android.com/tools"
	    android:id="@+id/activity_main"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:paddingBottom="@dimen/activity_vertical_margin"
	    android:paddingLeft="@dimen/activity_horizontal_margin"
	    android:paddingRight="@dimen/activity_horizontal_margin"
	    android:paddingTop="@dimen/activity_vertical_margin"
	    android:orientation="vertical"
	    tools:context="vn.udn.dut.openhelpera.MainActivity">
	    <EditText
		android:id="@+id/edtUserName"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		android:padding="10dp"
		android:background="@android:color/transparent"
		android:hint="username" />

	    <EditText
		android:id="@+id/edtPass"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		android:padding="10dp"
		android:background="@android:color/transparent"
		android:hint="password" />

	    <EditText
		android:id="@+id/edtName"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		android:padding="10dp"
		android:background="@android:color/transparent"
		android:hint="name" />

	    <Button
		android:id="@+id/btnCreate"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		android:text="@string/tkmoi" />

	    <Button
		android:id="@+id/btnList"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		android:text="@string/danh_sach" />

	    <Button
		android:id="@+id/btnDeleteAll"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		android:text="@string/xoa_het" />
	    <Button
		android:id="@+id/btnLogin"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		android:text="Login" />
	    <TextView
		android:id="@+id/textView2"
		android:layout_width="match_parent"
		android:layout_height="wrap_content" />
	</LinearLayout>

    - Picture description
	![alt tag](https://github.com/danisluis6/Android-OpenHelperLevel0/blob/master/OpenHelperA/1.png)










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
	    <ListView
		android:id="@+id/lvUser"
		android:layout_width="match_parent"
		android:layout_height="wrap_content">
	    </ListView>
	</LinearLayout>

    - Picture description
![alt tag](https://github.com/danisluis6/Android-OpenHelperLevel0/blob/master/OpenHelperA/1.png)

    - Step 2: Create Object User in package bean

	public class User {
	    private String username;
	    private String password;
	    private String fullname;

	    public User() {
	    }

	    public User(String username, String password, String fullname) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
	    }

	    public String getUsername() {
		return username;
	    }

	    public String getPassword() {
		return password;
	    }

	    public String getFullname() {
		return fullname;
	    }

	    public void setUsername(String username) {
		this.username = username;
	    }

	    public void setPassword(String password) {
		this.password = password;
	    }

	    public void setFullname(String fullname) {
		this.fullname = fullname;
	    }
	}

    - Step 3: Create new adapter call ModelDemo

	package vn.udn.dut.openhelpera;

	import android.net.Uri;
	import android.support.v7.app.AppCompatActivity;
	import android.os.Bundle;
	import android.util.Log;
	import android.view.View;
	import android.widget.ArrayAdapter;
	import android.widget.Button;
	import android.widget.EditText;
	import android.widget.ListView;
	import android.widget.Toast;

	import com.google.android.gms.appindexing.Action;
	import com.google.android.gms.appindexing.AppIndex;
	import com.google.android.gms.appindexing.Thing;
	import com.google.android.gms.common.api.GoogleApiClient;

	import java.util.ArrayList;
	import java.util.List;

	import vn.udn.dut.openhelpera.adapter.ModelDemo;
	import vn.udn.dut.openhelpera.bean.User;

	import static android.R.layout.simple_list_item_1;

	public class MainActivity extends AppCompatActivity {

	private Button btnCreate, btnList, btnDeleteAll;
	private ListView lvUser;
	private EditText edtUser, edtPass, edtName;
	private Button mBtnLogin;

	private ModelDemo model = new ModelDemo(this);
	private ArrayList<User> listItems;
	private List<String> listUsername;
	/**
	* ATTENTION: This was auto-generated to implement the App Indexing API.
	* See https://g.co/AppIndexing/AndroidStudio for more information.
	*/
	private GoogleApiClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	lvUser = (ListView) findViewById(R.id.lvUser);

	edtUser = (EditText) findViewById(R.id.edtUser);
	edtPass = (EditText) findViewById(R.id.edtPass);
	edtName = (EditText) findViewById(R.id.edtName);

	btnCreate = (Button) findViewById(R.id.btnCreate);
	btnList = (Button) findViewById(R.id.btnList);
	btnDeleteAll = (Button) findViewById(R.id.btnDeleteAll);
	mBtnLogin = (Button) findViewById(R.id.btnLogin);

	listItems = new ArrayList<>();
	listUsername = new ArrayList<>();

	btnCreate.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
		model.open();
		model.addItem(new User(edtUser.getText().toString(), edtPass.getText().toString(), edtName.getText().toString()));
		model.close();
		resetForm();
	    }
	});

	btnList.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
		model.open();
		listItems = model.getList();
		for(int i = 0;i<listItems.size();i++){
		    listUsername.add(new String(listItems.get(i).getFullname()));
		}
		model.close();
		showListView(listUsername);
	    }
	});

	btnDeleteAll.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
		model.open();
		model.delAll();
		model.close();
	    }
	});
	// ATTENTION: This was auto-generated to implement the App Indexing API.
	// See https://g.co/AppIndexing/AndroidStudio for more information.
	client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
	}

	/**
	* ATTENTION: This was auto-generated to implement the App Indexing API.
	* See https://g.co/AppIndexing/AndroidStudio for more information.
	*/
	public Action getIndexApiAction() {
	Thing object = new Thing.Builder()
		.setName("Main Page") // TODO: Define a title for the content shown.
		// TODO: Make sure this auto-generated URL is correct.
		.setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
		.build();
	return new Action.Builder(Action.TYPE_VIEW)
		.setObject(object)
		.setActionStatus(Action.STATUS_TYPE_COMPLETED)
		.build();
	}

	@Override
	public void onStart() {
	super.onStart();

	// ATTENTION: This was auto-generated to implement the App Indexing API.
	// See https://g.co/AppIndexing/AndroidStudio for more information.
	client.connect();
	AppIndex.AppIndexApi.start(client, getIndexApiAction());
	}

	@Override
	public void onStop() {
	super.onStop();

	// ATTENTION: This was auto-generated to implement the App Indexing API.
	// See https://g.co/AppIndexing/AndroidStudio for more information.
	AppIndex.AppIndexApi.end(client, getIndexApiAction());
	client.disconnect();
	}

	public void showListView(List<String> list){
	ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
	lvUser.setAdapter(adapter);
	}

	public void resetForm(){
	edtUser.setText("");
	edtPass.setText("");
	edtName.setText("");
	}
	}

    - Step 4: Picture 
![alt tag](https://github.com/danisluis6/Android-OpenHelperLevel0/blob/master/OpenHelperA/2.png)









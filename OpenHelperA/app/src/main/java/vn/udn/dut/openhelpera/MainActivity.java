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

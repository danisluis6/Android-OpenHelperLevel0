package vn.udn.dut.openhelpera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import vn.udn.dut.openhelpera.adapter.ModelDemo;
import vn.udn.dut.openhelpera.bean.User;

public class MainActivity extends AppCompatActivity {

    private Button mBtnCreate, mBtnList, mBtnDeleteAll;
    private ListView lvUser;
    private EditText mEdtUserName;
    private EditText mEdtPass;
    private EditText mEdtName;
    private Button mBtnLogin;
    private ModelDemo model = new ModelDemo(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnCreate = (Button) findViewById(R.id.btnCreate);
        mBtnList = (Button) findViewById(R.id.btnList);
        mBtnDeleteAll = (Button) findViewById(R.id.btnDeleteAll);

        lvUser = (ListView) findViewById(R.id.lvUser);

        mEdtUserName = (EditText) findViewById(R.id.edtUserName);
        mEdtName = (EditText) findViewById(R.id.edtName);
        mEdtPass = (EditText) findViewById(R.id.edtPass);
        mBtnLogin = (Button) findViewById(R.id.btnLogin);

        mBtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.open();
                model.addData(new User(mEdtUserName.getText().toString(), mEdtPass.getText().toString(), mEdtName.getText().toString()));
                model.close();
            }
        });

        mBtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.open();
                String ds = model.getData();
                model.close();
                lvUser.setText(ds);
            }
        });

        mBtnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.open();
                model.deleteAccountAll();
                model.close();
            }
        });
    }
}

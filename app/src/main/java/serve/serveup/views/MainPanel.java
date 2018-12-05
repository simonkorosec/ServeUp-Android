package serve.serveup.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import serve.serveup.R;
import serve.serveup.dataholder.UserInfo;

public class MainPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_panel);


        Bundle getUserInfo = getIntent().getExtras();
        UserInfo userInfo = (UserInfo)getUserInfo.getSerializable("userInfo");

        TextView email = findViewById(R.id.email);
        TextView name = findViewById(R.id.name);
        TextView uid = findViewById(R.id.uid);

        email.setText(email.getText().toString() + " " + userInfo.getEmail());
        name.setText(name.getText().toString() + " " + userInfo.getDisplayName());
        uid.setText(uid.getText().toString() + " " + userInfo.getuID());



    }
}

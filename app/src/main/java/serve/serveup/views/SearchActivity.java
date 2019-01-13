package serve.serveup.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import serve.serveup.R;
import serve.serveup.utils.Utils;

public class SearchActivity extends AppCompatActivity {

    private ImageView backButton;
    private LinearLayout searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        backButton = findViewById(R.id.backButtonSearch);
        searchButton = findViewById(R.id.searchButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.showToast(getApplicationContext(), "Search function comes with PRO version:)");
            }
        });

    }
}

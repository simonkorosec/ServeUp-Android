package serve.serveup.views.order;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import serve.serveup.R;
import serve.serveup.dataholder.order.Order;
import serve.serveup.dataholder.session.Session;
import serve.serveup.utils.ContentStore;
import serve.serveup.utils.Utils;

public class PaymentOptionActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private View payButton;
    private View backToOrderIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_option);

        radioGroup = findViewById(R.id.radioGroup);
        payButton = findViewById(R.id.payButton);
        backToOrderIcon = findViewById(R.id.backToOrderIcon);

        backToOrderIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish(); }
        });

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                openDialog();
            }
        });

    }

    public void openDialog() {
        PayDialog payDialog = new PayDialog();
        payDialog.show(getSupportFragmentManager(), "pay dialog");
    }
}

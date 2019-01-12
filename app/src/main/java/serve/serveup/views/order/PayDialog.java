package serve.serveup.views.order;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

public class PayDialog extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder bulder = new AlertDialog.Builder(getActivity());
        bulder.setTitle("Plačilo");
        bulder.setMessage("Potrjujem plačilo");
        bulder.setPositiveButton("Plačaj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return bulder.create();
    }
}

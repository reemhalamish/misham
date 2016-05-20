package halamish.reem.referandum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by Re'em on 5/19/2016.
 */
public class ItemDesDialog extends AppCompatActivity {
    public static final String ITEM_DESCRIPTION_TAG = "item description";
    public static final String ITEM_TITLE_TAG = "item title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getIntent().getStringExtra(ITEM_TITLE_TAG));
        setContentView(R.layout.dialog_item_description);
        TextView tvDes = ((TextView) findViewById(R.id.tv_descriptiondialog_des));
        tvDes.setText(
                getIntent().getStringExtra(ITEM_DESCRIPTION_TAG)
        );
        tvDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

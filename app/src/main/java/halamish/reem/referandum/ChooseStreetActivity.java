package halamish.reem.referandum;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

/**
 * Created by Re'em on 5/19/2016.
 */
public class ChooseStreetActivity extends Activity {
    /**
     * Called when the activity is starting.  This is where most initialization
     * should go: calling {@link #setContentView(int)} to inflate the
     * activity's UI, using {@link #findViewById} to programmatically interact
     * with widgets in the UI, calling
     * {@link #managedQuery(Uri, String[], String, String[], String)} to retrieve
     * cursors for data being displayed, etc.
     * <p/>
     * <p>You can call {@link #finish} from within this function, in
     * which case onDestroy() will be immediately called without any of the rest
     * of the activity lifecycle ({@link #onStart}, {@link #onResume},
     * {@link #onPause}, etc) executing.
     * <p/>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     * @see #onStart
     * @see #onSaveInstanceState
     * @see #onRestoreInstanceState
     * @see #onPostCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_street);


        // wire the input textview
        final AutoCompleteTextView actvStreet = (AutoCompleteTextView) findViewById(R.id.actv_choose_street_input);
        ArrayAdapter<String> aa = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                StreetNeighbourhoodManager.getInstance().getAllStreets()
        );
        actvStreet.setAdapter(aa);





        // wire the button
        findViewById(R.id.btn_choose_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String street = actvStreet.getText().toString();
                if (StreetNeighbourhoodManager.getInstance().streetValid(street)) {
                    UserClientManager.getManager().setStreet(street);
                    ChooseStreetActivity.this.startActivity(
                            new Intent(
                                    ChooseStreetActivity.this,
                                    MainActivity.class
                            ));
                    ChooseStreetActivity.this.finish();

                } else {
                    Toast.makeText(ChooseStreetActivity.this, "רחוב זה לא קיים. אנא נסו שוב", Toast.LENGTH_SHORT).show();
                    // TODO language locale
                }
            }
        });




    }

    private void registerUserStreet(String street) {
        UserClientManager.getManager().setStreet(street);
    }

}

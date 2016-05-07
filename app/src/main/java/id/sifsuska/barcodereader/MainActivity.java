package id.sifsuska.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void mScan(View v) {
        //http://stackoverflow.com/posts/4094191/
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");

        try {
            startActivityForResult(intent, 0);
        }catch (android.content.ActivityNotFoundException e){
            Toast.makeText(MainActivity.this, "Error! Please install ZXing Barcode Reader first from Google Play", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                // Handle successful scan

                //Toast
                Toast.makeText(MainActivity.this, contents, Toast.LENGTH_SHORT).show();
                EditText editText = (EditText) findViewById(R.id.editText1);
                editText.setText(contents);

            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
            }
        }
    }


}

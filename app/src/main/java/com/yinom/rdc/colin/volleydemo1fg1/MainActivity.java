package com.yinom.rdc.colin.volleydemo1fg1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public static EditText ettNumber;
    public static Button btnQuery;
    public static TextView ttvProvince;
    public static TextView ttvCity;
    public static TextView ttvAreacode;
    public static TextView ttvZip;
    public static TextView ttvCompany;
    public static TextView ttvCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ettNumber = (EditText) findViewById(R.id.ettMnNum);
        btnQuery = (Button) findViewById(R.id.btnMnQuery);
        ttvProvince = (TextView) findViewById(R.id.ttvMnProvince);
        ttvCity = (TextView) findViewById(R.id.ttvMnCity);
        ttvAreacode = (TextView) findViewById(R.id.ttvMnAreacode);
        ttvZip = (TextView) findViewById(R.id.ttvMnZip);
        ttvCompany = (TextView) findViewById(R.id.ttvMnCompany);
        ttvCard = (TextView) findViewById(R.id.ttvCard);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volley_Get();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    //Volley与Activity生命周期的关联
    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getHttpQueues().cancelAll("abcGet");
    }

    private void volley_Get() {
        String url = ("http://apis.juhe.cn/mobile/get?phone=" + ettNumber.getText().toString() + "&key=1b27ee246b8392716f924692cc241d25");
        /*StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
            }
        });
        request.setTag("abcGet");
        MyApplication.getHttpQueues().add(request);
        */
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    ttvProvince.setText("Province:" + jsonObject.getString("province"));
                    ttvCity.setText("City:" + jsonObject.getString("city"));
                    ttvAreacode.setText("Areacode" + jsonObject.getString("areacode"));
                    ttvZip.setText("Zip:" + jsonObject.getString("zip"));
                    ttvCompany.setText("Company" + jsonObject.getString("company"));
                    ttvCard.setText("Card:" + jsonObject.getString("card"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
            }
        });
        request.setTag("abcGet");
        MyApplication.getHttpQueues().add(request);
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
}

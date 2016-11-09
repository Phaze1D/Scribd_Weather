package com.steadypathapp.david.scribdweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONObject;

import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements YahooNetworkTask.YahooNetworkTaskListener {

    public final static String EXTRA_RESPONE = "com.steadypathapp.david.XML_REPONSE";

    Button mButton;
    EditText mEditCity;
    TextView mError;

    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button)findViewById(R.id.sub_button);
        mEditCity = (EditText)findViewById(R.id.cityText);
        mError = (TextView)findViewById(R.id.errorText);
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);

    }


    public void onSubmit(View view){
        showProgress();
        new YahooNetworkTask(this).execute(mEditCity.getText().toString());
    }


    @Override
    public void onFinished(String city, Response<String> res) {

        XMLExtractor xml = new XMLExtractor(res.body());
        if(!xml.hasErrors()){
            Intent intent = new Intent(this, WeatherActivity.class);
            intent.putExtra(EXTRA_RESPONE, res.body());
            startActivity(intent);
        }else{

            mError.setText("City Not Found");
        }

        hideProgress();

    }


    private void showProgress(){
        mError.setText("");
        mButton.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress(){
        mButton.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
    }
}

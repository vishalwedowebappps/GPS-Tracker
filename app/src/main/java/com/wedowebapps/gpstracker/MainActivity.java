package com.wedowebapps.gpstracker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity implements MyTracker.ADLocationListener {


    TextView txtData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtData=(TextView) findViewById(R.id.txtData);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //ask for permission
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
        else{
            findLoc();
        }
    }
    private void findLoc(){
        new MyTracker(getApplicationContext(),this).track();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            findLoc();
        }
    }
    @Override
    public void whereIAM(WDLocation loc) {
        txtData.setText(""+loc.address.toString().trim());
        System.out.println(loc);
    }
}

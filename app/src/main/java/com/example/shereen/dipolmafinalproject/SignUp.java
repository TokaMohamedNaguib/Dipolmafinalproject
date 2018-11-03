package com.example.shereen.dipolmafinalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String sharedPrefName = "Location";
    String user_Data = "user_Data";
    double user_lat,user_lng;
    public static final String TAG="TEST";
    String user_name,user_email,user_password;
    int user_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSharedPreference();
        Button register = (Button) findViewById(R.id.registerbutton);
        final EditText address = (EditText) findViewById(R.id.signlocation);
        address.setText(user_lat+","+user_lng);

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,location.class);
                startActivity(intent);

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_user();
            }
        });
    }

//    public void add_user_Data()
//    {
//
//        EditText name = (EditText) findViewById(R.id.signname);
//        user_name = name.getText().toString();
//
//        EditText phone = (EditText) findViewById(R.id.signphone);
//        user_phone =Integer.parseInt(phone.getText().toString());
//
//        EditText email = (EditText) findViewById(R.id.signemail);
//        user_email = email.getText().toString();
//        Log.d(TAG, "save_user: " + user_email);
//
//        EditText password = (EditText) findViewById(R.id.signpassword);
//        user_password = password.getText().toString();
//
//        sharedPreferences = getSharedPreferences(user_Data, MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("username", user_name);
//        editor.putInt("user_phone", user_phone);
//        editor.putString("user_email", user_email);
//        editor.putString("user_password", user_password);
//
//        editor.commit();
//    }
    public void save_user()
    {
        EditText name = (EditText) findViewById(R.id.signname);
        user_name = name.getText().toString();
        name.setText(user_name);

        EditText phone = (EditText) findViewById(R.id.signphone);
        user_phone =Integer.parseInt(phone.getText().toString());

        EditText email = (EditText) findViewById(R.id.signemail);
        user_email = email.getText().toString();
        Log.d(TAG, "save_user: " + user_email);

        EditText password = (EditText) findViewById(R.id.signpassword);
        user_password = password.getText().toString();


        EditText address = (EditText) findViewById(R.id.signlocation);
        address.setText(user_lat+","+user_lng);


        User user = new User(user_name,user_phone ,user_lat,user_lng,user_email,user_password);

        sqlLiteHelper sqlLiteHelper = new sqlLiteHelper(SignUp.this);
        sqlLiteHelper.insert_user(user);

        Toast.makeText(SignUp.this,"Registered",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignUp.this , LoginActivity.class);
        startActivity(intent);

    }
    @Override
    public void onResume(){
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        //Log.d(TAG, "onPause: ");
        //EditText name = (EditText) findViewById(R.id.signname);
        //name.setText(user_name);

        //EditText phone = (EditText) findViewById(R.id.signphone);
        //phone.setText(user_phone);

        //EditText email = (EditText) findViewById(R.id.signemail);
        //email.setText(user_email);

        //EditText password = (EditText) findViewById(R.id.signpassword);
        //password.setText(user_password);
    }


    public void getSharedPreference()
    {
        sharedPreferences = getSharedPreferences(sharedPrefName,MODE_PRIVATE);
        String lat = sharedPreferences.getString("lat", "0.0");
        String lng = sharedPreferences.getString("lng" , "0.0");
        user_lat = Double.parseDouble(lat);
        user_lng = Double.parseDouble(lng);
    }

}

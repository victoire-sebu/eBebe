package com.mysebu.ebebe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {


    Button connex;
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        connex=(Button) findViewById(R.id.connect);
        register=(TextView) findViewById(R.id.singin);

        OnclickConnxion();
        OnclickRegister();
    }

    private void OnclickConnxion(){
        connex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void OnclickRegister(){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents=new Intent(WelcomeActivity.this,RegisterActivity.class);
                startActivity(intents);
            }
        });
    }
}

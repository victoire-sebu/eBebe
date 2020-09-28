package com.mysebu.ebebe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mysebu.ebebe.Utils.LoginDataClass;

public class LoginActivity extends AppCompatActivity {

    public Button homepg;
    EditText usernameEt, passwordEt;
    TextView txt_singin;

    public String userN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        homepg=(Button) findViewById(R.id.homepg);
        usernameEt=(EditText) findViewById(R.id.usename);
        passwordEt=(EditText) findViewById(R.id.password);

        txt_singin=(TextView) findViewById(R.id.txt_singin);



        OnclickListenerSingin();
    }
    public void OnLogin(View view){
        userN=usernameEt.getText().toString();
        String passW=passwordEt.getText().toString();
        String type="login";
        if(userN.equals("")||passW.equals("")){
            Toast.makeText(this,"Entrer mail ou mot de passe",Toast.LENGTH_SHORT).show();

        }else {
            LoginDataClass loginDataClass = new LoginDataClass(this);
            loginDataClass.execute(type,userN,passW);
        }

    }

    private void OnclickListenerSingin(){

        txt_singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}

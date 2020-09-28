package com.mysebu.ebebe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mysebu.ebebe.Utils.LoginDataClass;
import com.mysebu.ebebe.Utils.RegisterDataClass;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    EditText noms,sexe,mail,telephone,nom_service,fonction_service,password, confirmPass;
    EditText nationalite,adresse;
    TextView date_naissance;
    private TextView mDisplayDate,mDisplayDate2,mDisplayDate3;
    private static final String TAG="RegisterActivity";
    RadioButton sexeM,sexeF;

    //
    private DatePickerDialog.OnDateSetListener mDateSetListener3=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        }
    };
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //date

        mDisplayDate3=(TextView) findViewById(R.id.et_date_naissance);
        mDisplayDate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        RegisterActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener3,
                        year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        mDateSetListener3=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                Log.d(TAG,"onDateSet:"+dayOfMonth+"/"+month+"/"+year);
                String date=dayOfMonth+"/"+month+"/"+year;
                mDisplayDate3.setText(date);

            }
        };

        //
        sexeM=(RadioButton) findViewById(R.id.sexeM);
        sexeF=(RadioButton) findViewById(R.id.sexeF);

        noms =(EditText) findViewById(R.id.et_nom);

        date_naissance=(TextView) findViewById(R.id.et_date_naissance);
        mail=(EditText) findViewById(R.id.et_email);
        telephone=(EditText) findViewById(R.id.et_phone);
        nom_service=(EditText) findViewById(R.id.et_nom_service);
        fonction_service=(EditText) findViewById(R.id.et_fonction);
        password=(EditText) findViewById(R.id.et_password);
        confirmPass=(EditText) findViewById(R.id.et_conf_password);

        adresse=(EditText) findViewById(R.id.adresse_p);
        nationalite=(EditText) findViewById(R.id.nationalite_p);

    }
    public void OnRegister(View view){

        String str_noms =noms.getText().toString();
        String str_sexe=(sexeM.isChecked())?"Masculin":(sexeF.isChecked())?"Feminin":"";
        String str_date_naissance=date_naissance.getText().toString();
        String str_mail=mail.getText().toString();
        String str_telephone=telephone.getText().toString();
        String str_nom_service=nom_service.getText().toString();
        String str_fonction_service=fonction_service.getText().toString();
        String str_password=password.getText().toString();
        String str_confirmPass=confirmPass.getText().toString();

        String str_adresse=adresse.getText().toString();
        String str_nationalite=nationalite.getText().toString();

        String type="register";

        if(str_noms.equals("") || str_sexe.equals("") || str_date_naissance.equals("") || str_mail.equals("") ||
                str_telephone.equals("") || str_nom_service.equals("") || str_fonction_service.equals("") ||
                str_password.equals("") || str_confirmPass.equals("")){
            Toast.makeText(this,"Completer tout les champs vide",Toast.LENGTH_SHORT).show();
        }else  {


            RegisterDataClass registerDataClass = new RegisterDataClass(this);
            registerDataClass.execute(type,str_noms,str_sexe,str_date_naissance,str_mail,str_telephone,str_nom_service,str_fonction_service,str_password, str_confirmPass,str_adresse,str_nationalite);

            noms.setText("");
            sexe.setText("");
            date_naissance.setText("");
            mail.setText("");
            telephone.setText("");
            nom_service.setText("");
            fonction_service.setText("");
            password.setText("");
            confirmPass.setText("");

            adresse.setText("");
            nationalite.setText("");

            Intent intent= new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
        }

    }
}

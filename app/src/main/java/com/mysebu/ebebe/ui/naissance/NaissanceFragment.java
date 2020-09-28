package com.mysebu.ebebe.ui.naissance;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mysebu.ebebe.R;
import com.mysebu.ebebe.Utils.LoginDataClass;
import com.mysebu.ebebe.Utils.NaissanceDataClass;
import com.mysebu.ebebe.Utils.UploadImages;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class NaissanceFragment extends Fragment {


    EditText noms_enfant,nom_hopital,lieu_naissance,nom_pere,nom_mere,prenom_pere,prenom_mere,phone_respo,mail_respo;
    Button bt_declarer;
    TextView date_naissance,age_pere, age_mere;
    EditText  nationalite_pere, nationalite_mere, adresse_pere, adresse_mere, profession_pere, profession_mere;
    RadioButton sexeM,sexeF;

    ImageView imageSelect;
    public static final int CAMERA_PERM_CODE=101;
    public static final int CAMERA_REQUEST_CODE = 102;
    Button bt_camera;
    Bitmap bitmap;
    private Uri imagePath;
    private String UploadUrl="http://192.168.8.100/ebebe/uploadImage.php";

    //date
    private Context mContext;

    private static final String TAG="NaissanceActivity";
    private TextView mDisplayDate,mDisplayDate2,mDisplayDate3;
    private DatePickerDialog.OnDateSetListener mDateSetListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        }
    };
    //
    private DatePickerDialog.OnDateSetListener mDateSetListener2=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        }
    };
    //
    private DatePickerDialog.OnDateSetListener mDateSetListener3=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        }
    };
    //

    private NaissanceViewModel naissanceViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        naissanceViewModel = ViewModelProviders.of(this).get(NaissanceViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_naissance, container, false);
        //final TextView textView = root.findViewById(R.id.text_gallery);
        naissanceViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);
            }
        });


        //date

        mDisplayDate=(TextView) root.findViewById(R.id.b_date_naissance);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                Log.d(TAG,"onDateSet:"+dayOfMonth+"/"+month+"/"+year);
                String date=dayOfMonth+"/"+month+"/"+year;
                mDisplayDate.setText(date);

            }
        };

        //
        //date

        mDisplayDate2=(TextView) root.findViewById(R.id.b_date_naissance_pere);
        mDisplayDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener2,
                        year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        mDateSetListener2=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                Log.d(TAG,"onDateSet:"+dayOfMonth+"/"+month+"/"+year);
                String date=dayOfMonth+"/"+month+"/"+year;
                mDisplayDate2.setText(date);

            }
        };

        //
        //date

        mDisplayDate3=(TextView) root.findViewById(R.id.b_date_naissance_mere);
        mDisplayDate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        getActivity(),
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

        sexeM=(RadioButton)root.findViewById(R.id.sexeM);
        sexeF=(RadioButton) root.findViewById(R.id.sexeF);

        noms_enfant=(EditText) root.findViewById(R.id.b_noms_enfant);
        date_naissance=(TextView) root.findViewById(R.id.b_date_naissance);
        nom_hopital=(EditText) root.findViewById(R.id.b_nom_hopital);
        lieu_naissance=(EditText) root.findViewById(R.id.b_lieu_naissance);
        nom_pere=(EditText) root.findViewById(R.id.b_nom_pere);
        nom_mere=(EditText) root.findViewById(R.id.b_nom_mere);
        phone_respo=(EditText) root.findViewById(R.id.b_telephone);
        mail_respo=(EditText) root.findViewById(R.id.b_mail);
        prenom_pere=(EditText) root.findViewById(R.id.b_prenom_pere);
        prenom_mere=(EditText) root.findViewById(R.id.b_prenom_mere);

        age_pere=(TextView) root.findViewById(R.id.b_date_naissance_pere);
        age_mere=(TextView) root.findViewById(R.id.b_date_naissance_mere);
        nationalite_pere=(EditText) root.findViewById(R.id.nationalite_pere);
        nationalite_mere=(EditText) root.findViewById(R.id.nationalite_mere);
        adresse_pere=(EditText) root.findViewById(R.id.adresse_pere);
        adresse_mere=(EditText) root.findViewById(R.id.adresse_mere);
        profession_pere=(EditText) root.findViewById(R.id.profession_pere);
        profession_mere=(EditText) root.findViewById(R.id.profession_mere);

        imageSelect=(ImageView) root.findViewById(R.id.image_attestation);
        bt_camera=(Button) root.findViewById(R.id.bt_cameraAt);

        bt_declarer=(Button) root.findViewById(R.id.b_declarer);

        // test camera
       // bt_other=(Button) root.findViewById(R.id.bt_other);
       // bt_other.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View v) {
        //        uploadImage();
        //    }
       // });
        //

        bt_declarer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                naissanceViewModel.getText();
                String str_noms_enfant=noms_enfant.getText().toString();

                String str_sexe=(sexeM.isChecked())?"Masculin":(sexeF.isChecked())?"Feminin":"";

                String str_date_naissance=date_naissance.getText().toString();
                String str_nom_hopital=nom_hopital.getText().toString();
                String str_lieu_naissance=lieu_naissance.getText().toString();
                String str_noms_pere=nom_pere.getText().toString()+" "+prenom_pere.getText().toString();
                String str_noms_mere=nom_mere.getText().toString()+" "+prenom_mere.getText().toString();
                String str_mail=mail_respo.getText().toString();
                String str_telephone=phone_respo.getText().toString();

                String str_age_pere=age_pere.getText().toString();
                String str_age_mere=age_mere.getText().toString();
                String str_nationalite_pere=nationalite_pere.getText().toString();
                String str_nationalite_mere=nationalite_mere.getText().toString();
                String str_adresse_pere=adresse_pere.getText().toString();
                String str_adresse_mere=adresse_mere.getText().toString();
                String str_profession_pere=profession_pere.getText().toString();
                String str_profession_mere=profession_mere.getText().toString();




                String type="declarer_enfant";

                if(str_noms_enfant.equals("") || str_sexe.equals("") || str_date_naissance.equals("") || str_mail.equals("") ||
                        str_telephone.equals("") || str_nom_hopital.equals("") || str_noms_mere.equals("") ||
                        str_noms_pere.equals("") || str_lieu_naissance.equals("")){
                    Toast.makeText(getActivity(),"Remplissez les cases vide",Toast.LENGTH_SHORT).show();
                }else {
                    //Toast.makeText(getActivity(),str_sexe.toString(),Toast.LENGTH_SHORT).show();
                    uploadImage();
                    NaissanceDataClass naissanceDataClass = new NaissanceDataClass(getActivity());
                    naissanceDataClass .execute(type,str_noms_enfant,str_sexe,str_date_naissance,str_nom_hopital,str_lieu_naissance,str_noms_pere,str_noms_mere,str_telephone,str_mail,str_age_pere,str_age_mere,str_nationalite_pere,str_nationalite_mere,str_adresse_pere,str_adresse_mere,str_profession_pere,str_profession_mere);


                    imageSelect.setVisibility(View.GONE);
                    noms_enfant.setText("");
                    date_naissance.setText("");
                    nom_hopital.setText("");
                    lieu_naissance.setText("");
                    nom_pere.setText("");
                    nom_mere.setText("");
                    phone_respo.setText("");
                    mail_respo.setText("");
                    prenom_pere.setText("");
                    prenom_mere.setText("");

                    age_pere.setText("");
                    age_mere.setText("");
                    nationalite_pere.setText("");
                    nationalite_mere.setText("");
                    adresse_pere.setText("");
                    adresse_mere.setText("");
                    profession_pere.setText("");
                    profession_mere.setText("");
                }

            }
        });


        ///camera
        bt_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermission();
            }
        });
        ///

        return root;
    }

    ////camera
    private void askCameraPermission(){
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.CAMERA},CAMERA_PERM_CODE);
        }else{
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==CAMERA_PERM_CODE){
            if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                openCamera();
            }else {
                Toast.makeText(getActivity(),"La permission est requise pour utiliser la camera",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCamera(){
        Intent camera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==CAMERA_REQUEST_CODE){
            bitmap=(Bitmap) data.getExtras().get("data");
           // imagePath=data.getData();
            imageSelect.setImageBitmap(bitmap);
            imageSelect.setVisibility(View.VISIBLE);
           // Toast.makeText(getActivity(),imagePath.toString(),Toast.LENGTH_SHORT).show();
        }
    }
    ////

    ///upload image
    private void uploadImage(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, UploadUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String Response=jsonObject.getString("response");
                    Toast.makeText(getActivity(),Response,Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("name",noms_enfant.getText().toString().trim());
                params.put("image",imageToString(bitmap));

                return params;
            }
        };
        UploadImages.getInstance(getActivity()).addToResquestQue(stringRequest);

    }

    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes=byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }
}
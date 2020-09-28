package com.mysebu.ebebe.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.mysebu.ebebe.LoginActivity;
import com.mysebu.ebebe.MainActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class RegisterDataClass extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;
    public String testVariable;

    public RegisterDataClass(Context ctx){

        context=ctx;
    }

    @Override
    protected String doInBackground(String... voids) {

        String type=voids[0];
        String register_url="http://192.168.8.100/ebebe/insertPersonnel.php";
        if(type.equals("register")){

            try {
                String noms=voids[1];
                String sexe=voids[2];
                String date_naissance=voids[3];
                String mail=voids[4];
                String telephone=voids[5];
                String nom_service=voids[6];
                String fonction_service=voids[7];
                String password=voids[8];

                String adresse=voids[9];
                String nationalite=voids[10];

                URL url=new URL(register_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("noms","UTF-8")+"="+URLEncoder.encode(noms,"UTF-8")+"&"
                        + URLEncoder.encode("sexe","UTF-8")+"="+URLEncoder.encode(sexe,"UTF-8")+"&"
                        + URLEncoder.encode("date_naissance","UTF-8")+"="+URLEncoder.encode(date_naissance,"UTF-8")+"&"
                        + URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(mail,"UTF-8")+"&"
                        + URLEncoder.encode("telephone","UTF-8")+"="+URLEncoder.encode(telephone,"UTF-8")+"&"
                        + URLEncoder.encode("nom_service","UTF-8")+"="+URLEncoder.encode(nom_service,"UTF-8")+"&"
                        + URLEncoder.encode("fonction_service","UTF-8")+"="+URLEncoder.encode(fonction_service,"UTF-8")+"&"
                        + URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                        + URLEncoder.encode("adresse","UTF-8")+"="+URLEncoder.encode(adresse,"UTF-8")+"&"
                        + URLEncoder.encode("nationalite","UTF-8")+"="+URLEncoder.encode(nationalite,"UTF-8")+"&";
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    result+=line;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Info Enregitrement");

    }

    @Override
    protected void onPostExecute(String resulta) {

        if(resulta.equals("Insertion success !")){
            Toast.makeText(context,resulta,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Problème connexion\nVériffier votre connexion internet",Toast.LENGTH_LONG).show();
            //new  AlertDialog.Builder(context).setMessage(resulta).show();

        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

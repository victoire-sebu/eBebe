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

public class NaissanceDataClass extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;
    public String testVariable;

    public NaissanceDataClass(Context ctx){

        context=ctx;
    }

    @Override
    protected String doInBackground(String... voids) {

        String type=voids[0];
        String declaration_url="http://192.168.8.100/ebebe/insertdeclaration.php";
        if(type.equals("declarer_enfant")) {

            try {
                String noms_enfant = voids[1];
                String sexe = voids[2];
                String date_naissance = voids[3];
                String nom_hopital = voids[4];
                String lieu_naissance = voids[5];
                String noms_pere = voids[6];
                String noms_mere = voids[7];
                String telephone = voids[8];
                String mail = voids[9];

                String age_pere = voids[10];
                String age_mere = voids[11];
                String nationalite_pere = voids[12];
                String nationalite_mere = voids[13];
                String adresse_pere = voids[14];
                String adresse_mere = voids[15];
                String profession_pere = voids[16];
                String profession_mere = voids[17];


                URL url = new URL(declaration_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("noms_enfant", "UTF-8") + "=" + URLEncoder.encode(noms_enfant, "UTF-8") + "&"
                        + URLEncoder.encode("sexe", "UTF-8") + "=" + URLEncoder.encode(sexe, "UTF-8") + "&"
                        + URLEncoder.encode("date_naissance", "UTF-8") + "=" + URLEncoder.encode(date_naissance, "UTF-8") + "&"
                        + URLEncoder.encode("nom_hopital", "UTF-8") + "=" + URLEncoder.encode(nom_hopital, "UTF-8") + "&"
                        + URLEncoder.encode("lieu_naissance", "UTF-8") + "=" + URLEncoder.encode(lieu_naissance, "UTF-8") + "&"
                        + URLEncoder.encode("noms_pere", "UTF-8") + "=" + URLEncoder.encode(noms_pere, "UTF-8") + "&"
                        + URLEncoder.encode("noms_mere", "UTF-8") + "=" + URLEncoder.encode(noms_mere, "UTF-8") + "&"
                        + URLEncoder.encode("telephon_respo", "UTF-8") + "=" + URLEncoder.encode(telephone, "UTF-8") + "&"
                        + URLEncoder.encode("mail_respo", "UTF-8") + "=" + URLEncoder.encode(mail, "UTF-8") + "&"

                        + URLEncoder.encode("age_pere", "UTF-8") + "=" + URLEncoder.encode(age_pere, "UTF-8") + "&"
                        + URLEncoder.encode("age_mere", "UTF-8") + "=" + URLEncoder.encode(age_mere, "UTF-8") + "&"
                        + URLEncoder.encode("nationalite_pere", "UTF-8") + "=" + URLEncoder.encode(nationalite_pere, "UTF-8") + "&"
                        + URLEncoder.encode("nationalite_mere", "UTF-8") + "=" + URLEncoder.encode(nationalite_mere, "UTF-8") + "&"
                        + URLEncoder.encode("adresse_pere", "UTF-8") + "=" + URLEncoder.encode(adresse_pere, "UTF-8") + "&"
                        + URLEncoder.encode("adresse_mere", "UTF-8") + "=" + URLEncoder.encode(adresse_mere, "UTF-8") + "&"
                        + URLEncoder.encode("profession_pere", "UTF-8") + "=" + URLEncoder.encode(profession_pere, "UTF-8") + "&"
                        + URLEncoder.encode("profession_mere", "UTF-8") + "=" + URLEncoder.encode(profession_mere, "UTF-8") + "&";
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;

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
        alertDialog.setTitle("Info Login");

    }

    @Override
    protected void onPostExecute(String resulta) {

        if(resulta.equals("Declaration success !")){
            Toast.makeText(context,resulta,Toast.LENGTH_SHORT).show();

        }else {
           // Toast.makeText(context,"Problème connexion\nVériffier votre connexion internet",Toast.LENGTH_LONG).show();
            new  AlertDialog.Builder(context).setMessage(resulta).show();

        }


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

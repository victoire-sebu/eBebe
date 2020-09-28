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

public class LoginDataClass extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;
    public String testVariable;

    public LoginDataClass(Context ctx){

        context=ctx;
    }

    @Override
    protected String doInBackground(String... voids) {

        String type=voids[0];
        String login_url="http://192.168.8.100/ebebe/login.php";
        if(type.equals("login")){
            try {
                String user_name=voids[1];
                String pass_word=voids[2];

                URL url=new URL(login_url);

                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+URLEncoder.encode("pass_word","UTF-8")+"="+URLEncoder.encode(pass_word,"UTF-8");
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
        alertDialog.setTitle("Info Login");

    }

    @Override
    protected void onPostExecute(String resulta) {

        if(resulta.equals("login success")){
           Intent intent=new Intent((LoginActivity)context, MainActivity.class);
            intent.putExtra("myemail",((LoginActivity) context).userN);
            context.startActivity(intent);

            ((LoginActivity) context).finish();

            // ((MainActivity)context).startActivity(new Intent(context,Home.class));


        }else {
            Toast.makeText(context,"Problème connexion\nVériffier votre connexion internet",Toast.LENGTH_LONG).show();
           // new  AlertDialog.Builder(context).setMessage(resulta).show();

        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

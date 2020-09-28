package com.mysebu.ebebe.ui.listdeclaration;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysebu.ebebe.MainActivity;
import com.mysebu.ebebe.R;
import com.mysebu.ebebe.Utils.DeclarationAdapter;
import com.mysebu.ebebe.Utils.ListDeclarerationClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.w3c.dom.Document;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class ListdeclarationFragment extends Fragment {

    View root;
    private static Context mContext;
    private ListdeclarationViewModel slideshowViewModel;
    ListView listView;
    List<ListDeclarerationClass> listDeclaration;
    private static final String urlListDecl="http://192.168.8.100/ebebe/listsdeclaration.php";

    TextView noms_enfant,sexe,date_naissance,date_register,nom_hopital,lieu_naissance,nom_pere,nom_mere;
    TextView age_pere,age_mere,nationalite_pere,nationalite_mere,adresse_pere,adresse_mere,profession_pere,profession_mere;


    //imprimer
    Bitmap bmp,scaldbmp;
    int pageWidh=600,pageHeigh=860;


    //

    //itext
    private static final int STORAGE_CODE = 1000;
    String mTextEt="je suis une personne respectable ayant des embution surpassant même la nature de l'être humaine nomal. pour tous les reste je continue à travailler dur pour atteindre l'excelence";



    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(ListdeclarationViewModel.class);
        root = inflater.inflate(R.layout.fragment_listdeclaration, container, false);

        bmp= BitmapFactory.decodeResource(getResources(),R.drawable.kinlogo);
        scaldbmp=Bitmap.createScaledBitmap(bmp,75,80,false);


        listView=(ListView) root.findViewById(R.id.list_decl);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(),listDeclaration.get(position).getNoms_enfant()+"\n"+listDeclaration.get(position).getDate_naissance(),Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                View view1=layoutInflater.inflate(R.layout.dialog_declaration_info,null);
                noms_enfant=(TextView) view1.findViewById(R.id.d_Tv_nomsEnf);
                sexe=(TextView) view1.findViewById(R.id.d_Tv_sexe);
                date_naissance=(TextView) view1.findViewById(R.id.d_Tv_date_naissance);
                nom_hopital=(TextView) view1.findViewById(R.id.d_Tv_nom_hopital);
                lieu_naissance=(TextView) view1.findViewById(R.id.d_Tv_lieu_naissance);
                nom_pere=(TextView) view1.findViewById(R.id.d_Tv_nomPere);
                nom_mere=(TextView) view1.findViewById(R.id.d_Tv_nomsMere);
                age_pere=(TextView) view1.findViewById(R.id.d_Tv_age_pere);
                age_mere=(TextView) view1.findViewById(R.id.d_Tv_age_mere);
                nationalite_pere=(TextView) view1.findViewById(R.id.d_Tv_nationalite_pere);
                nationalite_mere=(TextView) view1.findViewById(R.id.d_Tv_nationalite_mere);
                adresse_pere=(TextView) view1.findViewById(R.id.d_Tv_adresse_pere);
                adresse_mere=(TextView) view1.findViewById(R.id.d_Tv_adresse_mere);
                profession_pere=(TextView) view1.findViewById(R.id.d_Tv_profession_pere);
                profession_mere=(TextView) view1.findViewById(R.id.d_Tv_profession_mere);

                noms_enfant.setText(listDeclaration.get(position).getNoms_enfant());
                sexe.setText(listDeclaration.get(position).getSexe());
                date_naissance.setText(listDeclaration.get(position).getDate_naissance());
                nom_hopital.setText(listDeclaration.get(position).getNom_hopital());
                lieu_naissance.setText(listDeclaration.get(position).getLieu_naissance());
                nom_pere.setText(listDeclaration.get(position).getNom_pere());
                nom_mere.setText(listDeclaration.get(position).getNom_mere());
                age_pere.setText(listDeclaration.get(position).getAge_pere());
                age_mere.setText(listDeclaration.get(position).getAge_mere());
                nationalite_pere.setText(listDeclaration.get(position).getNationalite_pere());
                nationalite_mere.setText(listDeclaration.get(position).getNationalite_mere());
                adresse_pere.setText(listDeclaration.get(position).getAdresse_pere());
                adresse_mere.setText(listDeclaration.get(position).getAdresse_mere());
                profession_pere.setText(listDeclaration.get(position).getProfession_pere());
                profession_mere.setText(listDeclaration.get(position).getProfession_mere());
                builder.setView(view1)
                        //.setTitle("Declaration info")
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("Imprimer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //creatPDF();

                               /*
                                //we need to handle runtime permission for devices with marshmallow and above
                                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                                    //system OS >= Marshmallow(6.0), check if permission is enabled or not
                                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                            PackageManager.PERMISSION_DENIED){
                                        //permission was not granted, request it
                                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                                        requestPermissions(permissions, STORAGE_CODE);
                                    }
                                    else {
                                        //permission already granted, call save pdf method
                                        savePdf();
                                    }
                                }
                                else {
                                    //system OS < Marshmallow, call save pdf method
                                    savePdf();
                                }

                                */
                               savePdf();
                            }


                        });
                builder.create();
                builder.show();



            }
        });


        return root;
    }

    /*
    ///itext
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case STORAGE_CODE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission was granted from popup, call savepdf method
                    savePdf();
                }
                else {
                    //permission was denied from popup, show error message
                    //Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

     */

    private void savePdf() {
        //create object of Document class
        Document mDoc = new Document(PageSize.A4);
       // mDoc.setMargins(36,72,108,180);

        //pdf file name
        String myday = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(System.currentTimeMillis());
        String mFileName=noms_enfant.getText().toString()+"_"+myday;

        String onDay = new SimpleDateFormat("dd/MM/yyyy",
                Locale.getDefault()).format(System.currentTimeMillis());
        //pdf file path
        String mFilePath = Environment.getExternalStorageDirectory() + "/" + mFileName + ".pdf";



        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.kinlogo);
        ByteArrayOutputStream stream=new ByteArrayOutputStream();

        bitmap=Bitmap.createScaledBitmap(bmp,75,80,false);
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        Image img=null;
        byte[] bytesArray=stream.toByteArray();


        try {


            img=Image.getInstance(bytesArray);
            img.setAbsolutePosition(45f,680f);
            img.scalePercent(100f);




            //create instance of PdfWriter class
            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));
            //open the document for writing
            mDoc.open();
            //get text from EditText i.e. mTextEt

            String mText = mTextEt;



            Paragraph p=new Paragraph("P.No:00980051 / H.No:A2893823",new Font(Font.FontFamily.HELVETICA,10));
            p.setAlignment(Element.ALIGN_RIGHT);
            mDoc.add(p);

            Chunk c00=new Chunk("REPUBIQUE DEMOCRATIQUE");
            c00.setTextRise(15f);
            mDoc.add(new Paragraph(c00));

            /*
            Rectangle rectangle=new Rectangle(0,806,36,842);
            rectangle.setBackgroundColor(BaseColor.BLACK);
            mDoc.add(rectangle);

             */


            Chunk c01=new Chunk("\t\tDU CONGO \n\n\n\n\n");
            c01.setTextRise(15f);
            mDoc.add(new Paragraph(c01));

            Chunk c=new Chunk("Ville de Kinshasa");
            mDoc.add(new Paragraph(c));

            Chunk c2=new Chunk("Commune de Lemba");
            mDoc.add(new Paragraph(c2));

            Chunk c1=new Chunk("Service de l'Etat-Civil");
            c1.setUnderline(0.2f,-2f);
            mDoc.add(new Paragraph(c1));

            Chunk c3=new Chunk("ACTE N°: ....");
            mDoc.add(new Paragraph(c3));

            Chunk c4=new Chunk("FOLIO N°: ....");
            mDoc.add(new Paragraph(c4));

            Chunk c5=new Chunk("VOLUME N°: ....");
            mDoc.add(new Paragraph(c5));


            Paragraph p1=new Paragraph("ACTE DE NAISSANCE",new Font(Font.FontFamily.HELVETICA,14,Typeface.BOLD));
            p1.setAlignment(Element.ALIGN_CENTER);

            //p1.setPaddingTop(50f);//***
            mDoc.add(p1);

            Paragraph p2=new Paragraph("\nEn date du "+date_naissance.getText().toString()+" à ***h,\nPar devant nous, ABDOUL KARIM Bernard," +
                    " Bourgmestre et Officier de l'Etat-Civil de la Commune de XXXXX \nA comparru:\n\n" +
                    "Mr "+nom_pere.getText().toString()+" en qualité de residence "+adresse_pere.getText().toString()+", né(e) à xxxx en" +
                    " date du" +age_pere.getText().toString()+", profession "+profession_pere.getText().toString()+"\nLequel(Laquelle) nous a déclaré que\n\n" +
                    "Qu'en date du "+date_naissance+" à **h, est né(e) à "+lieu_naissance.getText().toString()+" un enfant de sexe "+sexe.getText().toString()+"" +
                    ", nommé(e) "+noms_enfant.getText().toString()+" fils(fille) de "+nom_pere.getText().toString()+"né(e) à xxxx le "+age_pere.getText().toString()+"" +
                    " de nationalité "+nationalite_pere.getText().toString()+" de profession "+profession_pere.getText().toString()+"" +
                    "Résident à "+adresse_pere.getText().toString()+" Et de Mme "+nom_mere.getText().toString()+" né(e) à XXXX le "+adresse_mere.getText().toString()+"" +
                    " de nationalité "+nationalite_mere.getText().toString()+" de profession "+profession_mere.getText().toString()+"" +
                    " Résident à "+adresse_mere.getText().toString()+"\n\nLecture de l'acte à été faite ou connaissance de l'acte a été donnéeou traduction" +
                    " de l'acte a été faite en Français, langue que nous connaissons ou par interpète(*).\n\nEn foi de quoi, avons dresé le présent acte et après" +
                    " connaissance en ait été donnée aux comparant nous l'avons signé avec eux.\n\n" ,new Font(Font.FontFamily.HELVETICA,10));
            p2.setAlignment(Element.ALIGN_JUSTIFIED);
            p2.setIndentationLeft(pageWidh/4);//****
            mDoc.add(p2);

            Paragraph p3=new Paragraph("Fait à  "+lieu_naissance.getText().toString()+", le "+onDay,new Font(Font.FontFamily.HELVETICA,10));
            p3.setAlignment(Element.ALIGN_RIGHT);
            mDoc.add(p3);

            Paragraph p4=new Paragraph("Singature du comparant \n "+nom_pere.getText().toString().toUpperCase(),new Font(Font.FontFamily.HELVETICA,10));
            p4.setAlignment(Element.ALIGN_LEFT);
            p4.setIndentationLeft(pageWidh/4);
            mDoc.add(p4);

            Paragraph p5=new Paragraph(" Le Bourgmestre ",new Font(Font.FontFamily.HELVETICA,10));
            p5.setAlignment(Element.ALIGN_RIGHT);
            mDoc.add(p5);

            Paragraph p6=new Paragraph("(*) Biffer les mentions unitils ",new Font(Font.FontFamily.HELVETICA,10));
            p6.setAlignment(Element.ALIGN_BOTTOM);
            p6.setIndentationLeft(pageWidh/4);
            mDoc.add(p6);


            //add author of the document (optional)
            mDoc.addAuthor("eBebe");

            mDoc.add(img);


            //add paragraph to the document
                        //
            mDoc.addTitle("Acte de naissance");




            //close the document
            mDoc.close();
            //show message that file is saved, it will show file name and file path too
           // Toast.makeText(this, mFileName +".pdf\nis saved to\n"+ mFilePath, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            //if any thing goes wrong causing exception, get and show exception message
            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

//end




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listDeclaration=new ArrayList<>();

    }

    @Override
    public void onStart() {
        super.onStart();
        showListDecla();
    }

    private void showListDecla(){
        StringRequest stringRequest=new StringRequest(Request.Method.GET, urlListDecl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("data",response);
                        try {

                            JSONArray array = new JSONArray(response);
                            listDeclaration.clear();
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);
                                listDeclaration.add(new ListDeclarerationClass(jsonObject.getString("noms_enfant"),jsonObject.getString("sexe"),jsonObject.getString("date_naissance"),jsonObject.getString("date_register"),jsonObject.getString("nom_hopital"),
                                        jsonObject.getString("lieu_naissance"),jsonObject.getString("nom_pere"),jsonObject.getString("nom_mere"),jsonObject.getString("telephon_respo"),jsonObject.getString("mail_respo"),
                                        jsonObject.getString("age_pere"),jsonObject.getString("age_mere"),jsonObject.getString("nationalite_pere"),jsonObject.getString("nationalite_mere"),
                                        jsonObject.getString("adresse_pere"),jsonObject.getString("adresse_mere"),jsonObject.getString("profession_pere"),jsonObject.getString("profession_mere")));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        listView.setAdapter(new DeclarationAdapter(listDeclaration,getActivity().getApplicationContext()));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }



    //begining methode 1
    private void creatPDF(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            PdfDocument myPdfDocument= new PdfDocument();
            Paint myPaint=new Paint();
            Paint titlepaint=new Paint();
            Paint myEnderP=new Paint();
            Paragraph paragraph=new Paragraph(mTextEt+mTextEt);

            PdfDocument.PageInfo myPageInfo=new PdfDocument.PageInfo.Builder(pageWidh,pageHeigh,1).create();
            PdfDocument.Page myPage=myPdfDocument.startPage(myPageInfo);
            Canvas canvas=myPage.getCanvas();

            canvas.drawBitmap(scaldbmp,45,65,myPaint);

            myPaint.setTextAlign(Paint.Align.LEFT);
            myPaint.setTextSize(10f);
            myPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
            canvas.drawText("REPUBLIQUE DEMOCRATIQUE",15,40,myPaint);
            canvas.drawText("DU CONGO",47,55,myPaint);

            titlepaint.setTextAlign(Paint.Align.LEFT);
            titlepaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
            titlepaint.setTextSize(10f);
            canvas.drawText("Ville de Kinshasa",35,130,titlepaint);
            canvas.drawText("Commune de Lemba",35,145,titlepaint);

            myEnderP.setTextAlign(Paint.Align.LEFT);
            myEnderP.setTextSize(10f);
            myEnderP.setUnderlineText(true);
            myEnderP.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
            canvas.drawText("Service de l'Etat Civil",35,160,myEnderP);

            titlepaint.setTextAlign(Paint.Align.LEFT);
            titlepaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
            titlepaint.setTextSize(10f);
            canvas.drawText("ACTE N°: ......",35,175,titlepaint);
            canvas.drawText("FOLIO N°: ......",35,190,titlepaint);
            canvas.drawText("VOLUME N°: ......",35,205,titlepaint);

            myPaint.setColor(Color.rgb(6,4,31));
            myPaint.setTextSize(10f);
            myPaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("A2893823",580,100,myPaint);

            titlepaint.setTextAlign(Paint.Align.CENTER);
            titlepaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
            titlepaint.setTextSize(14);
            canvas.drawText("ACTE DE NAISSANCE",pageWidh/2,230,titlepaint);

            myPaint.setColor(Color.rgb(6,4,31));
            myPaint.setTextSize(10f);
            myPaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("P.No:00980051 / H.No:A2893823",560,40,myPaint);

            titlepaint.setTextAlign(Paint.Align.LEFT);
            titlepaint.setTextSize(10f);
            titlepaint.setColor(Color.BLACK);


            canvas.drawText("En date du "+date_naissance.getText().toString()+" à ***h ",pageWidh/4,250,titlepaint);
            canvas.drawText("Par devant nous, ABDOUL KARIM Bernard, Bourgmestre et Officier de l'Etat-Civil de la Commune de XXXXX ",pageWidh/4,260,titlepaint);
            canvas.drawText("A comparu: ",pageWidh/4,270,titlepaint);
            canvas.drawText("Mr "+nom_pere.getText()+"xxxxxxxxxxxxxxxxxxxxxxx xxxxxxxxxxxxxxxxxx xxxxxxx \n xxxx xxxx xxxxxxxxxxxx xxxxxxx \n xxxxxxxxxxxxxxxx xxxxxx xxxxxxx xxxxxxxxxxxx \n xxxxxxxxxxxx xxxxxxxxxxx xxxxxxxxx",pageWidh/4,280,titlepaint);


            myPdfDocument.finishPage(myPage);


            File file=new File(Environment.getExternalStorageDirectory(),"/test.pdf");

            try {
                myPdfDocument.writeTo(new FileOutputStream(file));
            } catch (IOException e) {
                e.printStackTrace();
            }

            myPdfDocument.close();

        }

    }
    //end methode 1
}
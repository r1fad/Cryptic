package com.projects.rifadlafir.cryptic;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EncryptFragment extends Fragment implements Button.OnClickListener {

    private Button buttonE;
    private EditText editTextE;
    private TextView textViewE;
    private Button buttonER;
    private TextView textViewE1;
    private ImageButton buttonShareE;

    public EncryptFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_encrypt, container, false);

        buttonE = (Button)view.findViewById(R.id.buttonE);
        editTextE = (EditText)view.findViewById((R.id.editTextE));
        textViewE = (TextView)view.findViewById((R.id.textViewE));
        buttonER = (Button)view.findViewById(R.id.buttonER);
        textViewE1 = (TextView)view.findViewById(R.id.textViewE1);
        buttonShareE = (ImageButton) view.findViewById(R.id.buttonShareE);
        buttonE.setOnClickListener(this);
        buttonER.setOnClickListener(this);
        buttonShareE.setOnClickListener(this);
        buttonShareE.setEnabled(false);




        return view;

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonE:
                String x = String.valueOf(editTextE.getText());
                final String y ;
                y = Encrypt(x);
                buttonShareE.setEnabled(true);
                buttonShareE.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        shareIt(y);
                    }
                });
                break;



            case R.id.buttonER:
                editTextE.setText("");
                textViewE.setText("");
                textViewE1.setText("");
                buttonShareE.setEnabled(false);
            break;

            /*case R.id.buttonShareE:
                shareIt(sctext);*/



        }


    }
    private String Encrypt(String text){
        String ctext;
        text= String.valueOf(editTextE.getText());
        ctext="";
        //text=text.toLowerCase();
        //text=text.replace(" ","");

        List<String> textList = new LinkedList<>(Arrays.asList(text.split("")));
        int len = textList.size();
        int lencheck=len%3;
        if (lencheck!=0){
            int lena=lencheck+1;
            int lenb=lencheck+2;
            if (lena%3==0){
                textList.add("*");
            }
            else if (lenb%3==0){
                textList.add("*");
                textList.add("*");
            }
        }
        len=textList.size();

        for (int i =0;i<3;i++){
            int j = i;
            List<String> c = new ArrayList<>();
            while(j<len){
                c.add(textList.get(j));
                j=j+3;
            }

            int count,lenofc;
            count=0;
            lenofc=c.size();
            while (count < lenofc){
                ctext=ctext+c.get(count);
                count+=1;
            }
        }
        textViewE1.setText("The encrypted text is:");
        textViewE.setText(String.valueOf(ctext));

        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTextE.getWindowToken(), 0);
        return ctext;


    }

    private void shareIt(String shareCtext){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = shareCtext;
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

}

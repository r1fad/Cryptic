package com.projects.rifadlafir.cryptic;



import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DecryptFragment extends Fragment implements Button.OnClickListener {
    private Button buttonD;
    private EditText editTextD;
    private TextView textViewD;
    private Button buttonDR;
    private TextView textViewD1;
    private ImageButton buttonP;


    public DecryptFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_decrypt, container, false);

        buttonD = (Button)view.findViewById(R.id.buttonD);
        editTextD = (EditText)view.findViewById((R.id.editTextD));
        textViewD = (TextView)view.findViewById((R.id.textViewD));
        buttonDR = (Button)view.findViewById(R.id.buttonDR);
        textViewD1 = (TextView)view.findViewById(R.id.textViewD1);
        buttonP = (ImageButton)view.findViewById(R.id.buttonP);

        buttonD.setOnClickListener(this);
        buttonDR.setOnClickListener(this);
        buttonP.setOnClickListener(this);






        return view;
    }



    @Override
    public void onClick(View view) {
        switch(view.getId()){

        case R.id.buttonD:
            String text,dtext;
            text= String.valueOf(editTextD.getText());
            dtext="";
            List<String> textList = new LinkedList<>(Arrays.asList(text.split("")));
            int key,len;
            len=textList.size();
            key=(int) Math.floor(len/3);
            for (int i =0;i<key;i++) {
                int j = i;
                List<String> c = new ArrayList<>();
                while (j < len) {
                    c.add(textList.get(j));
                    j = j + key;
                }

                int count, lenofc;
                count = 0;
                lenofc = c.size();
                while (count < lenofc) {
                    dtext = dtext + c.get(count);
                    count += 1;
                }
                if (dtext.contains("*") == true) {
                    dtext = dtext.replace("*", "");

                }
                textViewD1.setText("The decrypted text is:");
                textViewD.setText(String.valueOf(dtext));
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editTextD.getWindowToken(), 0);
            }
        break;

        case R.id.buttonDR:
            textViewD.setText("");
            textViewD1.setText("");
            editTextD.setText("");

        break;


            case R.id.buttonP:
                ClipboardManager myClipBoard;
                myClipBoard = (ClipboardManager)this.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData cp = myClipBoard.getPrimaryClip();
                ClipData.Item item = cp.getItemAt(0);
                String textP = item.getText().toString();
                editTextD.setText(textP);


    }

}}

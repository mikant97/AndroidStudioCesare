package it.arezzo.itis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Disattivo il pulsante Decripta
        Button b2 = (Button) findViewById(R.id.button2);
        b2.setEnabled(false);


        EditText et0 = (EditText) findViewById(R.id.stringaDaCriptare);

        et0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Button b2 = (Button) findViewById(R.id.button2);
                b2.setEnabled(false);
                TextView risultato = (TextView) findViewById(R.id.textView3);
                risultato.setText("DeCriptata:\"");
            }
        });

        // Controllo Input Valore Codice di Criptazione
        EditText et = (EditText) findViewById(R.id.editTextNumber);
        et.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                if (text.length() > 0 && text.length() <= 2) {
                    if ((Integer.parseInt(text.toString()) > 26) || (Integer.parseInt(text.toString()) <= 0)) {
                        et.setError(getString(R.string.codice_errato));

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Button b2 = (Button) findViewById(R.id.button2);
                b2.setEnabled(false);
                TextView risultato = (TextView) findViewById(R.id.textView3);
                risultato.setText("DeCriptata:\"");

            }
        });
    }

    public void cripta(View view){
        Button b1 = (Button)view;
        String text = b1.getText().toString();
        String app;
        if (text.equals("CRIPTA!!!")) {
            EditText edit_stringa = (EditText) findViewById(R.id.stringaDaCriptare);
            app = edit_stringa.getText().toString();
            if (app.isEmpty() || app == null) {
                edit_stringa.setError("Inserire un valore");
                return;
            }
        }
        else
        {
            TextView edit_stringa = (TextView) findViewById(R.id.textView2);
            String app1 = edit_stringa.getText().toString();
            app = (String) app1.subSequence(10,app1.length()-1);

        }
        EditText edit_numero = (EditText)findViewById(R.id.editTextNumber);
        int appNum;
        try {

            appNum = Integer.parseInt(edit_numero.getText().toString());
            Button b2 = (Button) findViewById(R.id.button2);
            b2.setEnabled(true);
        } catch (NumberFormatException e)
        {
            edit_numero.setError("Inserire un valore");
            return;
        }

        if (text.equals("CRIPTA!!!")) {
            String criptata = criptaMetodo(app, appNum);
            TextView risultato = (TextView) findViewById(R.id.textView2);
            risultato.setText("Criptata:\"" + criptata + "\"");
        }
        else {
            String criptata = criptaMetodo(app, -appNum);
            TextView risultato = (TextView) findViewById(R.id.textView3);
            risultato.setText("DeCriptata:\"" + criptata + "\"");
        }
    }

    public String criptaMetodo(String app, int appNum) {
        String s = "";
        for (int i = 0; i < app.length(); i++) {
            boolean maiuscola = false;
            if (app.charAt(i) > 64 && app.charAt(i) < 91) { maiuscola = true; }
            int c = app.charAt(i);
            if (!maiuscola) { c = c - 32; }
            if (( c + appNum <= 90) && (c + appNum >= 65)){
               c = c + appNum;
            }
            else
            {
                if (c + appNum < 65) { c = c + Math.abs(appNum) - 90 + 1;}
                else { c = c + Math.abs(appNum) - 90 - 1; }
                c = 65 + c;
            }
            if (!maiuscola ) { c = c + 32; }
            s = s + (char) (c);
        }
        return s;
    }
}
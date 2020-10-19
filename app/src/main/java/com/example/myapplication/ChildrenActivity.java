package com.example.myapplication;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ChildrenActivity extends Activity {
    EditText et_children_login;
    EditText et_children_pwd;
    EditText et_children_email;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_children);

        et_children_login = (EditText)findViewById(R.id.et_children_login);
        et_children_pwd = (EditText)findViewById(R.id.et_children_password);
        et_children_email = (EditText)findViewById(R.id.et_children_email);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

        public void onChildrenClickManager(View v){
        switch (v.getId()) {
            case R.id.bt_children_main:
                /*Toast.makeText(getApplicationContext(),
                "Login : " + et_children_login.getText() +
                "\n Password : " + et_children_pwd.getText() +
                "\n Email : " + et_children_email.getText(), Toast.LENGTH_LONG)
                .show();*/

                /*Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);*/
                //setResult(RESULT_OK);

                if(et_children_login.getText().toString().isEmpty() || et_children_pwd.getText().toString().isEmpty() ||
                        et_children_email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Complétez tous les champs !", Toast.LENGTH_SHORT).show();
                }
                else {
                    /*
                    Intent intxt = new Intent(this, MainActivity.class);
                    intxt.putExtra("login", et_children_login.getText().toString());
                    intxt.putExtra("pwd", et_children_pwd.getText().toString());
                    intxt.putExtra("email", et_children_email.getText().toString());
                    startActivity(intxt);
                    */
                     SharedPreferences.Editor editor = sharedPreferences.edit();
                     editor.putString("login", et_children_login.getText().toString());
                     editor.putString("pwd", et_children_pwd.getText().toString());
                     editor.putString("email", et_children_email.getText().toString());
                     editor.commit();
                     Intent intent = new Intent(this, MainActivity.class);
                     startActivity(intent);
                     finish();
                }
                break;
            case R.id.bt_children_liste:
                Intent intentListe = new Intent(this, ListActivity.class);
                startActivity(intentListe);
                break;
            case R.id.bt_children_save:
                String s = et_children_login.getText().toString() + "#" +
                        et_children_pwd.getText().toString() + "#" +
                        et_children_email.getText().toString() + "#";
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                try {
                    FileOutputStream outputStream = openFileOutput("monFichier.txt", MODE_APPEND);
                    byte[] tab;
                    tab = s.toString().getBytes();
                    outputStream.write(tab);
                    outputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_children_read:
                Intent intentRead = new Intent(this, FileActivity.class);
                startActivity(intentRead);
                break;
            default:
                break;
        }
        }
}

package com.example.myapplication.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;

public class MainActivity extends Activity {

    private static final int CODE_ACTIVITE = 1;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if(!sharedPreferences.getAll().isEmpty()){
            Toast.makeText(getApplicationContext(), "votre login est : "
                    + (sharedPreferences.getString("login", "NULL")) + "\n"
                    + "votre password est : "
                    + (sharedPreferences.getString("pwd", "NULL")) + "\n"
                    + "votre email est : "
                    + (sharedPreferences.getString("email", "NULL")) + "\n", Toast.LENGTH_LONG).show();
        /*Bundle extractxt = this.getIntent().getExtras();
        if(extractxt != null) {
            Toast.makeText(getApplicationContext(), "votre login est : "
            + (extractxt.getString("login")) + "\n"
            + "votre password est : "
            + (extractxt.getString("pwd")) + "\n"
            + "votre email est : "
            + (extractxt.getString("email")) + "\n", Toast.LENGTH_LONG).show();
         */
        }
    }

    public void onMainClickManager(View v) {
        switch (v.getId()) {
            case R.id.bt_toast:
                Toast.makeText(getApplicationContext(), "Un toast pour vous", Toast.LENGTH_LONG).show();
                break;
            case R.id.bt_main_children:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Alerte activité")
                        .setMessage("Voulez-vous afficher l'activité nommée Children ?")
                        .setCancelable(false)
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getApplicationContext(),
                                        ChildrenActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create().show();
                /*
                Intent intent = new Intent(this, ChildrenActivity.class);
                //startActivity(intent);
                startActivityForResult(intent, CODE_ACTIVITE);
                */
                break;
            default:
                break;
        }

    }
@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case CODE_ACTIVITE:
                switch (resultCode){
                    case RESULT_OK:
                        Toast.makeText(this, "Action validée depuis l'activité Children", Toast.LENGTH_LONG).show();
                        break;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "Action annulée", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
        }
    }
}
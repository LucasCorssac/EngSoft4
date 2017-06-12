package com.example.galtama.engsoft4;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }





    public void changeToVisualizarPedidos(View view){
        Intent intent = new Intent(this, VisualizarPedidosActivity.class);
        startActivity(intent);
    }

    public void changeToFazerPedido(View view){
        Intent intent = new Intent(this, FazerPedidoActivity.class);
        startActivity(intent);
    }

    public void changeToRegisterUser(View view){
        Intent intent = new Intent(this, RegisterUserActivity.class);
        startActivity(intent);
    }
}

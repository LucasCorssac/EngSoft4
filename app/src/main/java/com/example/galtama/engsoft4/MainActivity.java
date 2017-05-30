package com.example.galtama.engsoft4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
}

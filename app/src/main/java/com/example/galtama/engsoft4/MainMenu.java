package com.example.galtama.engsoft4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }


    public void changeToVisualizarPedidos(View view){
        Intent intent = new Intent(this, VisualizarPedidosActivity.class);
        startActivity(intent);
    }

    public void changeToFazerPedido(View view){
        Intent intent = new Intent(this, FazerPedidoActivity.class);
        startActivity(intent);
    }

    public void changeToVisualizarMeusPedidos(View view){
        Intent intent = new Intent(this, VisualizarMeusPedidosActivity.class);
        startActivity(intent);
    }

}

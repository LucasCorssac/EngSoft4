package com.example.galtama.engsoft4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FazerPedidoActivity extends AppCompatActivity {

    DatabaseReference dbRef;

    EditText editText_Data;
    EditText editText_Valor;
    EditText editText_causaSocial;
    Button button_enviarPedido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        dbRef = FirebaseDatabase.getInstance().getReference("pedidos");

        editText_Data = (EditText) findViewById(R.id.editText_getData);
        editText_Valor = (EditText) findViewById(R.id.editText_getValor);
        editText_causaSocial = (EditText) findViewById(R.id.editText_causaSocial);
        button_enviarPedido = (Button) findViewById(R.id.button_EnviarPedido);

        button_enviarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPedido();
            }
        });

    }

    private void addPedido(){

        String svalor = editText_Valor.getText().toString().trim();
        String data = editText_Data.getText().toString().trim();
        String causaSocial = editText_causaSocial.getText().toString().trim();


        if(!TextUtils.isEmpty(svalor) && !TextUtils.isEmpty(svalor)){
            int valor = Integer.parseInt(svalor);

            String id = dbRef.push().getKey();


            Pedido pedido = new Pedido(id, valor, data, causaSocial);

            dbRef.child(id).setValue(pedido);

            Toast.makeText(this, "Pedido Feito com Sucesso", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this, "Por favor preencher ambos campos", Toast.LENGTH_LONG).show();
        }
    }



}

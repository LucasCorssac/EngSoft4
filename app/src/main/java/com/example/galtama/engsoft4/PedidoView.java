package com.example.galtama.engsoft4;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class PedidoView extends AppCompatActivity {

    EditText editTextNome;
    EditText editTextData;
    EditText editTextValor;
    EditText editTextCausaSocial;

    ImageView imageView;

    Button fazerOferta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pedidos_layout);

        editTextNome = (EditText) findViewById(R.id.list_pedidos_layout_Nome);
        editTextData = (EditText) findViewById(R.id.list_pedidos_layout_dataPagamento);
        editTextValor = (EditText) findViewById(R.id.list_pedidos_layout_valor);
        editTextCausaSocial = (EditText) findViewById(R.id.list_pedidos_layout_causaSocial);

        imageView = (ImageView) findViewById(R.id.list_pedidos_layout_imageView);

        fazerOferta = (Button) findViewById(R.id.buttonFazerOferta);

        final Pedido pedido = (Pedido) getIntent().getSerializableExtra("pedido");


        editTextNome.setText(pedido.getNome());
        editTextData.setText(pedido.getdataPagamento());
        editTextValor.setText(String.valueOf(pedido.getValorPedido()));
        editTextCausaSocial.setText(pedido.getcausaSocial());

        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();

        StorageReference testRef = mStorageRef.child("images/" + pedido.getIdPedido());

        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(testRef)
                .into(imageView);



        fazerOferta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fazerOfertaDialog(pedido);
            }
        });


    }



    private void fazerOfertaDialog(Pedido pedido)
    {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.fazer_oferta_dialog, null);

        dialogBuilder.setView(dialogView);

        final EditText editTextValorOferta = (EditText) dialogView.findViewById(R.id.dialog_valor_oferta);
        final Button buttonEnviarOferta = (Button) dialogView.findViewById(R.id.button_enviar_oferta);

        dialogBuilder.setTitle("Enviar Oferta");

        final AlertDialog alertDialog = dialogBuilder.create();

        alertDialog.show();

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("pedidos");
        final DatabaseReference pedidoReference = dbRef.child(pedido.getUserId()).child(pedido.getIdPedido());


        buttonEnviarOferta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String key = pedidoReference.push().getKey();
                String valorString = editTextValorOferta.getText().toString().trim();
                int valor = Integer.parseInt(valorString);

                Oferta oferta = new Oferta(valor, key);

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();

                pedidoReference.child(uid).setValue(oferta);
                alertDialog.dismiss();
            }
        });





    }


}

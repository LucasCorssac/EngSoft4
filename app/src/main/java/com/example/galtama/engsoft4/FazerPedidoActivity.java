package com.example.galtama.engsoft4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class FazerPedidoActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference dbRef;

    private EditText editText_Data;
    private EditText editText_Valor;
    private EditText editText_causaSocial;
    private EditText editText_Nome;
    private Button button_enviarPedido;

    //a constant to track the file chooser intent
    private static final int PICK_IMAGE_REQUEST = 234;

    //Buttons
    private Button buttonChoose;

    //ImageView
    private ImageView imageView;
    private Uri filePath;

    private StorageReference storageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        dbRef = FirebaseDatabase.getInstance().getReference("pedidos");

        editText_Data = (EditText) findViewById(R.id.editText_getData);
        editText_Valor = (EditText) findViewById(R.id.editText_getValor);
        editText_causaSocial = (EditText) findViewById(R.id.editText_causaSocial);
        button_enviarPedido = (Button) findViewById(R.id.button_EnviarPedido);
        editText_Nome = (EditText) findViewById(R.id.editTextNome);

        //getting views from layout
        buttonChoose = (Button) findViewById(R.id.buttonChoose);

        imageView = (ImageView) findViewById(R.id.imageView);

        //attaching listener
        buttonChoose.setOnClickListener(this);

        storageRef = FirebaseStorage.getInstance().getReference();


        button_enviarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPedido();
            }
        });

    }

    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            //try {
                Glide.with(this).load(filePath).into(imageView);

                //Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //imageView.setImageBitmap(bitmap);

            //} catch (IOException e) {
             //   e.printStackTrace();
            //}
        }
    }

    @Override
    public void onClick(View view) {
        //if the clicked button is choose
        if (view == buttonChoose) {
            showFileChooser();
        }
        //if the clicked button is upload

    }

    //this method will upload the file
    private void uploadFile(String id) {
        //if there is a file to upload
        if (filePath != null) {
            //displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();
            String filename = "images/" + id;
            StorageReference riversRef = storageRef.child(filename);
            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //if the upload is successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying a success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //if the upload is not successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying error message
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        }
        //if there is not any file
        else {
            //you can display an error toast
        }
    }




    private void addPedido(){

        String svalor = editText_Valor.getText().toString().trim();
        String data = editText_Data.getText().toString().trim();
        String causaSocial = editText_causaSocial.getText().toString().trim();
        String nome = editText_Nome.getText().toString().trim();


        if(!TextUtils.isEmpty(svalor) && !TextUtils.isEmpty(data) && !TextUtils.isEmpty(causaSocial) && !TextUtils.isEmpty(causaSocial)){
            int valor = Integer.parseInt(svalor);
            String id = dbRef.push().getKey();

            uploadFile(id);

            Pedido pedido = new Pedido(id, valor, data, causaSocial,nome);

            dbRef.child(id).setValue(pedido);

            Toast.makeText(this, "Pedido Feito com Sucesso", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this, "Por favor preencher ambos campos", Toast.LENGTH_LONG).show();
        }
    }



}

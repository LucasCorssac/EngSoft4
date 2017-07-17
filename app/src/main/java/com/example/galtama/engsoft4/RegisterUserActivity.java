package com.example.galtama.engsoft4;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUserActivity extends AppCompatActivity {

    private static final String TAG = "RegisterUserActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    EditText editText_Nome;
    EditText editText_Password;
    EditText editText_Email;
    EditText editText_capitalInicial;
    Button button_enviarPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        editText_Nome = (EditText) findViewById(R.id.editText_register_user_nome);
        editText_Email = (EditText) findViewById(R.id.editText_register_user_email);
        editText_Password = (EditText) findViewById(R.id.editText_register_user_password);
        editText_capitalInicial = (EditText) findViewById(R.id.editText_register_user_capitalInicial);
        button_enviarPedido = (Button) findViewById(R.id.button_register_user);

        button_enviarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });


        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }


     @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void createAccount(){

        final String nome = editText_Nome.getText().toString();
        final String email = editText_Email.getText().toString();
        final String password = editText_Password.getText().toString();
        final String capitalInicialString = editText_capitalInicial.getText().toString();



        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {

                            Log.d(TAG, "onComplete: Failed=" + task.getException().getMessage()); //ADD THIS

                            Toast.makeText(getApplicationContext(), "Erro na criacao de usuario", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Usuario criado com sucesso!", Toast.LENGTH_SHORT).show();

                            mAuth.signInWithEmailAndPassword(email, password);


                            int capitalInicial = Integer.parseInt(capitalInicialString);

                            DatabaseReference usuarioReference = FirebaseDatabase.getInstance().getReference("usuarios");

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            String uid = user.getUid();

                            Usuario usuario = new Usuario(uid, nome, capitalInicial);

                            usuarioReference.child(uid).setValue(usuario);

                            mAuth.signOut();

                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }

                        // ...
                    }
                });

    }


}

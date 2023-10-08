package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class
Registrar extends AppCompatActivity {
EditText us, pas, nom, ap;
Button reg, can;

daoUsuario dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar);
        us=(EditText) findViewById(R.id.RegUser);
        pas=(EditText) findViewById(R.id.RegPass);
        nom=(EditText) findViewById(R.id.RegNombre);
        ap=(EditText) findViewById(R.id.RegApellido);
        reg=(Button) findViewById(R.id.btnRegRegistrar);
        can=(Button) findViewById(R.id.btnRegCancelar);
        dao=new daoUsuario(this);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario u=new Usuario();
                u.setUsuario(us.getText().toString());
                u.setPassword(pas.getText().toString());
                u.setNombre(nom.getText().toString());
                u.setApellidos(ap.getText().toString());
                if (!u.isNull()){
                    Toast.makeText(Registrar.this, "ERROR: campos vacios",Toast.LENGTH_LONG).show();

                } else if (dao.insertUsuario(u)) {
                    Toast.makeText(Registrar.this, "REGISTRO exitoso!!!",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Registrar.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(Registrar.this, "Usuario ya registrado!!!",Toast.LENGTH_LONG).show();
                }

            }
        });

        can.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registrar.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

}
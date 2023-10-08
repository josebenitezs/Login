package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    EditText user, pass;
    Button btnEntrar, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=(EditText) findViewById(R.id.User);
        pass=(EditText) findViewById(R.id.Pass);
        btnEntrar=(Button) findViewById(R.id.btnEntrar);
        btnRegistrar=(Button) findViewById(R.id.btnRegistrar);
        daoUsuario dao=new daoUsuario( this);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u=user.getText().toString();
                String p=pass.getText().toString();
                if(u.equals("")&&p.equals("")){
                    Toast.makeText(MainActivity.this, "Error: campos vacios", Toast.LENGTH_SHORT).show();
                } else if (dao.login(u,p)==1) {
                    Usuario ux=dao.getUsuario(u,p);
                    Toast.makeText(MainActivity.this, "Datos corectos", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this,Inicio.class);
                    i.putExtra("Id", ux.getId());
                    startActivity(i);
                }else {
                    Toast.makeText(MainActivity.this, "Usuario y/o Password incorrectos", Toast.LENGTH_SHORT).show();
                }


            }
        });
        btnRegistrar.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Registrar.class);
                startActivity(intent);

            }
        });


    }



}
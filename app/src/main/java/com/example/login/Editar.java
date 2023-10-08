package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Editar extends AppCompatActivity {
    EditText ediUser, ediPass, ediNombre, ediApellido;
    Button btnActualizar, btnCancelar;
    int id=0;
    Usuario u;
    daoUsuario dao;
   Intent x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar);
        ediUser = (EditText) findViewById(R.id.EdiUser);
        ediPass = (EditText) findViewById(R.id.EdiPass);
        ediNombre = (EditText) findViewById(R.id.EdiNombre);
        ediApellido = (EditText) findViewById(R.id.EdiApellido);
        btnActualizar=(Button) findViewById(R.id.btnEdiActualizar);
        btnCancelar=(Button) findViewById(R.id.btnEdiCancelar);

        Bundle b =getIntent().getExtras();
        id = b.getInt("Id");
        dao = new daoUsuario( this);
        u = dao.getUsuarioById(id);
        ediUser.setText(u.getUsuario());
        ediPass.setText(u.getPassword());
        ediNombre.setText(u.getNombre());
        ediApellido.setText(u.getApellidos());

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                u.setUsuario(ediUser.getText().toString());
                u.setPassword(ediPass.getText().toString());
                u.setNombre(ediNombre.getText().toString());
                u.setApellidos(ediApellido.getText().toString());
                if(!u.isNull()){
                    Toast.makeText(Editar.this, "ERROR: campos vacios", Toast.LENGTH_SHORT).show();
                } else if (dao.updateUsuario(u)) {
                    Toast.makeText(Editar.this, "Actualizacion Exitosa!!!", Toast.LENGTH_SHORT).show();
                    Intent i2 = new Intent(Editar.this,Inicio.class);
                    i2.putExtra("Id", u.getId());
                    startActivity(i2);
                    finish();
                }else {
                    Toast.makeText(Editar.this, "No se puede actualizar!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Editar.this, Inicio.class);
                intent.putExtra("Id", u.getId());
                startActivity(intent);
                finish();
            }
        });


    }









}



package com.example.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Inicio extends AppCompatActivity {
Button btnEditar, btnEliminar, btnMostrar, btnSalir;
TextView nombre;
int id=0;
Usuario u;
daoUsuario dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        nombre=(TextView) findViewById(R.id.nombreUsuario);
        btnEditar=(Button) findViewById(R.id.btnEditar);
        btnEliminar=(Button) findViewById(R.id.btnEliminar);
        btnMostrar=(Button) findViewById(R.id.btnMostrar);
        btnSalir=(Button) findViewById(R.id.btnSalir);

        Bundle b=getIntent().getExtras();
        id=b.getInt("Id");
        dao=new daoUsuario(this);
        u=dao.getUsuarioById(id);
        nombre.setText(u.getNombre()+" "+u.getApellidos());

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Inicio.this, Editar.class);
                intent.putExtra("Id", id);
                startActivity(intent);

            }
        });



        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder b=new AlertDialog.Builder(Inicio.this);
                b.setMessage("Estas seguro de Eliminar tu cuenta??");
                b.setCancelable(false);
                b.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(dao.deleteUsuario(id)){
                            Toast.makeText(Inicio.this, "Se elimino correctamente!!!", Toast.LENGTH_SHORT).show();
                            Intent a=new Intent(Inicio.this,MainActivity.class);
                            startActivity(a);
                            finish();
                        }else {
                            Toast.makeText(Inicio.this, "ERROR: no se elimino Cuenta", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                b.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                b.show();
            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c=new Intent(Inicio.this, Mostrar.class);
                startActivity(c);

            }
        });


        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Inicio.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


}
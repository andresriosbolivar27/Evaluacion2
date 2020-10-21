package com.example.evaluacion2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditarContacto extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvFechaNac;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvDescripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contacto);

        editarRegistro();

        //Obtener parametros
        Bundle parametros = getIntent().getExtras();
        String nombre   = parametros.getString("nombre");//nombre
        String fechaNac   = parametros.getString("fecha");//fecha
        String telefono = parametros.getString("telefono");//telefono
        String email    = parametros.getString("email");//email
        String descripcion    = parametros.getString("descripcion");//descripcion

        tvNombre = findViewById(R.id.tvDatoNombre);
        tvFechaNac = findViewById(R.id.tvDatoFechaNac);
        tvTelefono = findViewById(R.id.tvDatoTelefono);
        tvEmail = findViewById(R.id.tvDatoEmail);
        tvDescripcion = findViewById(R.id.tvDatoDescripcion);

        tvNombre.setText(nombre);
        tvFechaNac.setText(fechaNac);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);
        tvDescripcion.setText(descripcion);

    }

    public void editarRegistro(){
        final Button btneditarRegistro = findViewById(R.id.btnEditar);
        btneditarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("editarNombre",tvNombre.getText().toString());
                intent.putExtra("editarFecha",tvFechaNac.getText().toString());
                intent.putExtra("editarTelefono",tvTelefono.getText().toString());
                intent.putExtra("editarEmail",tvEmail.getText().toString());
                intent.putExtra("editarDescripcion",tvDescripcion.getText().toString());

                setResult(2,intent);
                finish();
            }
        });
}
}
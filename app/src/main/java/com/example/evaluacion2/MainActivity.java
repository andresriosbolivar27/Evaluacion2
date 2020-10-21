package com.example.evaluacion2;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout etNombre,etTelefono, etEmail, etDescripcion;
    private TextInputEditText etFechaNac;
    private DatePickerDialog datePickerDialog;
    private boolean editar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agregarRegistro();

        etNombre = findViewById(R.id.tilNombre);
        etTelefono = findViewById(R.id.tilTelefono);
        etEmail = findViewById(R.id.tilEmail);
        etDescripcion = findViewById(R.id.tilDescripcion);
        etFechaNac = findViewById(R.id.etFecha);
        etFechaNac.setInputType(InputType.TYPE_NULL);
        etFechaNac.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        etFechaNac.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void editarRegistro(Intent data) {
        TextInputEditText tietNombre,tietTelefono, tietEmail, tietDescripcion;
        //Obtener parametros
        //Bundle editarParametros = getIntent().getExtras();
        String nombre   = data.getStringExtra("editarNombre");//nombre
        String fechaNac   = data.getStringExtra("editarFecha");//fecha
        String telefono = data.getStringExtra("editarTelefono");//telefono
        String email    = data.getStringExtra("editarEmail");//email
        String descripcion    = data.getStringExtra("editarDescripcion");//descripcion

        etNombre = findViewById(R.id.tilNombre);
        etFechaNac = (TextInputEditText)findViewById(R.id.etFecha);
        etTelefono = findViewById(R.id.tilTelefono);
        etEmail = findViewById(R.id.tilEmail);
        etDescripcion = findViewById(R.id.tilDescripcion);

        etNombre.setHint(nombre);
        etFechaNac.setHint(fechaNac);
        etTelefono.setHint(telefono);
        etEmail.setHint(email);
        etDescripcion.setHint(descripcion);
    }

    public void agregarRegistro(){
        final Button btnAgregarRegistro = findViewById(R.id.btnRegistrar);
        btnAgregarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EditarContacto.class);
                intent.putExtra("nombre",etNombre.getEditText().getText().toString());
                intent.putExtra("fecha",etFechaNac.getEditableText().toString());
                intent.putExtra("telefono",etTelefono.getEditText().getText().toString());
                intent.putExtra("email",etEmail.getEditText().getText().toString());
                intent.putExtra("descripcion",etDescripcion.getEditText().getText().toString());
                startActivityForResult(intent,2);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2)
        {
            editarRegistro(data);
        }
    }
}

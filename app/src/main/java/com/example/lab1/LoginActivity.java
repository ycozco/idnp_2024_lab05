package com.example.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab1.databinding.ActivityMainBinding;
import com.google.gson.Gson;


public class LoginActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private AccountEntity accountEntity;
    private String accountEntityString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        // Asignar los elementos de la vista a variables locales.
        EditText edtNombre = binding.edtNombre;
        EditText edtContraseniaa =binding.edtContraseniaa;
        Button btnLogin = binding.btnLogin;
        Button btnAddAccount = binding.btnAddAccount;
        // Configurar el listener para el botón de inicio de sesión
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificar si el nombre de usuario y la contraseña son "admin"
                if (edtNombre.getText().toString().equals("admin") && edtContraseniaa.getText().toString().equals
                        ("admin"))
                {
                    // Mostrar un mensaje de bienvenida
                    Toast.makeText(getApplicationContext(), "Bienvenido a mi App", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Bienvenido a mi App");
                    // Crear un intent para abrir la actividad HomeActivity
                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                 intent.putExtra("ACCOUNT",accountEntityString);
                 startActivity(intent);
                }else{
                    // Mostrar un mensaje de error en la autenticación
                    Toast.makeText(getApplicationContext(), "Error en la autenticacion", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Error en la autenticacion");
                }
            }
        });
        // Configurar el listener para el botón de agregar cuenta
        btnAddAccount.setOnClickListener(v -> {
            // Crear un intent para abrir la actividad AccountActivity
           Intent intent=new Intent(getApplicationContext(),AccountActivity.class);
           activityResultLauncher.launch(intent);
        });
        }
    // Registrar el resultado de la actividad AccountActivity
        ActivityResultLauncher<Intent>activityResultLauncher=registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                    public void onActivityResult(ActivityResult activityResult) {
                            // Obtener el código de resultado
                            Integer resulCode = activityResult.getResultCode();

                            // Verificar si se aceptó la cuenta
                    if(resulCode ==AccountActivity.ACCOUNT_ACEPTAR){

                        // Obtener los datos del intent
                        Intent data = activityResult.getData();
                        accountEntityString=data.getStringExtra(AccountActivity.ACCOUNT_RECORD);

                        // Convertir la cadena JSON a un objeto AccountEntity
                        Gson gson=new Gson();
                        accountEntity= gson.fromJson(accountEntityString,AccountEntity.class);

                        // Obtener el nombre del usuario y mostrarlo en un Toast
                        String firstname= accountEntity.getFirstname();
                        Toast.makeText (getApplicationContext(),"Nombre:"+firstname,Toast.LENGTH_SHORT).show();
                        Log.d("LoginActivity","Nombre:"+firstname);

                        // Mostrar un mensaje de cancelación
                    } else if (resulCode == AccountActivity.ACCOUNT_CANCELAR) {
                        Toast.makeText (getApplicationContext(),"Cancelado",Toast.LENGTH_SHORT).show();
                          Log.d("LoginActivity","Cancelado");
                    }

                        }
    }


        );
    }

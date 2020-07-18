package jpinales.com.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText mName, mEmail, mPass;
    Button sign;
    TextView login;
    FirebaseAuth auth;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mPass = findViewById(R.id.pass);
        sign=  findViewById(R.id.sign);
        login= findViewById(R.id.login1);

        auth = FirebaseAuth.getInstance();



        if (auth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),Welcome.class));
            finish();
        }

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 =  mEmail.getText().toString().trim();
                String pass1 = mPass.getText().toString().trim();

                if (TextUtils.isEmpty(email1)){
                    mEmail.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(pass1)){
                    mPass.setError("Password is Required");
                    return;
                }
                // Register the user in firebase
                auth.createUserWithEmailAndPassword(email1, pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(),Welcome.class));
                            Intent window = new Intent(Register.this, Welcome.class);
                            startActivity(window);
                        }else {
                            Toast.makeText(Register.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });

    }

    //Back to the Log In
    public void backLogin(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}

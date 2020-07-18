package jpinales.com.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText mEmail, mPass;
    String pass;
    String user;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail= (EditText) findViewById(R.id.txtcam1);
        mPass= (EditText) findViewById(R.id.txtcam2);
        auth= FirebaseAuth.getInstance();
    }

    public void click(View view) {
        mEmail.setText("");
        mPass.setText("");
    }
    public void Signup(View view){
        startActivity(new Intent(getApplicationContext(),Register.class));

    }

    public void Login(View view){
        user=mEmail.getText().toString();
        pass=mPass.getText().toString();

        if (user.isEmpty() ){
            Toast.makeText(this,"Email is Required", Toast.LENGTH_LONG).show();
        }
        if (pass.isEmpty()){
            Toast.makeText(this,"Password is Required", Toast.LENGTH_LONG).show();
        }

        auth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(getApplicationContext(),Welcome.class));
                    Intent window = new Intent(MainActivity.this, Welcome.class);
                    startActivity(window);
                }else{
                    Toast.makeText(MainActivity.this, "User o Password Incorrect", Toast.LENGTH_SHORT).show();
                }

            }
        });

/*
        else {
            if (user.equals("Jpinales") && pass.equals("1234")) {
                Intent window = new Intent(MainActivity.this, Welcome.class);
                startActivity(window);

            } else {
                Toast.makeText(this, "Usuario o contraseña incorreta", Toast.LENGTH_LONG).show();
            }
        }

 */
    }
}

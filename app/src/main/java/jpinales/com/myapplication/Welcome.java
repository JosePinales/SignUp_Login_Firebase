package jpinales.com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void Logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent window = new Intent(Welcome.this, MainActivity.class);
        startActivity(window);
    }
}

package sparsity.arnaujia.logindebug;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PostLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);

        Intent i = getIntent();
        UserView user = i.getParcelableExtra("user");


        TextView id = findViewById(R.id.id);
        TextView name = findViewById(R.id.name);
        TextView lastname = findViewById(R.id.lastname);
        TextView username = findViewById(R.id.username);
        TextView email = findViewById(R.id.email);


        id.setText(String.valueOf(user.getId()));
        name.setText(user.getName());
        lastname.setText(user.getLastname());
        username.setText(user.getUsername());
        email.setText(user.getEmail());


    }
}

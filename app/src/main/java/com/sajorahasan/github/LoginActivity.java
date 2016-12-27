package com.sajorahasan.github;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUsername;
    private AppCompatButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        btnLogin = (AppCompatButton) findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                String data = etUsername.getText().toString().trim();

                if (!data.isEmpty()) {
                    Intent i = new Intent(getApplicationContext(), UserActivity.class);
                    i.putExtra("name", data);
                    startActivity(i);

                } else {

                    Snackbar.make(btnLogin, "Field is empty !", Snackbar.LENGTH_LONG).show();
                }

                break;
        }
    }
}

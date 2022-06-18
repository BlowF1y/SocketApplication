package com.daelim.socketapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText id_text, pw_text;
    private Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id_text = findViewById(R.id.id_text);
        pw_text = findViewById(R.id.pw_text);
        id_text.setText("");
        pw_text.setText("");
        login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = id_text.getText().toString();
                String pw = pw_text.getText().toString();

                if (id.length() == 0 ) {
                    Toast.makeText(LoginActivity.this, "아이디, 비밀번호를 입력해주세요"
                            , Toast.LENGTH_LONG).show();
                }else if(pw.length() == 0){
                    Toast.makeText(LoginActivity.this, "아이디, 비밀번호를 입력해주세요"
                            , Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("id", id);
                    intent.putExtra("pw", pw);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
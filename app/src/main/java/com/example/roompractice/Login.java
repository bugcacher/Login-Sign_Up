package com.example.roompractice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.et_user_login);
        password = findViewById(R.id.et_password_login);
        login = findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name;
                String pass;
                name = username.getText().toString().trim();
                pass = password.getText().toString().trim();

                if (name.isEmpty() || pass.isEmpty())
                {
                    Toast.makeText(Login.this,"Enter Username and Password",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    checkAsycTask task = new checkAsycTask(Login.this.getApplication(),name,pass);
                    task.execute();
                }


            }
        });

    }


    public  class checkAsycTask extends AsyncTask<User,Void,User>
    {
        private UserDao userDao;
        private String name;
        private String pass;
        User user;

        public checkAsycTask(Application application,String name, String pass) {
            this.name = name;
            this.pass = pass;
            UserDatabase db = UserDatabase.getInstance(application);
            userDao = db.userDao();
        }

        @Override
        protected User doInBackground(User... users) {

            user = userDao.getUser(name,pass);
            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            if (user == null)
            {
                Toast.makeText(Login.this,"User not found!",Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(Login.this,Welcome.class);
                startActivity(intent);
            }
        }
    }
}

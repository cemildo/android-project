package com.cemil.dogan.activities;

/**
 * Created by Dogan on 24.10.15.
 */

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import service.Callback;
        import service.LibraryService;


public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    EditText _nameText ;
    EditText _emailText ;
    EditText _passwordText;
    Button _signupButton ;
    TextView _loginLink;
    TextView _matrikel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        _nameText = (EditText) findViewById(R.id.input_name);
        _emailText = (EditText)  findViewById(R.id.input_email);
        _passwordText = (EditText)  findViewById(R.id.input_password);
        _signupButton = (Button) findViewById(R.id.btn_signup);
        _loginLink  = (TextView) findViewById(R.id.link_login);
        _matrikel = (TextView) findViewById(R.id.input_matrikel);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
        R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String matrikel = _matrikel.getText().toString();


        LibraryService.register(email, password,name, matrikel, new Callback<Boolean>(){
            @Override
            public void onCompletion(Boolean result) {

                Toast.makeText(getBaseContext(), "Register is successfull", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                intent.putExtra("email", _emailText.getText().toString());
                intent.putExtra("password", _passwordText.getText().toString());
                intent.putExtra("name", _nameText.getText().toString());
                intent.putExtra("matrikel", _matrikel.getText().toString());
                startActivity(intent);

            }

            @Override
            public void onError(String message) {

                Toast.makeText(getBaseContext(), "Could not be registered", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String matrikel = _matrikel.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (matrikel.isEmpty() || matrikel.length() < 2 || matrikel.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _matrikel.setError(null);
        }

        return valid;
    }
}
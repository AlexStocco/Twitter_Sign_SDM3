package br.edu.ifspsaocarlos.sdm3.tsignsdm3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.*;
import com.twitter.sdk.android.core.identity.*;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "erPcLKzQIpIug6Gtcm5OZkhzY";
    private static final String TWITTER_SECRET = " iCGsKJpON9sPpwUvulCbhPZlrhxTeVmfj0c9ggyHvp5vTAsBl5";
    private TwitterLoginButton loginButton;
    private TextView status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        setContentView(R.layout.activity_main);

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            public void success(Result<TwitterSession> result) {
                TwitterSession session = result.data;
                TextView userName = (TextView)findViewById(R.id.userName);
                userName.setText("USUÁRIO: " + session.getUserName());
                TextView userId = (TextView)findViewById(R.id.userId);
                userId.setText("ID: " + session.getUserId());
            }
            public void failure(TwitterException exception) {
                Log.d("T Sign SDM3", "Erro ao efetuar login", exception);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

}

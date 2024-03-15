package fpoly.haonaph45181.duanmau;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fpoly.haonaph45181.duanmau.R;
import fpoly.haonaph45181.duanmau.login_logout.login;

public class manhinhchao extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchao);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(manhinhchao.this,login.class);
                startActivity(intent);
            }
        },3000);
    }
}

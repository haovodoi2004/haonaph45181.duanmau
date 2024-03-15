package fpoly.haonaph45181.duanmau.login_logout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import fpoly.haonaph45181.duanmau.Dao.ThuThuDAO;
import fpoly.haonaph45181.duanmau.login_logout.login;
import fpoly.haonaph45181.duanmau.R;
import fpoly.haonaph45181.duanmau.MainActivity;

public class login extends AppCompatActivity {
    Button btlogin,btlogout;
   TextInputEditText eduser,edpass;
    CheckBox checkBox;
    String user,pass;
    ThuThuDAO thuThuDAO;
    Boolean check;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btlogin=findViewById(R.id.button);
        btlogout=findViewById(R.id.button2);
        checkBox=findViewById(R.id.checkBox);
        eduser=findViewById(R.id.textInput);
        edpass=findViewById(R.id.textInput2);
        thuThuDAO=new ThuThuDAO(this);
        SharedPreferences preferences=getSharedPreferences("userfile",MODE_PRIVATE);
        user=preferences.getString("username","");
        pass=preferences.getString("password","");
        check=preferences.getBoolean("remember",false);

        eduser.setText(user);
        edpass.setText(pass);
        checkBox.setChecked(check);



        btlogin.setOnClickListener(v->{

            checklogin();
        });
    }

    public void checklogin(){
        String username=eduser.getText().toString();
        String password=edpass.getText().toString();
        Boolean t=thuThuDAO.checkLogin(username,password);
        if(username.isEmpty()||password.isEmpty()){
            Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
        }else{
            if( t==true){
                Toast.makeText(this, "Bạn đã đăng nhập thành công", Toast.LENGTH_SHORT).show();
                rememberuser(username,password,checkBox.isChecked());
                Intent intent=new Intent(login.this,MainActivity.class);
                intent.putExtra("user",username);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "bạn nhập sai", Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void rememberuser(String u, String p, Boolean r){
        SharedPreferences sharedPreferences=getSharedPreferences("userfile",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if(!r){
            editor.clear();
        }else{
            editor.putString("username",u);
            editor.putString("password",p);
            editor.putBoolean("check",r);
        }
        editor.commit();
    }
}

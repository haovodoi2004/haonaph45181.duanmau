package fpoly.haonaph45181.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import fpoly.haonaph45181.duanmau.Fragment.doanhthuFragment;
import fpoly.haonaph45181.duanmau.Fragment.doimkFragment;
import fpoly.haonaph45181.duanmau.Fragment.quanlyloaisachFragment;
import fpoly.haonaph45181.duanmau.Fragment.quanlyphieumuonFragment;
import fpoly.haonaph45181.duanmau.Fragment.quanlysachFragment;
import fpoly.haonaph45181.duanmau.Fragment.quanlythanhvienFragment;
import fpoly.haonaph45181.duanmau.Fragment.themnguoidungFragment;
import fpoly.haonaph45181.duanmau.Fragment.top10Fragment;
import fpoly.haonaph45181.duanmau.login_logout.login;

public class MainActivity extends AppCompatActivity {
    MaterialToolbar toolbar;
    FrameLayout frameLayout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Fragment fragment;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        frameLayout=findViewById(R.id.fr);
        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.navi);

        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.phieumuon) {
                    fragment = new quanlyphieumuonFragment();
                    getfragment(fragment);
                    toolbar.setTitle("quản lý phiếu mượn");
                }
                if (item.getItemId() == R.id.sach) {
                    fragment = new quanlysachFragment();
                    getfragment(fragment);
                    toolbar.setTitle("quản lý sách");
                }
                if (item.getItemId() == R.id.loaisach) {
                    fragment = new quanlyloaisachFragment();
                    getfragment(fragment);
                    toolbar.setTitle("quản lý loại sách");
                }
                if (item.getItemId() == R.id.thanhvien) {
                    fragment = new quanlythanhvienFragment();
                    getfragment(fragment);
                    toolbar.setTitle("quản lý thành viên");
                }
                if (item.getItemId() == R.id.top10) {
                    fragment = new top10Fragment();
                    getfragment(fragment);
                    toolbar.setTitle("top 10 sách");
                }
                if (item.getItemId() == R.id.doanhthu) {
                    fragment = new doanhthuFragment();
                    getfragment(fragment);
                    toolbar.setTitle("quản lý doanh thu");
                }

                if (item.getItemId() == R.id.mk) {
                    fragment = new doimkFragment();
                    getfragment(fragment);
                    toolbar.setTitle("đổi mật khẩu");
                }
                if (item.getItemId() == R.id.logout) {
                    Intent intent=new Intent(MainActivity.this, login.class);
                    startActivity(intent);
                }
                return true;
            }
        });


    }
    public void getfragment(Fragment fragment){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.fr,fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
package team.hashbash.sangarodhak;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import team.hashbash.sangarodhak.Adapters.SlidePageAdapter;
import team.hashbash.sangarodhak.Fragments.FragmentDonate;
import team.hashbash.sangarodhak.Fragments.FragmentFunZone;
import team.hashbash.sangarodhak.Fragments.FragmentHome;
import team.hashbash.sangarodhak.Fragments.FragmentNotice;
import team.hashbash.sangarodhak.Fragments.FragmentSupplies;

import team.hashbash.sangarodhak.R;

import java.util.ArrayList;


public class DashBoardActivity extends AppCompatActivity {

    public static ViewPager viewPage;
    public static PagerAdapter adapter;

    BottomNavigationView bottomNavigationView;

    boolean openAttendance, openAnnouncement, doubleTap;

    FragmentHome home;

    //ArrayList<Fragment> resultList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dash_board);

        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentNotice());
        fragments.add(new FragmentFunZone());
        fragments.add(new FragmentHome());
        fragments.add(new FragmentSupplies());
        fragments.add(new FragmentDonate());

        viewPage = findViewById(R.id.main_fragment_pager);
        bottomNavigationView = findViewById(R.id.bottom_nav);

        adapter = new SlidePageAdapter(getSupportFragmentManager(), fragments);

        viewPage.setAdapter(adapter);

        viewPage.setCurrentItem(2);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        viewPage.setOffscreenPageLimit(5);

        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int id = R.id.nav_home;
                if (position == 0)
                    id = R.id.nav_notice;
                else if (position == 1)
                    id = R.id.nav_fun_zone;
                else if (position == 2)
                    id = R.id.nav_home;
                else if (position == 3)
                    id = R.id.nav_supplies;
                else if (position == 4)
                    id = R.id.nav_donate;

                bottomNavigationView.setSelectedItemId(id);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nav_notice:
                        viewPage.setCurrentItem(0, false);
                        item.setChecked(true);
                        break;
                    case R.id.nav_fun_zone:
                        viewPage.setCurrentItem(1, false);
                        item.setChecked(true);
                        break;
                    case R.id.nav_home:
                        viewPage.setCurrentItem(2, false);
                        item.setChecked(true);
                        break;
                    case R.id.nav_supplies:
                        viewPage.setCurrentItem(3, false);
                        item.setChecked(true);
                        break;
                    case R.id.nav_donate:
                        viewPage.setCurrentItem(4, false);
                        item.setChecked(true);
                        break;


                }
                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (viewPage.getCurrentItem() != 2) {
            viewPage.setCurrentItem(2);
            bottomNavigationView.setSelectedItemId(R.id.nav_home);
        } else {
            if (doubleTap)
                super.onBackPressed();
            else {
                doubleTap = true;
                Snackbar.make(findViewById(R.id.snack_bar_holder), "Tap again to exit!", Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(getColor(R.color.colorAccent))
                        .show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleTap = false;
                    }
                }, 1800);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentNotice());
        fragments.add(new FragmentDonate());
        fragments.add(new FragmentHome());
        fragments.add(new FragmentSupplies());
        fragments.add(new FragmentFunZone());

        adapter = new SlidePageAdapter(getSupportFragmentManager(), fragments);
    }

}
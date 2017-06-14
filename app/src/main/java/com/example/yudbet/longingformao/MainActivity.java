package com.example.yudbet.longingformao;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.yudbet.longingformao.fragments.FavoritesFragment;
import com.example.yudbet.longingformao.fragments.HomeFragment;
import com.example.yudbet.longingformao.fragments.NotificationFragment;
import com.example.yudbet.longingformao.fragments.PostFragment;
import com.example.yudbet.longingformao.fragments.ProfileFragment;
import com.example.yudbet.longingformao.items.Notification;
import com.example.yudbet.longingformao.items.Pet;
import com.example.yudbet.longingformao.items.Post;
import com.example.yudbet.longingformao.items.User;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public  final static int CAMERA = 66 ;
    public final static int PHOTO = 99 ;

    BottomBar bottomBar;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();
        initBottomBar();
    }

    public void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    Fragment fragment = null;
    public void initBottomBar() {
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                //myScrollingContent.setNestedScrollingEnabled(true);

                switch (tabId) {
                    case R.id.tab_home:
                        //fragment = new MapviewFragment();
                        fragment = new HomeFragment();
                        //myScrollingContent.setNestedScrollingEnabled(false);
                        break;
                    case R.id.tab_notification:
                        fragment = new NotificationFragment();
                        break;
                    case R.id.tab_post:
                        fragment = new PostFragment();
                        break;
                    case R.id.tab_favorites:
                        fragment = new FavoritesFragment();
                        break;
                    case R.id.tab_profile:
                        fragment = new ProfileFragment();
                        break;
                    default:
                        break;
                }

                FragmentManager fm = getSupportFragmentManager();
                transaction = fm.beginTransaction();
                transaction.replace(R.id.contentContainer, fragment);
                transaction.commit();
            }
        });
    }

    public static ArrayList<MarkerOptions> getSampleMarkers() {
        final ArrayList<MarkerOptions> markers = new ArrayList<>();
        ArrayList<Pet> pets = getSamplePets();

        int len = pets.size();
        for (int i = 0; i < len; i++) {
            /**  Truth implementation: use Picasso to load image from url */
            final Pet pet = pets.get(i);
            markers.add(new MarkerOptions()
                    .position(pet.getPosition())
                    .title(pet.getName() + " - " + pet.getSch_name())
                    .snippet(pet.getImg_url())); // use snippet to store image url
        }

        return markers;
    }

    /**
     * sample source:  https://www.aquapets-show.com.tw/taipei/vote/voteList.asp?id=1
     */
    public static ArrayList<Pet> getSamplePets() {
        ArrayList<Pet> pets = new ArrayList<>();
        ArrayList<Integer> posts = new ArrayList<>();
        ArrayList<Integer> followers = new ArrayList<>();

        for (int i = 0; i < 128; i++) posts.add(i);
        for (int i = 0; i < 176; i++) followers.add(i);

        pets.add(new Pet(0, 20, 1, false, new LatLng(25.135448, 121.539907),
                "小花", "文化大學", "棕色混白色", "https://www.aquapets-show.com.tw/taipei/files/vote/1072012506145531_16_1_1.jpg",
                posts, followers));
        pets.add(new Pet(1, 10, 1, false, new LatLng(24.181587, 120.603122),
                "皺眉(阿冏)", "東海大學", "土黃色", "https://www.aquapets-show.com.tw/taipei/files/vote/980263302004604_35_1_1.jpg",
                posts, followers));
        pets.add(new Pet(2, 8, 0, false, new LatLng(24.794725, 120.993232),
                "Lally", "清華大學", "黑色", "https://www.aquapets-show.com.tw/taipei/files/vote/957669390920733_68_1_1.JPG",
                posts, followers));
        pets.add(new Pet(3, 7, 1, false, new LatLng(24.945217, 121.372715),
                "牛牛", "臺北大學", "白色", "https://www.aquapets-show.com.tw/taipei/files/vote/1013288242029769_52_1_1.jpg",
                posts, followers));
        pets.add(new Pet(4, 16, 1, false, new LatLng(22.904693, 120.273054),
                "虎皮", "長榮大學", "土色", "https://www.aquapets-show.com.tw/taipei/files/vote/1067508969996033_9_1_1.jpg",
                posts, followers));
        pets.add(new Pet(5, 6, 0, false, new LatLng(24.926689, 121.372835),
                "可樂", "安溪國中", "土黃色", "https://www.aquapets-show.com.tw/taipei/files/vote/10204108790975500_47_1_1.JPG",
                posts, followers));
        pets.add(new Pet(6, 3, 1, false, new LatLng(25.175339, 121.450003),
                "山下", "淡江大學", "黑色", "https://www.aquapets-show.com.tw/taipei/files/vote/472718062935804_25_1_1.jpg",
                posts, followers));

        return pets;
    }

    public static ArrayList<Post> getSamplePost() {
        ArrayList<Post> posts = new ArrayList<>();
        ArrayList<String> responsers = new ArrayList<>(Arrays.asList(new String[]{"l0l0lll", "jhon0418", "amy1216"}));
        ArrayList<String> comments = new ArrayList<>(Arrays.asList(new String[]{"嗨大家好", "好可愛啊~", "我也要跟她玩~"}));

        posts.add(new Post(0, 0, 0, false,
                "l0l0lll", "https://i.ytimg.com/vi/fWvGWCDOcK4/maxresdefault.jpg",
                "小花 - 文化大學", "https://www.aquapets-show.com.tw/taipei/files/vote/1072012506145531_16_1_1.jpg",
                "https://www.aquapets-show.com.tw/taipei/files/vote/1072012506145531_16_1_1.jpg",
                responsers, comments, 122));
        posts.add(new Post(0, 1, 1, false,
                "jhon0418", "https://i.ytimg.com/vi/fWvGWCDOcK4/maxresdefault.jpg",
                "虎皮 - 長榮大學", "https://www.aquapets-show.com.tw/taipei/files/vote/1067508969996033_9_1_1.jpg",
                "https://www.aquapets-show.com.tw/taipei/files/vote/1067508969996033_9_1_1.jpg",
                responsers, comments, 42));
        posts.add(new Post(0, 6, 2, false,
                "amy1216", "https://i.ytimg.com/vi/fWvGWCDOcK4/maxresdefault.jpg",
                "可樂 - 安溪國中", "https://www.aquapets-show.com.tw/taipei/files/vote/10204108790975500_47_1_1.JPG",
                "https://www.aquapets-show.com.tw/taipei/files/vote/10204108790975500_47_1_1.JPG",
                responsers, comments, 83));
        posts.add(new Post(0, 4, 1, false,
                "jhon0418", "https://img.moegirl.org/common/thumb/c/cd/10000.jpg/250px-10000.jpg",
                "可樂 - 安溪國中", "https://www.aquapets-show.com.tw/taipei/files/vote/10204108790975500_47_1_1.JPG",
                "https://www.aquapets-show.com.tw/taipei/files/vote/10204108790975500_47_1_1.JPG",
                responsers, comments, 72));
        posts.add(new Post(0, 2, 2, false,
                "l0l0lll", "https://i.ytimg.com/vi/fWvGWCDOcK4/maxresdefault.jpg",
                "小花 - 文化大學", "https://www.aquapets-show.com.tw/taipei/files/vote/1072012506145531_16_1_1.jpg",
                "https://www.aquapets-show.com.tw/taipei/files/vote/957669390920733_68_1_1.JPG",
                responsers, comments, 62));
        posts.add(new Post(0, 1, 0, false,
                "l0l0lll", "https://img.moegirl.org/common/thumb/c/cd/10000.jpg/250px-10000.jpg",
                "Lally - 清華大學", "https://www.aquapets-show.com.tw/taipei/files/vote/957669390920733_68_1_1.JPG",
                "https://www.aquapets-show.com.tw/taipei/files/vote/957669390920733_68_1_1.JPG",
                responsers, comments, 56));
        posts.add(new Post(0, 1, 1, false,
                "jhon0418", "https://i.ytimg.com/vi/fWvGWCDOcK4/maxresdefault.jpg",
                "虎皮 - 長榮大學", "https://www.aquapets-show.com.tw/taipei/files/vote/1067508969996033_9_1_1.jpg",
                "https://www.aquapets-show.com.tw/taipei/files/vote/1067508969996033_9_1_1.jpg",
                responsers, comments, 42));
        posts.add(new Post(0, 6, 2, false,
                "amy1216", "https://i.ytimg.com/vi/fWvGWCDOcK4/maxresdefault.jpg",
                "可樂 - 安溪國中", "https://www.aquapets-show.com.tw/taipei/files/vote/10204108790975500_47_1_1.JPG",
                "https://www.aquapets-show.com.tw/taipei/files/vote/10204108790975500_47_1_1.JPG",
                responsers, comments, 83));
        posts.add(new Post(0, 4, 1, false,
                "jhon0418", "https://img.moegirl.org/common/thumb/c/cd/10000.jpg/250px-10000.jpg",
                "可樂 - 安溪國中", "https://www.aquapets-show.com.tw/taipei/files/vote/10204108790975500_47_1_1.JPG",
                "https://www.aquapets-show.com.tw/taipei/files/vote/10204108790975500_47_1_1.JPG",
                responsers, comments, 72));
        posts.add(new Post(0, 2, 2, false,
                "l0l0lll", "https://i.ytimg.com/vi/fWvGWCDOcK4/maxresdefault.jpg",
                "小花 - 文化大學", "https://www.aquapets-show.com.tw/taipei/files/vote/1072012506145531_16_1_1.jpg",
                "https://www.aquapets-show.com.tw/taipei/files/vote/957669390920733_68_1_1.JPG",
                responsers, comments, 62));
        posts.add(new Post(0, 1, 0, false,
                "l0l0lll", "https://img.moegirl.org/common/thumb/c/cd/10000.jpg/250px-10000.jpg",
                "Lally - 清華大學", "https://www.aquapets-show.com.tw/taipei/files/vote/957669390920733_68_1_1.JPG",
                "https://www.aquapets-show.com.tw/taipei/files/vote/957669390920733_68_1_1.JPG",
                responsers, comments, 56));
        posts.add(new Post(0, 6, 2, false,
                "amy1216", "http://img.jpnest.com/sharingSub/1703/jpn1313420832721936592184.jpg",
                "小花 - 文化大學", "https://www.aquapets-show.com.tw/taipei/files/vote/1072012506145531_16_1_1.jpg",
                "https://www.aquapets-show.com.tw/taipei/files/vote/472718062935804_25_1_1.jpg",
                responsers, comments, 83));
        posts.add(new Post(0, 0, 0, false,
                "jhon0418", "http://img.jpnest.com/sharingSub/1703/jpn1313420832721936592184.jpg",
                "小花 - 文化大學", "https://www.aquapets-show.com.tw/taipei/files/vote/1072012506145531_16_1_1.jpg",
                "https://www.aquapets-show.com.tw/taipei/files/vote/1072012506145531_16_1_1.jpg",
                responsers, comments, 122));
        posts.add(new Post(0, 1, 1, false,
                "jhon0418", "http://img.jpnest.com/sharingSub/1703/jpn1313420832721936592184.jpg",
                "小花 - 文化大學", "https://www.aquapets-show.com.tw/taipei/files/vote/1072012506145531_16_1_1.jpg",
                "https://www.aquapets-show.com.tw/taipei/files/vote/980263302004604_35_1_1.jpg",
                responsers, comments, 42));
        posts.add(new Post(0, 6, 2, false,
                "l0l0lll", "https://i.ytimg.com/vi/fWvGWCDOcK4/maxresdefault.jpg",
                "小花 - 文化大學", "https://www.aquapets-show.com.tw/taipei/files/vote/1072012506145531_16_1_1.jpg",
                "https://www.aquapets-show.com.tw/taipei/files/vote/472718062935804_25_1_1.jpg",
                responsers, comments, 83));

        return posts;
    }

    public static ArrayList<Notification> getSampleNotifications() {
        ArrayList<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification(0, "小花", "文化大學", "https://www.aquapets-show.com.tw/taipei/files/vote/1072012506145531_16_1_1.jpg", 5, 3));
        notifications.add(new Notification(2, "Lally", "清華大學", "https://www.aquapets-show.com.tw/taipei/files/vote/957669390920733_68_1_1.JPG", 8, 2));
        notifications.add(new Notification(3, "牛牛", "臺北大學", "https://www.aquapets-show.com.tw/taipei/files/vote/1013288242029769_52_1_1.jpg", 15, 3));
        notifications.add(new Notification(1, "皺眉(阿冏)", "東海大學", "https://www.aquapets-show.com.tw/taipei/files/vote/980263302004604_35_1_1.jpg", 4, 1));
        notifications.add(new Notification(6, "山下", "淡江大學", "https://www.aquapets-show.com.tw/taipei/files/vote/472718062935804_25_1_1.jpg", 5, 3));
        notifications.add(new Notification(4, "虎皮", "長榮大學", "https://www.aquapets-show.com.tw/taipei/files/vote/1067508969996033_9_1_1.jpg", 7, 2));

        notifications.add(new Notification(0, "小花", "文化大學", "https://www.aquapets-show.com.tw/taipei/files/vote/1072012506145531_16_1_1.jpg", 5, 3));
        notifications.add(new Notification(2, "Lally", "清華大學", "https://www.aquapets-show.com.tw/taipei/files/vote/957669390920733_68_1_1.JPG", 8, 2));
        notifications.add(new Notification(3, "牛牛", "臺北大學", "https://www.aquapets-show.com.tw/taipei/files/vote/1013288242029769_52_1_1.jpg", 15, 3));
        notifications.add(new Notification(1, "皺眉(阿冏)", "東海大學", "https://www.aquapets-show.com.tw/taipei/files/vote/980263302004604_35_1_1.jpg", 4, 1));
        notifications.add(new Notification(6, "山下", "淡江大學", "https://www.aquapets-show.com.tw/taipei/files/vote/472718062935804_25_1_1.jpg", 5, 3));
        notifications.add(new Notification(4, "虎皮", "長榮大學", "https://www.aquapets-show.com.tw/taipei/files/vote/1067508969996033_9_1_1.jpg", 7, 2));
        return notifications;
    }

    public static ArrayList<User> getSampleUser() {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Integer> posts = new ArrayList<>();
        ArrayList<Integer> followings = new ArrayList<>();
        ArrayList<Integer> friends = new ArrayList<>();
        ArrayList<Integer> pets = new ArrayList<>();
        for (int i = 0; i < 231; i++) posts.add(i);
        for (int i = 0; i < 187; i++) followings.add(i);
        for (int i = 0; i < 143; i++) friends.add(i);

        users.add(new User(0, 1, "http://i.magcasa.com.s3-ap-southeast-1.amazonaws.com/content_images/2015/10/28/167576/1446034525_5472.jpg", "l0l0lll", "台科大", posts, followings, friends, pets));
        users.add(new User(0, 0, "https://i.ytimg.com/vi/8p1h8cu3Onk/maxresdefault.jpg", "l0l0lll", "中原大學", posts, followings, friends, pets));
        users.add(new User(0, 1, "http://s30.youmaker.com/other/2013/5-12/oth208572992f46a037f8a4c459885487011f36c0d1d00300.jpg", "l0l0lll", "台科大", posts, followings, friends, pets));
        users.add(new User(0, 0, "http://pic.pimg.tw/moonight8018/1375187690-1637845182_n.jpg", "l0l0lll", "嘉義大學", posts, followings, friends, pets));
        users.add(new User(0, 0, "https://img.moegirl.org/common/thumb/c/cd/10000.jpg/250px-10000.jpg", "l0l0lll", "輔仁大學", posts, followings, friends, pets));
        users.add(new User(0, 1, "https://i.ytimg.com/vi/fWvGWCDOcK4/maxresdefault.jpg", "l0l0lll", "台科大", posts, followings, friends, pets));
        users.add(new User(0, 1, "http://img.jpnest.com/sharingSub/1703/jpn1313420832721936592184.jpg", "l0l0lll", "成功大學", posts, followings, friends, pets));

        return users;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_map) {
            toPetsMapActivity();
        }
        else if (item.getItemId() == R.id.action_post) {
            doPost();
        }

        return super.onOptionsItemSelected(item);
    }

    public void toPetsMapActivity() {
        Intent intent = new Intent(MainActivity.this, PetsMapActivity.class);
        startActivity(intent);
    }

    public void doPost() {
        // post first, then go to home page
        bottomBar.selectTabAtPosition(0);
    }


    public static void refreshFragment(Fragment fragment) {
        FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
        ft.detach(fragment).attach(fragment).commit();
    }


    private ImageView mImg;
    private DisplayMetrics mPhone;
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        mPhone = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mPhone);

        if ((requestCode == CAMERA || requestCode == PHOTO ) && data != null) {
            Uri uri = data.getData();
            ContentResolver cr = this.getContentResolver();

            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));

                //判斷照片為橫向或者為直向
                if (bitmap.getWidth()>bitmap.getHeight()) scalePic(bitmap, mPhone.heightPixels);
                else scalePic(bitmap, mPhone.widthPixels);
            }
            catch (FileNotFoundException e) {
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void scalePic(Bitmap bitmap, int phone) {
        mImg = (ImageView) findViewById(R.id.post_img);

        float mScale = 1;

        //如果圖片寬度大於手機寬度則進行縮放，否則直接將圖片放入ImageView內
        if (bitmap.getWidth() > phone ) {
            //判斷縮放比例
            mScale = (float)phone/(float)bitmap.getWidth();

            Matrix mMat = new Matrix() ;
            mMat.setScale(mScale, mScale);

            Bitmap mScaleBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mMat, false);
            mImg.setImageBitmap(mScaleBitmap);
        }
        else mImg.setImageBitmap(bitmap);
    }

}
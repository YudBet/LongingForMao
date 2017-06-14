package com.example.yudbet.longingformao;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yudbet.longingformao.items.Recommandation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import BeyondQuery.BeyondQueryBuilder;

public class PetsMapActivity extends AppCompatActivity {
    int flag = 0;

    ArrayList<Integer> ans;
    ArrayList<Recommandation> recommandations;
    ImageButton btn_unlike, btn_like;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets_map);

        initToolBar();
    }

    public void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_recommand) {
            startAll();
        }

        return super.onOptionsItemSelected(item);
    }


    int[] currTagCountOfUser = new int[5];
    int[] idxOfItem = new int[5];
    int[] answer = new int[5];
    private void startAll() {
        ans = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            ans.add(0);

        BeyondQueryBuilder builder = new BeyondQueryBuilder(currTagCountOfUser, idxOfItem, answer, getApplicationContext());

        if (flag++ == 0)
            recommandations = builder.genRecomendations();
        else
            recommandations = builder.genRecomendations(builder.computeRecommandResults());

        int startby = 0;
        startRecommand(startby);
    }

    public void startRecommand(final int startby) {
        final Recommandation recommandation = recommandations.get(startby);
        final int img = recommandation.getImg();
        final String type = recommandation.getType();
        final String gender = recommandation.getGender();
        final String location = recommandation.getLocation();
        final String age = recommandation.getAge();

        showRecommandDialog(startby, img, type, gender, location, age);
    }

    public void showRecommandDialog(final int startby, int img, String type, String gender, String location, String age) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog, null);

        builder.setView(dialogView);

        final AlertDialog dialog = builder.create();

        TextView title = (TextView) dialogView.findViewById(R.id.title);
        title.setText("本周認養推薦: " + (startby+1) + "/" + recommandations.size());

        ImageView pet_img = (ImageView) dialogView.findViewById(R.id.pet_img);
        Picasso.with(getApplicationContext())
                .load(img)
                .resize(312, 312)
                .centerCrop()
                .into(pet_img);

        TextView pet_info = (TextView) dialogView.findViewById(R.id.pet_info);
        pet_info.setText(type + "．" + gender + "．" + age + "．" + location);

        btn_like = (ImageButton) dialogView.findViewById(R.id.btn_like);
        btn_unlike = (ImageButton) dialogView.findViewById(R.id.btn_unlike);

        btn_unlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PetsMapActivity.this, "Like!", Toast.LENGTH_SHORT).show();
                ans.set(startby, 1);
                btn_unlike.setVisibility(View.INVISIBLE);
                btn_like.setVisibility(View.VISIBLE);
            }
        });

        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans.set(startby, 0);
                btn_like.setVisibility(View.INVISIBLE);
                btn_unlike.setVisibility(View.VISIBLE);
            }
        });

        ImageButton btn_next =(ImageButton) dialogView.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startby+1 < recommandations.size()) {
                    dialog.dismiss();
                    startRecommand(startby + 1);
                } else {
                    for (int i = 0; i < 5; i++) {
                        idxOfItem[i] = recommandations.get(i).getIdxofanimal();
                        answer[i] = ans.get(i);
                    }
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }
}

package com.example.yudbet.longingformao.fragments;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yudbet.longingformao.MainActivity;
import com.example.yudbet.longingformao.R;
import com.example.yudbet.longingformao.items.Pet;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import static com.example.yudbet.longingformao.MainActivity.CAMERA;

/**
 * Created by Mist on 2017/6/7.
 */
public class PostFragment extends Fragment {

    ImageView mImg;
    ImageButton mCamera;
    ImageButton mPhoto;

    private DisplayMetrics mPhone;
    private EditText txt1, pet_info_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_post, container, false);

        initViews(v);
        // Inflate the layout for this fragment
        return v;
    }

    public void initViews(View v) {
        initPictureGen(v);

        Pet pet = MainActivity.getSamplePets().get(0);
        pet_info_text = (EditText) v.findViewById(R.id.pet_info_text);
        pet_info_text.setText(pet.getPetInfo());

        txt1 = (EditText) v.findViewById(R.id.editText);
    }

    public void initPictureGen(View v) {
        mImg = (ImageView) v.findViewById(R.id.post_img);
        mCamera = (ImageButton) v.findViewById(R.id.camera);
        mPhoto = (ImageButton) v.findViewById(R.id.photo);

        //讀取手機解析度
        mPhone = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mPhone);

        mCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //開啟相機功能
                ContentValues value = new ContentValues();
                value.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                Uri uri= getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, value);
                Intent intent = new Intent("android.provider.MediaStore.ACTION_IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri.getPath());
                getActivity().startActivityForResult(intent, MainActivity.CAMERA);
            }
        });

        mPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //開啟相簿相片集
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                getActivity().startActivityForResult(intent, MainActivity.PHOTO);
            }
        });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_post, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}

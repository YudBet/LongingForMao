package com.example.yudbet.longingformao.recyclerview;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yudbet.longingformao.R;

/**
 * Created by Mist on 2017/6/7.
 */
public class PostsRecyclerViewHolder extends RecyclerView.ViewHolder {
    ImageView usr_img;
    TextView usr_name;
    TextView pet_info;
    ImageView pet_img;

    ImageView img;

    Button like_unchecked, like_checked;
    Button new_comment;
    Button share;

    TextView like_count;

    RecyclerView comments_recycler_view;
    View divider;

    private static Typeface typeFace;

    public PostsRecyclerViewHolder(final View itemView) {
        super(itemView);

        usr_img = (ImageView) itemView.findViewById(R.id.usr_img);
        usr_name = (TextView) itemView.findViewById(R.id.usr_name);
        pet_info = (TextView) itemView.findViewById(R.id.pet_info);
        pet_img = (ImageView) itemView.findViewById(R.id.pet_img);

        img = (ImageView) itemView.findViewById(R.id.img);

        like_unchecked = (Button) itemView.findViewById(R.id.like_unchecked);
        like_checked = (Button) itemView.findViewById(R.id.like_checked);
        new_comment = (Button) itemView.findViewById(R.id.new_comment);
        share = (Button) itemView.findViewById(R.id.share);

        like_count = (TextView) itemView.findViewById(R.id.like_count);

        comments_recycler_view = (RecyclerView) itemView.findViewById(R.id.comments_recycler_view);
        divider = itemView.findViewById(R.id.divider);

        if (typeFace == null)
            typeFace = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/cwTeXHei-zhonly.ttf");
        usr_name.setTypeface(typeFace, Typeface.BOLD);
        pet_info.setTypeface(typeFace);
        like_count.setTypeface(typeFace, Typeface.BOLD);
    }

    public void itemOnClick(View v) {
        /*
        final Context context = v.getContext();
        final Intent intent = new Intent(context, );
        if (context.getClass().getSimpleName().equals("Application"))
            intent.putExtra("ALLOW_CYCLE", false);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);*/
    }
}

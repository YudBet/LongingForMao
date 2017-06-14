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
public class NotificationRecyclerViewHolder extends RecyclerView.ViewHolder {
    ImageView img;
    TextView pet_info;
    TextView new_post_info;
    TextView new_feed_info;
    Button to_pet;
    View divider;

    private static Typeface typeFace;

    public NotificationRecyclerViewHolder(final View itemView) {
        super(itemView);

        img = (ImageView) itemView.findViewById(R.id.img);
        pet_info = (TextView) itemView.findViewById(R.id.pet_info);
        new_post_info = (TextView) itemView.findViewById(R.id.new_post_info);
        new_feed_info = (TextView) itemView.findViewById(R.id.new_feed_info);
        to_pet = (Button) itemView.findViewById(R.id.to_pet);
        divider = itemView.findViewById(R.id.divider);

        if (typeFace == null)
            typeFace = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/cwTeXHei-zhonly.ttf");
        pet_info.setTypeface(typeFace);
        new_post_info.setTypeface(typeFace);
        new_feed_info.setTypeface(typeFace);

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

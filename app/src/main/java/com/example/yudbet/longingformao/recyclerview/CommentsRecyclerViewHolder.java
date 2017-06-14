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
public class CommentsRecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView responser;
    TextView comment;

    private static Typeface typeFace;

    public CommentsRecyclerViewHolder(final View itemView) {
        super(itemView);

        responser = (TextView) itemView.findViewById(R.id.responser);
        comment = (TextView) itemView.findViewById(R.id.comment);

        if (typeFace == null)
            typeFace = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/cwTeXHei-zhonly.ttf");
        responser.setTypeface(typeFace, Typeface.BOLD);
        comment.setTypeface(typeFace);
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

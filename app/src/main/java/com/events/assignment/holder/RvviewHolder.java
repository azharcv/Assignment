package com.events.assignment.holder;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.events.assignment.R;

public   class RvviewHolder  extends RecyclerView.ViewHolder {
    public TextView tv_title,tv_byline,tv_date;


    public RvviewHolder(@NonNull View itemView) {
        super(itemView);

        tv_title=itemView.findViewById(R.id.textview_title);

        tv_byline=itemView.findViewById(R.id.textview_byline);

        tv_date=itemView.findViewById(R.id.tv_date);

    }
}

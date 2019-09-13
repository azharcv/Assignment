package com.events.assignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.events.assignment.R;
import com.events.assignment.activity.DetailActivity;
import com.events.assignment.holder.RvviewHolder;
import com.events.assignment.model.Results;
import java.util.ArrayList;


public class RvAdapter extends RecyclerView.Adapter<RvviewHolder> implements Filterable{
    private Context context;
    private ArrayList<Results> results;
    private ArrayList<Results> filteredresultslist;

    public RvAdapter(Context context, ArrayList<Results> results) {
        this.context = context;
        this.results = results;
        this.filteredresultslist=results;
    }

    @NonNull
    @Override
    public RvviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        //Inflate the layout, initialize the View Holder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);

        return new RvviewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RvviewHolder holder, final int position) {
        holder.tv_title.setText(results.get(position).getTitle());
        holder.tv_byline.setText(results.get(position).getByline());
        holder.tv_date.setText(results.get(position).getPublishedDate());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Results result=results.get(position);

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("mostpopulardata", result);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredresultslist.size();
    }


    /**
     * Filter Method
     * @return
     */
    @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    String charString = charSequence.toString();
                    if (charString.isEmpty()) {
                        filteredresultslist = results;
                    } else {
                        ArrayList<Results> filteredList = new ArrayList<>();
                        for (Results row : results) {

                            // name match condition. this might differ depending on your requirement
                            // here we are looking for name or phone number match
                            if (row.getTitle().toLowerCase().contains(charString.toLowerCase()) || row.getAdxKeywords().contains(charSequence)) {
                                filteredList.add(row);
                            }
                        }

                        filteredresultslist = filteredList;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = filteredresultslist;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    filteredresultslist = (ArrayList<Results>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
        }

}

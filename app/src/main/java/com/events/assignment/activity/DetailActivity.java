package com.events.assignment.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.events.assignment.R;
import com.events.assignment.model.Results;

/**
 * Details Activity
 */
public class DetailActivity extends AppCompatActivity {
    private TextView textView_title,tv_abstrction,tv_source,tv_ad_keyword,tv_byline,tv_publishdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        textView_title=findViewById(R.id.tv_title);

        tv_abstrction=findViewById(R.id.tv_abstraction);

        tv_source=findViewById(R.id.tv_source);

        tv_ad_keyword=findViewById(R.id.tv_adxkeyword);

        tv_byline=findViewById(R.id.tv_byline);

        tv_publishdate=findViewById(R.id.tv_published_Date);

        Intent i = getIntent();
        Results results = (Results) i.getSerializableExtra("mostpopulardata");


        textView_title.setText(results.getTitle());
        tv_abstrction.setText(results.get_abstract());
        tv_source.setText(results.getSource());
        tv_ad_keyword.setText(results.getAdxKeywords());
        tv_byline.setText(results.getByline());
        tv_publishdate.setText(results.getPublishedDate());

    }
}

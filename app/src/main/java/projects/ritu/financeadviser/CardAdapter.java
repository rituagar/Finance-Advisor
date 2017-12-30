package projects.ritu.financeadviser;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import static android.widget.GridLayout.spec;

/**
 * Created by Ritu on 23-04-2017.
 */

public class CardAdapter extends BaseAdapter {
    TextView textview;
    ImageView imageview1;
    CardView cardview;
    Context mContext;

    GridView.LayoutParams layoutparams;
    public Integer[] gridviewImages = {
            R.mipmap.yellowleaf,R.mipmap.ic_launcher
    };
    @Override
    public int getCount() {
        return gridviewImages.length;
    }
    public CardAdapter(Context c) {
        mContext = c;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        cardview = new CardView(mContext);
//        GridLayout.Spec rowspec=spec(56);
//        GridLayout.Spec colspec=spec(23);
        layoutparams = new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        cardview.setLayoutParams(layoutparams);
        cardview.setRadius(6);
        cardview.setCardBackgroundColor(Color.GRAY);
        cardview.setMaxCardElevation(6);
        textview = new TextView(mContext);
        textview.setLayoutParams(layoutparams);
        textview.setText("Hello, World!");
        imageview1 = new ImageView(mContext);
        imageview1.setImageResource(R.mipmap.yellowleaf);
//        textview.setTextAppearance(android.R.style.TextAppearance_Material_Headline);
        textview.setTextColor(Color.BLACK);
        imageview1.setLayoutParams(layoutparams);
        cardview.addView(textview);
        cardview.addView(imageview1);
        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent articleListIntent=new Intent(mContext,ArticleList.class);
                articleListIntent.putExtra("stringtodisplay","bla bla");
                mContext.startActivity(articleListIntent);
            }
        });
        return cardview;
    }
}

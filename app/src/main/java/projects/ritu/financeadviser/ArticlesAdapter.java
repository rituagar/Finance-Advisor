package projects.ritu.financeadviser;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import static projects.ritu.financeadviser.R.id.overflow;

/**
 * Created by Ritu on 02-05-2017.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<projects.ritu.financeadviser.ArticlesAdapter.MyViewHolder> {

        private Context mContext;
        private List<ArticleCard> catList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title, count;
            public ImageView  overflow;
            public TextView thumbnail;
            public View mView;
            public MyViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.title);
                count = (TextView) view.findViewById(R.id.count);
                thumbnail = (TextView) view.findViewById(R.id.thumbnail);
                overflow = (ImageView) view.findViewById(R.id.overflow);
                mView=view;
                mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent articleIntent=new Intent(mContext.getApplicationContext(),Article.class);
                        articleIntent.putExtra("Article Id", mView.getTag().toString());
                        mContext.startActivity(articleIntent);
                    }
                });
            }
        }


        public ArticlesAdapter(Context mContext, List<ArticleCard> catList) {
            this.mContext = mContext;
            this.catList = catList;
        }

        @Override
        public projects.ritu.financeadviser.ArticlesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.article_card, parent, false);

            return new projects.ritu.financeadviser.ArticlesAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final projects.ritu.financeadviser.ArticlesAdapter.MyViewHolder holder, int position) {
            ArticleCard category = catList.get(position);
            holder.title.setText(category.getArticleTitle());
            holder.thumbnail.setText(category.getArticle());
            holder.mView.setTag(category.getArticle());
            // loading album cover using Glide library
//            Glide.with(mContext).load(category.getThumbnail()).into(holder.thumbnail);

            holder.overflow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(holder.overflow);
                }
            });
        }

        /**
         * Showing popup menu when tapping on 3 dots
         */
        private void showPopupMenu(View view) {
            // inflate menu
//        PopupMenu popup = new PopupMenu(mContext, view);
//        MenuInflater inflater = popup.getMenuInflater();
//        inflater.inflate(R.menu.menu_album, popup.getMenu());
//        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
//        popup.show();
        }

        /**
         * Click listener for popup menu items
         */
        class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

            public MyMenuItemClickListener() {
            }

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
//            case R.id.action_add_favourite:
//                Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.action_play_next:
//                Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
//                return true;
                    default:
                }
                return false;
            }
        }

        @Override
        public int getItemCount() {
            return catList.size();
        }

}

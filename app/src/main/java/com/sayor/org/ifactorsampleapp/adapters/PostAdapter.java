package com.sayor.org.ifactorsampleapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sayor.org.ifactorsampleapp.R;
import com.sayor.org.ifactorsampleapp.models.Post;

import java.util.List;

/**
 * Created by Sayor on 1/28/16.
 */
public class PostAdapter extends ArrayAdapter<Post> {
    private String mUserId;

    public PostAdapter(Context context, List<Post> posts) {
        super(context, 0, posts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_post, parent, false);
            final ViewHolder holder = new ViewHolder();
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.tvBody = (TextView) convertView.findViewById(R.id.tvBody);
            convertView.setTag(holder);
        }
        final Post post = (Post) getItem(position);
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        //    holder.tvTitle.setText(post.getTitle());
        //    holder.tvBody.setText(post.getBody());
        return convertView;
    }


    // the best practice is to use viewholder pattern instead of running finding view everytime
    final class ViewHolder {
        public TextView tvTitle;
        public TextView tvBody;
    }

}

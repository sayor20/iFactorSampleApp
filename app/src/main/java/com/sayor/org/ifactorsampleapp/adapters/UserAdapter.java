package com.sayor.org.ifactorsampleapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sayor.org.ifactorsampleapp.R;
import com.sayor.org.ifactorsampleapp.models.User;

import java.util.List;

/**
 * Created by Sayor on 1/28/16.
 */
public class UserAdapter extends ArrayAdapter<User> {
    private String mUserId;

    public UserAdapter(Context context, List<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
            final ViewHolder holder = new ViewHolder();
            holder.tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
            holder.tvAddress = (TextView) convertView.findViewById(R.id.tvAddress);
            convertView.setTag(holder);
        }
        final User user = (User) getItem(position);
        final ViewHolder holder = (ViewHolder) convertView.getTag();

        //    holder.tvTitle.setText(post.getTitle());
        //    holder.tvBody.setText(post.getBody());
        return convertView;
    }

    // the best practice is to use viewholder pattern instead of running finding view everytime
    final class ViewHolder {
        public TextView tvUserName;
        public TextView tvAddress;
    }

}

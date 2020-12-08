package com.hddev.task1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hddev.task1.R;
import com.hddev.task1.model.Post;
import com.hddev.task1.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.hddev.task1.util.Constants.HEAD_TYPE;
import static com.hddev.task1.util.Constants.ITEM_TYPE;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    Context context;
    List<Post> posts;
    Picasso picasso;
    public PostAdapter(Context context, Picasso picasso) {
        this.context = context;
        this.picasso = picasso;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == HEAD_TYPE)
            view = LayoutInflater.from(context).inflate(R.layout.header_item, parent, false);
        else
            view = LayoutInflater.from(context).inflate(R.layout.user_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (getItemViewType(position) == ITEM_TYPE) {
            Post post = getItem(position);
            User user = post.getUser();
            holder.userId.setText(String.valueOf(user.getId()));
            holder.userName.setText(user.getLogin());
            String urlText = user.getReposUrl();
            urlText = urlText.substring(0, urlText.length() - 3).concat("...");
            holder.userUrl.setText(urlText);
            if (post.getState().equals("open")) {
                holder.checkBox.setChecked(true);

            }
            picasso.load(user.getAvatarUrl())
                    .into(holder.userImage);
        }
    }


    @Override
    public int getItemCount() {
        if (posts == null)
            return 1;
        return posts.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEAD_TYPE;
        return ITEM_TYPE;
    }

    public Post getItem(int position) {
        return posts.get(position - 1);
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public class headHolder extends RecyclerView.ViewHolder {

        public headHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView userImage;
        TextView userId;
        TextView userName;
        TextView userUrl;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.user_image);
            userId = itemView.findViewById(R.id.user_id);
            userName = itemView.findViewById(R.id.user_name);
            userUrl = itemView.findViewById(R.id.repo_url);
            checkBox = itemView.findViewById(R.id.user_open_check);

        }
    }
}

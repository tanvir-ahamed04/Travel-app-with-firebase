package com.example.travelappdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    public interface OnDeleteClickListener {
        void onDelete(Post post);
    }

    private final Context context;
    private final List<Post> postList;
    private final OnDeleteClickListener deleteListener;

    // Full constructor (for Admin or Deletion functionality)
    public PostAdapter(Context context, List<Post> postList, OnDeleteClickListener deleteListener) {
        this.context = context;
        this.postList = postList;
        this.deleteListener = deleteListener;
    }

    // Simplified constructor (for read-only use like BestDestinationsActivity)
    public PostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
        this.deleteListener = null;
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription;
        Button btnDelete;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvPostTitle);
            tvDescription = itemView.findViewById(R.id.tvPostDescription);
            btnDelete = itemView.findViewById(R.id.btnDeletePost);
        }
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.tvTitle.setText(post.title);
        holder.tvDescription.setText(post.description);

        // Show or hide delete button based on presence of listener
        if (deleteListener != null) {
            holder.btnDelete.setVisibility(View.VISIBLE);
            holder.btnDelete.setOnClickListener(v -> deleteListener.onDelete(post));
        } else {
            holder.btnDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}

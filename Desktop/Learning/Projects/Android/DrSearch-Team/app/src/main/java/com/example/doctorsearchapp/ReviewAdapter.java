package com.example.doctorsearchapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doctorsearchapp.models.Reviews;
import com.example.doctorsearchapp.models.Users;
import com.parse.ParseFile;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{

    private List<Reviews> reviews;
    private Context context;

    public ReviewAdapter(List<Reviews> reviews, Context context)
    {
        this.reviews = reviews;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.review_layout,parent,false);

        return new ReviewViewHolder(view);
    }

    public void clear()
    {
        reviews.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Reviews> newReviews)
    {
        reviews.addAll(newReviews);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Reviews review = reviews.get(position);
        holder.bind(review);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder
    {
        private TextView usernameTv;
        private TextView reviewTv;
        private ImageView profilePicIv;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTv = itemView.findViewById(R.id.usernameTV);
            reviewTv = itemView.findViewById(R.id.reviewTV);
            profilePicIv = itemView.findViewById(R.id.profilePicIV);

        }

        public void bind(Reviews review)
        {
            Users user = new Users(review.getUser());
            usernameTv.setText(user.getUsername());
            reviewTv.setText(review.getReview());

            if(user.getProfilePic().getUrl() != null)
            {
               Glide.with(context).load(user.getProfilePic().getUrl()).into(profilePicIv);
            }

        }
    }
}

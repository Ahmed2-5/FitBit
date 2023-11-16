package com.example.fitapplitcation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitapplitcation.Entities.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;
    private AdapterListener adapterListener;

    public UserAdapter(AdapterListener listener) {
        this.adapterListener = listener;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, mobileTextView, emailTextView, dobTextView;
        private ImageView delete;

        public UserViewHolder(View itemView) {
            super(itemView);
            // Initialize views
            nameTextView = itemView.findViewById(R.id.nameTextView);
            mobileTextView = itemView.findViewById(R.id.mobileTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            dobTextView = itemView.findViewById(R.id.dobTextView);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_layout, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        // Update views with user data
        holder.nameTextView.setText(user.getName());
        holder.mobileTextView.setText(user.getMobile());
        holder.emailTextView.setText(user.getEmail());
        holder.dobTextView.setText(user.getDateOfBirth());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapterListener != null) {
                    adapterListener.onDelete(user.getEmail(), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList != null ? userList.size() : 0;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public void removeUser(int position) {
        if (position >= 0 && position < userList.size()) {
            userList.remove(position);
            notifyItemRemoved(position);
        }
    }
}
package com.example.fitapplitcation;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitapplitcation.Entities.Publication;

import java.util.ArrayList;
import java.util.List;



public class PubAdapter extends RecyclerView.Adapter<PubAdapter.MyViewHolder>{

    private Context context ;
    private List<Publication> publicationList ;

    private AdapterListener adapterListener ;








    public PubAdapter(Context context , AdapterListener listener) {
        this.context = context;
        publicationList=new ArrayList<>();
        this.adapterListener=listener ;


    }

    public void addPublication(Publication publication){
        publicationList.add(publication);
        notifyDataSetChanged();
    }

    public void removePub(int position){
        publicationList.remove(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.publication_row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Publication publication = publicationList.get(position);
        holder.userName.setText(publication.getUserName());
        holder.content.setText(publication.getContent());



        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.OnUpdate(publication);

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.OnDelete(publication.getPubId(),position);

            }
        });

    }

    @Override
    public int getItemCount() {

        return publicationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView userName, content ;
        private ImageView delete, update ;





        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            userName=itemView.findViewById(R.id.userName);
            content=itemView.findViewById(R.id.content);
            delete=itemView.findViewById(R.id.delete);
            update=itemView.findViewById(R.id.update);

        }
    }
}

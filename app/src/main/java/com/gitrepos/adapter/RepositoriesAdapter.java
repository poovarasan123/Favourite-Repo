package com.gitrepos.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.gitrepos.R;
import com.gitrepos.model.RepositoryModel;

import java.util.List;

public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.ViewHolder> {
    Context context;
    List<RepositoryModel> repositoryModelList;

    public RepositoriesAdapter(Context context, List<RepositoryModel> repositoryModelList) {
        this.context = context;
        this.repositoryModelList = repositoryModelList;
    }

    @NonNull
    @Override
    public RepositoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.respositories, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoriesAdapter.ViewHolder holder, int pos) {
        int position = holder.getAdapterPosition();
        holder.tv_owner.setText(repositoryModelList.get(position).getName());
        holder.tv_description.setText(repositoryModelList.get(position).getDescription());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(repositoryModelList.get(position).getHtml_url()));
                context.startActivity(i);
            }
        });

        holder.img_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = repositoryModelList.get(position).getName() + "\n" + repositoryModelList.get(position).getHtml_url();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, content);
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, "Share");
                context.startActivity(shareIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return repositoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout layout;
        TextView tv_owner;
        TextView tv_description;
        ImageView img_share;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            tv_owner = itemView.findViewById(R.id.tv_owner);
            tv_description = itemView.findViewById(R.id.tv_description);
            img_share = itemView.findViewById(R.id.img_share);
        }
    }
}

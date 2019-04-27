package com.logistic.splashactivity.View.AdminAdapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import Model.OffersList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class offerListAdapters extends RecyclerView.Adapter<offerListAdapters.ViewHolder>
{

    Context context;
    RecyclerView recyclerView;
    List<OffersList> offersLists=new ArrayList<>();

    public offerListAdapters(Context context, RecyclerView recyclerView
            , List<OffersList> offersLists) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.offersLists = offersLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context)
                .inflate(com.logistic.splashactivity.R.layout.custom_offer_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final OffersList offers=offersLists.get(position);
        Glide.with(context).load(offers.getImageUrl()).into(holder.imageView);
        holder.textViewTitle.setText(offers.getTitle());
        holder.textViewDssc.setText(offers.getDesc());
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key=offers.getOfferKey();
                final ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Deleting");
                progressDialog.show();
                CollectionReference collectionReference= FirebaseFirestore.getInstance()
                        .collection("BrandMore")
                        .document("AdminOffers")
                        .collection("Offer1");
                collectionReference.document(key).delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()){
                                    progressDialog.dismiss();
                                    offersLists.remove(position);
                                }
                                else {
                                    progressDialog.dismiss();
                                    Toast.makeText(context, "Failed !", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }

    @Override
    public int getItemCount() {
        return offersLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView,imageViewDelete;
        TextView textViewTitle,textViewDssc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(com.logistic.splashactivity.R.id.imageViewOffers);
            textViewTitle=itemView.findViewById(com.logistic.splashactivity.R.id.titleEditText);
            textViewDssc=itemView.findViewById(com.logistic.splashactivity.R.id.descEditText);
            imageViewDelete=itemView.findViewById(com.logistic.splashactivity.R.id.deleteImage);
        }
    }


}

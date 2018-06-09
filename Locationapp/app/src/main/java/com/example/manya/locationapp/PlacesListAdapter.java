package com.example.manya.locationapp;

/**
 * Created by Manya on 13/06/2017.
 */
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.PlaceBuffer;

import static java.security.AccessController.getContext;

public class PlacesListAdapter  extends RecyclerView.Adapter<PlacesListAdapter.PlaceViewHolder>  {
    private Context mContext;
    private PlaceBuffer mPlaces;
    public PlacesListAdapter(Context context,PlaceBuffer places) {
        this.mContext = context;
        this.mPlaces=places;
    }


    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_place_card, parent, false);
        return new PlaceViewHolder(view);
    }
    public void onBindViewHolder(final PlaceViewHolder holder, int position) {
        String placeName = mPlaces.get(position).getName().toString();
        final String placeAddress = mPlaces.get(position).getAddress().toString();
        holder.nameTextView.setText(placeName);
        holder.nameTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int adapterPos = holder.getAdapterPosition();
                if(adapterPos != RecyclerView.NO_POSITION) {
                   // Toast.makeText(mContext , "Your Message", Toast.LENGTH_LONG).show();
                    //Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    //        Uri.parse("google.navigation:q="+placeAddress));
                   // mContext.startActivity(intent);
Intent intent=new Intent(mContext,Main4Activity.class);
                    mContext.startActivity(intent);
                }
            }
        });
        holder.addressTextView.setText(placeAddress);
    }
    public void swapPlaces(PlaceBuffer newPlaces){
        mPlaces = newPlaces;
        if (mPlaces != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }
    public int getItemCount() {
        if(mPlaces==null) return 0;
        return mPlaces.getCount();
        }
    class PlaceViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView addressTextView;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            addressTextView = (TextView) itemView.findViewById(R.id.address_text_view);
        }

    }


}

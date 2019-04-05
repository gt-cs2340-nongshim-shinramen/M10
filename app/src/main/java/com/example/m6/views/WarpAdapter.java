package com.example.m6.views;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.m6.R;
import com.example.m6.model.Planet;
import com.example.m6.model.Player;
import com.example.m6.model.SolarSystem;
import com.example.m6.viewmodels.c;

import java.util.List;


/**
 *
 */
@SuppressWarnings("ALL")
public class WarpAdapter extends RecyclerView.Adapter<WarpAdapter.WarpViewHolder>  {
    // --Commented out by Inspection:private static final String TAG = "WarpAdapter";
    private final Player player;
    private final List<SolarSystem> validPlanet;
    private OnDestinationClickListener listener;

    /**
     *
     * @param mplayer the passed in player that will warp to other planet
     */
    public WarpAdapter(Player mplayer){
        player = mplayer;
        //since solar system == planet in our current system
        List<SolarSystem> planetList = player.getUniverse().getSystem();
        validPlanet = c.validPlanet(player, planetList);
//        for(SolarSystem e : validPlanet){
//            Log.d("test", e.getPlanet().getName());
//        }
    }

    @NonNull
    @Override
    public WarpViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.destination, viewGroup, false);
        return new WarpAdapter.WarpViewHolder(itemView);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull WarpViewHolder warpViewHolder, int i) {
        SolarSystem planets = validPlanet.get(i);
        warpViewHolder.destination.setText(planets.getPlanet().getName());
        int d = (int) distanceBetween(player.getCurrentplanet().getCoordinateX(),
                player.getCurrentplanet().getCoordinateY(),
                                planets.getPlanet().getCoordinateX(),
                                planets.getPlanet().getCoordinateY());
        Log.d("distance", String.valueOf(d)+ " to "+ planets.getPlanet().getName());
        warpViewHolder.distance.setText(d+" LY");
        int require_fuel = (int)(d/player.getSpaceship().getEfficiency());

        warpViewHolder.rfuel.setText(require_fuel+"");
    }

    @Override
    public int getItemCount() {
        return validPlanet.size();
    }
    private double distanceBetween(int x1, int y1, int x2, int y2) {
        return Math. sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
    }

    public class WarpViewHolder extends RecyclerView.ViewHolder {

        final TextView destination;
        final TextView distance;
        final TextView rfuel;
        WarpViewHolder(@NonNull View itemView) {
            super(itemView);
            destination = itemView.findViewById(R.id.destination_planet);
            distance = itemView.findViewById(R.id.destination_distance);
            rfuel = itemView.findViewById(R.id.destination_requiredFuel);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if ((listener != null) && (position != RecyclerView.NO_POSITION)) {
                        listener.onDestinationClicked(validPlanet.get(position).getPlanet(), rfuel);
                    }
                }
            });
        }

    }

    /**
     *  Implementation of onClickListener
     */
    public interface OnDestinationClickListener{

        /**
         *
         * @param planet the destination
         * @param rfuel required fuel for the warp
         */
        void onDestinationClicked(Planet planet, TextView rfuel);
    }

    /**
     *
     * @param listener the respond for action on click
     */
    public void setOnDestinationClickListener(OnDestinationClickListener listener) {
        this.listener = listener;
    }
}

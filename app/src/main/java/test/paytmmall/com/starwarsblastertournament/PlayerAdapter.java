package test.paytmmall.com.starwarsblastertournament;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class PlayerAdapter extends RecyclerView.Adapter<PlayerViewHolder> {

    //private List<Player> movieList = new ArrayList<>();
    private OnPlayerClickListener listener;
    private Context context;
    private HashMap<Integer, Integer> integerIntegerHashMap;

    public PlayerAdapter(Context context) {
        this.context = context;
        integerIntegerHashMap = ApiController.getInstance().getmPlayerScore();
    }


    /*public void setData(List<Player> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }*/

    public void setListener(OnPlayerClickListener listener) {
        this.listener = listener;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return integerIntegerHashMap.size();
    }

//    private int getItem(int position) {
//        return integerIntegerHashMap.get(position);
//    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_player_list, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
       // final Player player = getItem(position);
        PlayerDetails playerData= ApiController.getInstance().mPlayerDetails.get(position);
        holder.txtName.setText(playerData.getName());
        holder.txtScore.setText(integerIntegerHashMap.get(position));
        holder.avatar.setImageUrl(playerData.getIcon(),VolleySingleton.getInstance(context).getImageLoader());
    }

}

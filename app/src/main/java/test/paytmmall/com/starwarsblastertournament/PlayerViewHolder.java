package test.paytmmall.com.starwarsblastertournament;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.android.volley.toolbox.NetworkImageView;


public class PlayerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.rel_parent)
    RelativeLayout relParent;
    @BindView(R.id.img_player)
    public NetworkImageView avatar;
    @BindView(R.id.txt_score)
    public TextView txtScore;
    @BindView(R.id.txt_name)
    public TextView txtName;


    public PlayerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
package test.paytmmall.com.starwarsblastertournament;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements dataFetchDelegate  {

    @BindView(R.id.list_view)
    RecyclerView PlayerListView;

    PlayerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupLayout();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setupLayout() {
        ApiController.getInstance().getMatchList(this);
        ApiController.getInstance().getPlayerList(this, this);

    }

    @Override
    public void didFetchdata() {
        adapter = new PlayerAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        PlayerListView.setLayoutManager(manager);
        PlayerListView.setAdapter(adapter);

        adapter.setListener(new OnPlayerClickListener() {
            @Override
            public void onPlayerClicked() {

            }
        });
    }
}

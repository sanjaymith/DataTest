package test.paytmmall.com.starwarsblastertournament;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApiController {

    public static final String playerList = "https://api.myjson.com/bins/pe6h0";
    public static final  String matchList = "https://api.myjson.com/bins/14a0lg";

    public HashMap<Integer, Integer> getmPlayerScore() {
        return mPlayerScore;
    }

    public void setmPlayerScore(HashMap<Integer, Integer> mPlayerScore) {
        this.mPlayerScore = mPlayerScore;
    }

    // Map of Id and Point scored by player.
    private HashMap<Integer,Integer> mPlayerScore = new HashMap<>();

    HashMap<Integer,PlayerDetails> mPlayerDetails = new HashMap<>();

    HashMap<Integer,MatchesDetails> mCrossMatches = new HashMap<>();

    List<MatchesDetails> matchesDetails = new ArrayList<>();

    private static ApiController mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private ApiController(){
    }

    public static synchronized ApiController getInstance(){
        // If Instance is null then initialize new Instance
        if(mInstance == null){
            mInstance = new ApiController();
        }

        return mInstance;
    }

    public void getPlayerList(Context pContext, final dataFetchDelegate callback) {

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, matchList, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonobject = null;
                            try {
                                jsonobject = response.getJSONObject(i);
                                JSONObject player1Detail = jsonobject.getJSONObject("player1");
                                JSONObject player2Detail = jsonobject.getJSONObject("player2");

                                int player1Score=player1Detail.getInt("score");
                                int player2Score=player2Detail.getInt("score");

                                int player1Id= player1Detail.getInt("id");
                                int player2Id= player2Detail.getInt("id");


                                if(player1Score>player2Score){

                                    if(mPlayerScore.containsKey(player1Id)){
                                        mPlayerScore.put(player1Id,mPlayerScore.get(player1Id)+3);
                                    } else {
                                        mPlayerScore.put(player1Id,3);
                                    }
                                    if(mPlayerScore.containsKey(player2Id)){
                                        mPlayerScore.put(player2Id,mPlayerScore.get(player2Id)+0);
                                    } else {
                                        mPlayerScore.put(player2Id, 0);
                                    }


                                } else if(player1Score==player2Score){

                                    if(mPlayerScore.containsKey(player1Id)){
                                        mPlayerScore.put(player1Id,mPlayerScore.get(player1Id)+1);
                                    } else {
                                        mPlayerScore.put(player1Id,1);
                                    }
                                    if(mPlayerScore.containsKey(player2Id)){
                                        mPlayerScore.put(player2Id,mPlayerScore.get(player2Id)+1);
                                    } else {
                                        mPlayerScore.put(player2Id,1);
                                    }

                                } else {

                                    if(mPlayerScore.containsKey(player1Id)){
                                        mPlayerScore.put(player1Id,mPlayerScore.get(player1Id)+0);
                                    } else {
                                        mPlayerScore.put(player1Id,0);
                                    }
                                    if(mPlayerScore.containsKey(player2Id)){
                                        mPlayerScore.put(player2Id,mPlayerScore.get(player2Id)+3);
                                    } else {
                                        mPlayerScore.put(player2Id,3);
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        callback.didFetchdata();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error Masg",error.getMessage());

                    }
                });

        VolleySingleton.getInstance(pContext).getRequestQueue().add(jsonObjectRequest);

    }

    public void getMatchList(Context pContext) {

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, playerList, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {

                            PlayerDetails person = new PlayerDetails();
                            try {
                                JSONObject data = response.getJSONObject(i);
                                String name = data.getString("name");
                                String icon = data.getString("icon");
                                person.setIcon(icon);
                                person.setName(name);
                                int id = data.getInt("id");
                                mPlayerDetails.put(id,person);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        VolleySingleton.getInstance(pContext).getRequestQueue().add(jsonObjectRequest);

    }
}

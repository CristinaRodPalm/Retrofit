package palmer;

import com.sun.media.jfxmedia.events.PlayerEvent;
import palmer.domain.Player;
import palmer.domain.Position;
import palmer.domain.Statistic;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 12/11/2016.
 */
public class RestSyncCallback {
    private static Retrofit retrofit;
    private PlayerService playerService;
    //LISTA DE PLAYERS
    private List<Player> listPlayer;
    Player player = new Player("Grace", 66, 28, 84, Position.FORWARDS);

    public RestSyncCallback(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:64")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlayerService playerService = retrofit.create(PlayerService.class);
    }

    // GET ALL PLAYERS
    public synchronized void getAllPlayers(PlayerCallback playerCallback){
        Call<List<Player>> callGetAll = playerService.getAllPlayer();
        callGetAll.enqueue(new Callback<List<Player>>(){
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                if(response.isSuccessful()){
                    listPlayer = response.body();
                    playerCallback.onSuccess(listPlayer);
                }
            }
            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                playerCallback.onFailure(t);
            }
        });
    }

    public synchronized void getPlayerID(PlayerCallback playerCallback){
        Call<Player> callGetID = playerService.getPlayerID(1L);
        callGetID.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                if(response.isSuccessful()) playerCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {

            }
        });
    }

    public synchronized void createPlayer(PlayerCallback playerCallback){
        Call<Player> callCreatePlayer = playerService.createPlayer(new Player());
        callCreatePlayer.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                if(response.isSuccessful()) playerCallback.onSuccess(player);
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {

            }
        });
    }
}

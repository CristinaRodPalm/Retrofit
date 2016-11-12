package palmer;

import palmer.domain.Player;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by User on 12/11/2016.
 */
public class RestAsync {
    private static Retrofit retrofit;

    public static void main(String[] args) throws IOException {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:64")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlayerService playerService = retrofit.create(PlayerService.class);

        //GET ALL PLAYERS
        Call<List<Player>> callGetAll = playerService.getAllPlayer();
        callGetAll.enqueue(new Callback<List<Player>>(){
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                System.out.println("Code: "+response.code()+"\n"
                +"GET ALL PLAYERS: "+response.body());
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                System.out.println("GET ALL PLAYERS --> ERROR \n"+
                t.getMessage());
            }
        });
    }

}

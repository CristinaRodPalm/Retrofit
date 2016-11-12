package palmer;

import palmer.domain.Player;
import palmer.domain.Position;
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

        // GET ALL PLAYERS
        Call<List<Player>> callGetAll = playerService.getAllPlayer();
        callGetAll.enqueue(new Callback<List<Player>>(){
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                System.out.println("Code: "+response.code()+"\n"
                +"GET ALL PLAYERS: "+response.body());
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                System.out.println("GET ALL PLAYERS --> ERROR \n"+ t.getMessage());
            }
        });

        // GET PLAYER id
        Call<Player> callGetID = playerService.getPlayerID(1L);
        callGetID.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                System.out.println("Code: "+response.code()+"\n"
                +"GET PLAYER ID: "+response.body());
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                System.out.println("GET ALL PLAYERS --> ERROR \n"+ t.getMessage());
            }
        });

        // POST
        Player player = new Player("Grace", 66, 28, 84, Position.FORWARDS);
        Call<Player> callCreatePlayer = playerService.createPlayer(player);
        callCreatePlayer.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                System.out.println("Code: "+response.code()+"\n"
                +"CREATE PLAYER: "+response.body());
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                System.out.println("CREATE PLAYER --> ERROR \n"+ t.getMessage());
            }
        });

        // DELETE
        Call<Void> callDeletePlayer = playerService.deletePlayerID(6L); //grace, recien creada
        callDeletePlayer.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("Code: "+response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("DELETE PLAYER --> ERROR \n"+ t.getMessage());
            }
        });

        // PUT
        // Creo un jugador para no editar ninguno
        Player p = new Player("Melissa", 12, 8, 34, Position.PIVOT);
        playerService.createPlayer(p);
        p.setName("Modificado");
        Call<Player> callUpdatePlayer = playerService.updatePlayer(p);
        callUpdatePlayer.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                System.out.println("Code: "+response.code()+"\n"
                +"PUT: "+response.body());
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                System.out.println("PUT --> ERROR\n"+t.getMessage());
            }
        });

    }
}

package palmer;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by Cristina on 03/11/2016.
 */
public class RestSync {

    private static Retrofit retrofit;

    public static void main(String[] args) throws IOException {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:64")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlayerService playerService = retrofit.create(PlayerService.class);


        // GET ALL PLAYERS
        Call<List<Player>> callGetAll = playerService.getAllPlayer();
        Response<List<Player>> responseGet = callGetAll.execute();

        if (responseGet.isSuccessful()) {
            List<Player> players = responseGet.body();
            System.out.println("GET ALL \n " +
                    "Code: " + responseGet.code() + "\n" + players);
        } else {
            System.out.println("GET ALL --> ERROR\n " +
                    "Code: " + responseGet.code() + "\n" +
                    "ERROR: " + responseGet.errorBody());
        }

        // POST PLAYER
        Player player = new Player("Jacobo", 27, 16);
        Call<Player> callCreate = playerService.createPlayer(player);
        Response<Player> responseCreate = callCreate.execute();

        if (responseCreate.isSuccessful()) {
            Player playerCreated = responseCreate.body();
            System.out.println("POST \n " +
                    "Code: " + responseCreate.code() + "\n" + playerCreated);

            // PUT PLAYER (id)
            playerCreated.setRebound(100);
            Call<Player> callUpdate = playerService.updatePlayer(playerCreated);
            Response<Player> responseUpdate = callUpdate.execute();

            System.out.println("PUT (id) \n " +
                    "Code: " + responseUpdate.code() + "\n" + responseUpdate.body());

            // DELETE PLAYER (id)
            Call<Void> callDelete = playerService.deletePlayerID(playerCreated.getId());
            Response<Void> responseDelete = callDelete.execute();

            System.out.println("DELETE \n Code: " + responseDelete.code());

            // GET ALL PARA VER SI TODO HA FUNCIONADO
            Call<List<Player>> getAll = playerService.getAllPlayer();
            Response<List<Player>> responseGetAll = getAll.execute();

            if (responseGet.isSuccessful()) {
                List<Player> players = responseGetAll.body();
                System.out.println("GET ALL \n " +
                        "Code: " + responseGetAll.code() + "\n" + players);
            }
        }else {
            System.out.println("POST --> ERROR \n " +
                    "Code: " + responseCreate.code() + "\n" +
                    "ERROR: " + responseCreate.errorBody());
        }

        // GET PLAYER (id)
        Call<Player> callGetID = playerService.getPlayerID(1L);
        Response<Player> responseGetID = callGetID.execute();

        if(responseGetID.isSuccessful()){
            System.out.println("GET (id) \n " +
                    "Code: " + responseGetID.code() + "\n" + responseGetID.body());
        }
    }
}
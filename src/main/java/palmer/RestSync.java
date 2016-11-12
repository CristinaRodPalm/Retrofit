package palmer;

import palmer.domain.Player;
import palmer.domain.Position;
import palmer.domain.Statistic;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
        Player player = new Player("Jacobo", 27, 66, 250, Position.GUARDS);

        Call<Player> callCreate = playerService.createPlayer(player);
        Response<Player> responseCreate = callCreate.execute();

        if (responseCreate.isSuccessful()) {
            Player playerCreated = responseCreate.body();
            System.out.println("POST \n " +
                    "Code: " + responseCreate.code() + "\n" + playerCreated);

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
        Player playerID = responseGetID.body();

        if(responseGetID.isSuccessful()){
            System.out.println("GET (id) \n " +
                    "Code: " + responseGetID.code() + "\n" + responseGetID.body());
        }

        // PUT --> player get with id
        playerID.setBaskets(100000);
        Call<Player> callChange = playerService.updatePlayer(playerID);
        Response<Player> responseChange= callChange.execute();

        System.out.println("PUT (id) \n " +
                "Code: " + responseChange.code() + "\n" + responseChange.body());

        //    -----> Práctica 2

        // GET --> ORDER BY BASKETS DESC
        Call<List<Player>> callOrderByBaskets = playerService.orderByBaskets();
        Response<List<Player>> responseOrderByBaskets = callOrderByBaskets.execute();

        if (responseGet.isSuccessful()) {
            List<Player> players = responseOrderByBaskets.body();
            System.out.println("GET PLAYERS ORDER BY BASKETS \n " +
                    "Code: " + responseOrderByBaskets.code() + "\n" + players);
        } else {
            System.out.println("GET PLAYERS ORDER BY BASKETS --> ERROR\n " +
                    "Code: " + responseOrderByBaskets.code() + "\n" +
                    "ERROR: " + responseOrderByBaskets.errorBody());
        }

        // GET --> BASKETS GREATER THAN EQUAL
        Call<List<Player>> callBasketsGreaterThanEqual = playerService.basketsGreaterThanEqual(3);
        Response<List<Player>> responseBasketsGreatherThanEqual = callBasketsGreaterThanEqual.execute();

        if(responseBasketsGreatherThanEqual.isSuccessful()){
            List<Player> players = responseBasketsGreatherThanEqual.body();
            System.out.println("BASKETS GREATER THAN EQUAL \n" +
                    "Code: " + responseBasketsGreatherThanEqual.code() +"\n" +players);
        }else{
            System.out.println("BASKETS GREATER THAN EQUAL --> ERROR\n " +
                    "Code: " + responseBasketsGreatherThanEqual.code() + "\n" +
                    "ERROR: " + responseBasketsGreatherThanEqual.errorBody());
        }

        //GET --> BASKETS BETWEEN
        Call<List<Player>> callBasketsBetween = playerService.basketsBetween(2, 6);
        Response<List<Player>> responseBasketsBetween =  callBasketsBetween.execute();

        if(responseBasketsBetween.isSuccessful()) {
            List<Player> players = responseBasketsBetween.body();
            System.out.println("BASKETS BETWEEN \n Code: " + responseBasketsBetween.code() + "" +
                    "\n" + players);
        }else{
            System.out.println("BASKETS BETWEEN --> ERROR\n"+"Code: "+responseBasketsBetween.code()+"" +
                    "\n ERROR: "+responseBasketsBetween.errorBody());
        }

        // GET --> AVG BASKETS, ASSISTS, REBOUND FROM ALL PLAYERS IN THE SAME POSITION
        Call<Map<Position, Statistic>> callAvgByPosition = playerService.avgByPosition();
        Response<Map<Position, Statistic>> responseAvgByPosition = callAvgByPosition.execute();

        if(responseAvgByPosition.isSuccessful()){
            Map<Position, Statistic> mapAvgByPosition= responseAvgByPosition.body();
            System.out.println("STATISTICS PLAYERS SAME POSITION \n Code:" +
                    responseAvgByPosition.code()+"\n"+mapAvgByPosition);
        }else{
            System.out.println("STATISTICS PLAYERS SAME POSITION --> ERROR\n Code: "+
                    responseAvgByPosition.code()+"\n"+responseAvgByPosition.errorBody());
        }

        // GET --> SHOW ALL THE PLAYERS WITH THE SAME POSITION ORDERED BY BASKETS --> MULTIMAP
        Call<Map<Position, Collection<Player>>> callStatisticPosition = playerService.statisticFromPosition();
        Response<Map<Position, Collection<Player>>> responseStatisticPosition = callStatisticPosition.execute();

        if(responseStatisticPosition.isSuccessful()){
            Map<Position, Collection<Player>> mapPositionPlayers= responseStatisticPosition.body();
            System.out.println("PLAYERS SAME POSITION \n Code:" +
                    responseStatisticPosition.code()+"\n"+mapPositionPlayers);
            // need to show the map
        }else{
            System.out.println("PLAYERS SAME POSITION --> ERROR\n Code: "+
                    responseStatisticPosition.code()+"\n"+responseStatisticPosition.errorBody());
        }

    }
}
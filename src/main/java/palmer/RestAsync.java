package palmer;

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
                System.out.println("GET ALL PLAYERS \n " +
                        "Code: "+response.code()+"\n"
                        +response.body());
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
                System.out.println("GET PLAYER ID \n" +
                        "Code: "+response.code()+"\n"
                        +response.body());
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
                System.out.println("CREATE PLAYER \n" +
                        "Code: "+response.code()+"\n"
                        +response.body());
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
                System.out.println("DELETE PLAYER \n " +
                        "Code: "+response.code());
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
                System.out.println("PUT PLAYER \n" +
                        "Code: "+response.code()+"\n"
                        +response.body());
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                System.out.println("PUT --> ERROR\n"+t.getMessage());
            }
        });

        //      --> Práctica 2

        // GET --> ORDER BY BASKETS DESC
        Call<List<Player>> callOrderByBaskets = playerService.orderByBaskets();
        callOrderByBaskets.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                System.out.println("Code: "+response.code()+"\n"
                        +"ORDER BY BASKETS DESC: "+response.body());
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                System.out.println("ORDER BY BASKETS DESC --> ERROR\n"+t.getMessage());
            }
        });

        // GET --> BASKETS GREATER THAN EQUAL
        Call<List<Player>> callBasketsGreaterThanEqual = playerService.basketsGreaterThanEqual(5);
        callBasketsGreaterThanEqual.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                System.out.println("Code: "+response.code()+"\n"
                        +"BASKETS GREATER THAN EQUAL: "+response.body());
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                System.out.println("BASKETS GREATER THAN EQUAL --> ERROR\n"+t.getMessage());
            }
        });

        // GET --> BASKETS BETWEEN
        Call<List<Player>> callBasketsBetween = playerService.basketsBetween(2, 10);
        callBasketsBetween.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                System.out.println("Code: "+response.code()+"\n"
                        +"BASKETS BETWEEN: "+response.body());
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                System.out.println("BASKETS BETWEEN --> ERROR\n"+t.getMessage());
            }
        });

        // GET --> AVG BASKETS, ASSISTS, REBOUND FROM ALL PLAYERS SAME POSITION

        Call<Map<Position, Statistic>> callAvgByPosition = playerService.avgByPosition();
        callAvgByPosition.enqueue(new Callback<Map<Position, Statistic>>() {
            @Override
            public void onResponse(Call<Map<Position, Statistic>> call, Response<Map<Position, Statistic>> response) {
                Map<Position, Statistic> mapAvgByPosition= response.body();
                System.out.println("Code: "+response.code()+"\n"
                        +"STATISTICS PLAYERS SAME POSITION: "+mapAvgByPosition);
            }

            @Override
            public void onFailure(Call<Map<Position, Statistic>> call, Throwable t) {
                System.out.println("STATISTICS PLAYERS SAME POSITION --> ERROR\n"+t.getMessage());
            }
        });

        // GET --> ALL PLAYERS SAME POSITION ORDERED BY BASKETS
        Call<Map<Position, Collection<Player>>> callStatisticPosition = playerService.statisticFromPosition();
        callStatisticPosition.enqueue(new Callback<Map<Position, Collection<Player>>>() {
            @Override
            public void onResponse(Call<Map<Position, Collection<Player>>> call, Response<Map<Position, Collection<Player>>> response) {
                Map<Position, Collection<Player>> mapPositionPlayers= response.body();
                System.out.println("ALL PLAYERS SAME POSITION ORDERED BY BASKETS \n Code:" +
                        response.code()+"\n"+mapPositionPlayers);
            }

            @Override
            public void onFailure(Call<Map<Position, Collection<Player>>> call, Throwable t) {
                System.out.println("ALL PLAYERS SAME POSITION ORDERED BY BASKETS --> ERROR\n"+t.getMessage());

            }
        });
    }
}

package palmer;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Cristina on 10/10/2016.
 */

public interface PlayerService {

    // GET ALL PLAYERS
    @GET("/players")
    Call<List<Player>> getAllPlayer();

    // GET PLAYER BY ID
    @GET("/players/{id}")
    Call<Player> getPlayerID(@Path("id") Long id);

    // POST PLAYER
    @POST("/players")
    Call<Player> createPlayer(@Body Player player);

    // PUT PLAYER
    @PUT("/players")
    Call<Player> updatePlayer(@Body Player player);

    // DELETE PLAYER BY ID
    @DELETE("/players/{id}")
    Call<Void> deletePlayerID(@Path("id") Long id);

    // GET --> ORDER BY BASKETS DESC
    @GET("/players/byBaskets")
    Call<List<Player>> orderByBaskets();

    // GET --> BASKETS GREATER THAN EQUAL
    @GET("/players/byBaskets/{num}")
    Call<List<Player>> basketsGreaterThanEqual(@Path("num") int num);

    // GET --> BASKETS BETWEEN MIN AND MAX
    @GET("/players/byBaskets/{min}/{max}")
    Call<List<Player>> basketsBetween(@Path("min") int min, @Path("max") int max);

    @GET("/players/byPosition")
    Call<Map<Position, Statistic>> avgByPosition();

    // GET --> AVG BASKETS, ASSISTS, REBOUNDS FROM PLAYER SAME POSITION
    @GET("/players/playersByPositionBaskets")
    Call<Map<Position, Collection<Player>>> statisticFromPosition();
}

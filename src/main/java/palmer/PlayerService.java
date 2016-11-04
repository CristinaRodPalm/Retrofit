package palmer;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

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

}

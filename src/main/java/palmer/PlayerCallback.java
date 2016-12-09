package palmer;

import palmer.domain.Player;
import retrofit2.Callback;

import java.util.List;

/**
 * Created by User on 16/11/2016.
 */
public interface PlayerCallback {
    void onSuccess(List<Player> playerList);

    void onSuccess(Player player);

    void onFailure(Throwable t);

}

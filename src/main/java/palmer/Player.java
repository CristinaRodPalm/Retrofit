package palmer;

/**
 * Created by Cristina on 10/10/2016.
 */


public class Player {

    private long id;
    private String name;
    private int baskets;
    private int assists;
    private int rebound;

    public Player() {
    }

    public Player(String name, int baskets, int assists) {
        this.name = name;
        this.baskets = baskets;
        this.assists = assists;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaskets() {
        return baskets;
    }

    public void setBaskets(int baskets) {
        this.baskets = baskets;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getRebound() {
        return rebound;
    }

    public void setRebound(int rebound) {
        this.rebound = rebound;
    }

    @Override
    public String toString() {
        return "RESPONSE{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", baskets=" + baskets +
                ", assists=" + assists +
                ", rebound=" + rebound +
                '}';
    }
}
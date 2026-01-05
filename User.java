import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String nid;
    private boolean isAdmin;
    private ArrayList<String> favorites = new ArrayList<>();

    public User(String username, String password, String nid, boolean isAdmin){
        this.username=username;
        this.password=password;
        this.nid=nid;
        this.isAdmin=isAdmin;
    }

    public String getUsername(){ return username; }
    public String getPassword(){ return password; }
    public String getNid(){ return nid; }
    public boolean isAdmin(){ return isAdmin; }

    public void setPassword(String p){ this.password=p; }
    public void setNid(String n){ this.nid=n; }

    public void addFavorite(String fno){ if(!favorites.contains(fno)) favorites.add(fno); }
    public void removeFavorite(String fno){ favorites.remove(fno); }
    public ArrayList<String> getFavorites(){ return favorites; }
}

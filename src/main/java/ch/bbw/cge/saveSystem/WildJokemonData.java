package ch.bbw.cge.saveSystem;

import java.io.Serializable;

public class WildJokemonData implements Serializable {
    private int x;
    private int y;
    private String name;
    private JokemonData jokemonData;
    private boolean isCaught;

    public WildJokemonData(int x, int y, String name, JokemonData jokemonData, boolean isCaught) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.jokemonData = jokemonData;
        this.isCaught = isCaught;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JokemonData getJokemonData() {
        return jokemonData;
    }

    public void setJokemonData(JokemonData jokemonData) {
        this.jokemonData = jokemonData;
    }

    public boolean isCaught() {
        return isCaught;
    }

    public void setCaught(boolean caught) {
        isCaught = caught;
    }
}

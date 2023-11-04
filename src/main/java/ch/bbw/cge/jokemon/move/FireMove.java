package ch.bbw.cge.jokemon.move;

import java.util.ArrayList;
import java.util.List;

public interface FireMove extends Move { // interfaces can extend other interfaces
    Type moveType = Type.FIRE; // variables in interfaces are explicitly public static final
    List<Type> strongAgainst = new ArrayList<>(List.of(Type.GRASS, Type.ICE, Type.BUG, Type.STEEL));
    List<Type> weakAgainst = new ArrayList<>(List.of(Type.FIRE, Type.WATER, Type.ROCK, Type.DRAGON));
}

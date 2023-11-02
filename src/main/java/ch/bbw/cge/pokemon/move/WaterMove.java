package ch.bbw.cge.pokemon.move;

import java.util.ArrayList;
import java.util.List;

public interface WaterMove extends Move { // interfaces can extend other interfaces
    Type moveType = Type.WATER; // variables in interfaces are explicitly public static final

    List<Type> strongAgainst = new ArrayList<>(List.of(Type.FIRE, Type.GROUND, Type.ROCK));
    List<Type> weakAgainst = new ArrayList<>(List.of(Type.WATER, Type.GRASS, Type.DRAGON));
}

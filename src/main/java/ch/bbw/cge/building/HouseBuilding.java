package ch.bbw.cge.building;

import ch.bbw.cge.building.Building;

public class HouseBuilding extends Building {
    private static final char[][] design = {
            {'╔', '═', '╗'},
            {'║', 'H', '║'},
            {'╚', '◫', '╝'}
    };

    public HouseBuilding(int centerX, int centerY, boolean canEnter) {
        super(centerX, centerY, BuildingType.HOUSE, canEnter, design);
    }


    @Override
    public char[][] getBuildingDesign() {
        return design;
    }
}

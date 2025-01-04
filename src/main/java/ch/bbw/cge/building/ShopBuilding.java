package ch.bbw.cge.building;

public class ShopBuilding extends Building {

    private static final char[][] design = {
        {'╔', '═', '╗'},
        {'║', 'S', '║'},
        {'╚', '◫', '╝'}
    };

    public ShopBuilding(int centerX, int centerY, boolean canEnter) {
        super(centerX, centerY, BuildingType.SHOP, canEnter, design);
    }

    @Override
    public char[][] getBuildingDesign() {
        return design;
    }
}

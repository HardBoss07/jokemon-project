package ch.bbw.cge.building;

public class MedicalBuilding extends Building {
    private static final char[][] design = {
            {'╔', '═', '╗'},
            {'║', 'M', '║'},
            {'╚', '◫', '╝'}
    };

    public MedicalBuilding(int centerX, int centerY, boolean canEnter) {
        super(centerX, centerY, BuildingType.MEDICAL, canEnter, design);
    }

    @Override
    public char[][] getBuildingDesign() {
        return design;
    }
}

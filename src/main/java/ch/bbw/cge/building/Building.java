package ch.bbw.cge.building;

public abstract class Building {
    // Enum for different building types
    public enum BuildingType {
        MEDICAL,
        SHOP,
        HOUSE,
        OUTSIDE
    }

    // Common properties
    protected int centerX;
    protected int centerY;
    protected BuildingType buildingType;
    protected boolean canEnter;
    protected char[][] design;

    // Constructor for the abstract class
    public Building(int centerX, int centerY, BuildingType buildingType, boolean canEnter, char[][] design) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.buildingType = buildingType;
        this.canEnter = canEnter;
        this.design = design;
    }

    public char[][] getBuildingDesign() {
        return design;
    }

    // Getter methods for common properties
    public int getX() {
        return centerX;
    }

    public int getY() {
        return centerY;
    }
    public boolean getCanEnter() {
        return canEnter;
    }
    public BuildingType getBuildingType() {
        return buildingType;
    }

    @Override
    public String toString() {
        return "Building{" +
                "centerX=" + centerX +
                ", centerY=" + centerY +
                ", buildingType=" + buildingType +
                ", canEnter=" + canEnter +
                '}';
    }
}

package Components;

public class Floor extends LiftComponent{
    private final int floorNumber;

    public Floor(String name, int floorNumber) {
        super(name);
        this.floorNumber = floorNumber;
    }

    public void openDoors() {
        director.notify(this, "DOORS_OPEN");
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}

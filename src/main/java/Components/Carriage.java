package Components;

public class Carriage extends LiftComponent {
    private int currentFloor;
    private int targetFloor;

    public Carriage(String name, int currentFloor) {
        super(name);
        this.currentFloor = currentFloor;
        targetFloor = currentFloor;
    }

    public void goTo(int floorNumber) {
        targetFloor = floorNumber;
        director.notify(this, "CARRIAGE_MOVING");
        currentFloor = targetFloor;
        director.notify(this, "CARRIAGE_ARRIVING");
    }

    public int getTargetFloor() {
        return targetFloor;
    }
}

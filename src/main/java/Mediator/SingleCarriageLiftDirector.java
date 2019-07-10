package Mediator;

import Components.Carriage;
import Components.Floor;
import Components.LiftComponent;

public class SingleCarriageLiftDirector implements LiftDirector {
    private final LiftLogger logger;
    private Floor floor0;
    private Floor floor1;
    private Carriage liftCarriage;

    public SingleCarriageLiftDirector(LiftLogger logger) {
        this.logger = logger;
    }

    @Override
    public void callLiftToFloor(int floorNumber) {
        liftCarriage.goTo(floorNumber);
    }

    @Override
    public void registerComponent(LiftComponent component) {
        component.setDirector(this);
        //Dislike the magic strings... Need to find a better way which isn't a setter.
        switch (component.getName()){
            case "carriage":
                this.liftCarriage = (Carriage) component;
                break;
            case "floor0":
                this.floor0 = (Floor) component;
                break;
            case "floor1":
                this.floor1 = (Floor) component;
                break;
        }
    }

    @Override
    public void notify(LiftComponent component, String event) {
        final String className = getStrippedClassName(component);
        switch (className){
            case "Carriage":
                handleCarriageEvent((Carriage) component, LiftEvent.valueOf(event));
                break;
            case "Floor":
                handleFloorEvent((Floor) component, LiftEvent.valueOf(event));
                break;
        }
    }

    private String getStrippedClassName(LiftComponent component) {
        return component.getClass().getSimpleName().split("\\$")[0];
    }

    private void handleFloorEvent(Floor floor, LiftEvent liftEvent) {
        switch (liftEvent){
            case DOORS_OPEN:
                floorDoorsOpenEvent(floor);
                break;
            case DOORS_CLOSE:
                floorDoorsCloseEvent(floor);
                break;
        }
    }

    private void floorDoorsCloseEvent(Floor floor) {
        logger.log("Components.Floor "+ floor.getFloorNumber() + " Doors: open.");
    }

    private void floorDoorsOpenEvent(Floor floor) {
        logger.log("Components.Floor "+ floor.getFloorNumber() + " Doors: open.");
    }

    private void handleCarriageEvent(Carriage carriage, LiftEvent liftEvent) {
        switch (liftEvent){
            case CARRIAGE_MOVING:
                carriageMovingEvent(carriage);
                break;
            case CARRIAGE_ARRIVING:
                carriageArrivingEvent(carriage);
                break;
        }
    }

    private void carriageMovingEvent(Carriage carriage) {
        logger.log("Lift Components.Carriage: moving to floor " + carriage.getTargetFloor());
    }

    private void carriageArrivingEvent(Carriage carriage) {
        logger.log("Lift Components.Carriage: arrived at floor " + carriage.getTargetFloor());
        openDoorsOnFloor(liftCarriage.getTargetFloor());
    }

    private void openDoorsOnFloor(int floorNumber) {
        switch (floorNumber){
            case 0:
                floor0.openDoors();
                break;
            case 1:
                floor1.openDoors();
                break;
        }
    }

}

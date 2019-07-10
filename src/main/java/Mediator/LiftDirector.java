package Mediator;

import Components.LiftComponent;

public interface LiftDirector {
    void callLiftToFloor(int floorNumber);

    void registerComponent(LiftComponent component);

    void notify(LiftComponent component, String event);
}

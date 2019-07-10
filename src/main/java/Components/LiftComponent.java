package Components;

import Mediator.LiftDirector;

public class LiftComponent {
    LiftDirector director;

    private String name;

    LiftComponent(String name) {
        this.name = name;
    }

    public void setDirector(LiftDirector director) {
        this.director = director;
    }

    public String getName() {
        return name;
    }
}

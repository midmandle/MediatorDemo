package Acceptance;

import Components.Carriage;
import Components.Floor;
import Components.LiftComponent;
import Mediator.LiftDirector;
import Mediator.LiftLogger;
import Mediator.SingleCarriageLiftDirector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class LiftDirector_AT {

    private final LiftLogger logger = mock(LiftLogger.class);
    private LiftDirector liftDirector;
    private Floor floor0;
    private Floor floor1;
    private Carriage liftCarriage;

    @BeforeEach
    void setUp() {
        liftDirector = new SingleCarriageLiftDirector(logger);
        floor0 = new Floor("floor0", 0);
        floor1 = new Floor("floor1", 1);
        liftCarriage = new Carriage("carriage", 1);

        liftDirector.registerComponent(liftCarriage);
        liftDirector.registerComponent(floor0);
        liftDirector.registerComponent(floor1);
    }

    @Test
    public void carriage_travels_to_floor0_and_opens_doors() {
        liftDirector.callLiftToFloor(0);

        InOrder inOrder = Mockito.inOrder(logger);
        inOrder.verify(logger, times(1)).log("Lift Components.Carriage: moving to floor 0");
        inOrder.verify(logger, times(1)).log("Lift Components.Carriage: arrived at floor 0");
        inOrder.verify(logger, times(1)).log("Components.Floor 0 Doors: open.");
    }
}

package Unit;

import Components.Carriage;
import Components.Floor;
import Mediator.LiftLogger;
import Mediator.SingleCarriageLiftDirector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class SingleCarriageLiftDirectorShould {

    private Carriage liftCarriageSpy;
    private Floor floor0Spy;
    private Floor floor1Spy;
    private LiftLogger loggerMock;
    private SingleCarriageLiftDirector liftDirector;

    @BeforeEach
    void setUp() {
        loggerMock = mock(LiftLogger.class);

        liftDirector = new SingleCarriageLiftDirector(loggerMock);

        liftCarriageSpy = Mockito.spy(new Carriage("carriage", 1));
        floor0Spy = Mockito.spy(new Floor("floor0", 0));
        floor1Spy = Mockito.spy(new Floor("floor1", 1));

        liftDirector.registerComponent(liftCarriageSpy);
        liftDirector.registerComponent(floor0Spy);
        liftDirector.registerComponent(floor1Spy);
    }

    @Test
    void request_a_lift_to_a_floor() {
        liftDirector.callLiftToFloor(0);

        InOrder inOrder = inOrder(liftCarriageSpy, floor0Spy);
        inOrder.verify(liftCarriageSpy, times(1)).goTo(0);
        inOrder.verify(floor0Spy, times(1)).openDoors();
    }
}

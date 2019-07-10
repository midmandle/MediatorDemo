package Unit;

import Components.Carriage;
import Mediator.LiftDirector;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class CarriageShould {
    @Test
    void go_to_the_requested_floor_and_notify_on_arrival() {
        LiftDirector director = mock(LiftDirector.class);

        Carriage carriage = new Carriage("carriage", 1);
        carriage.setDirector(director);
        carriage.goTo(0);

        InOrder inOrder = Mockito.inOrder(director);
        inOrder.verify(director,times(1)).notify(carriage, "CARRIAGE_MOVING");
        inOrder.verify(director,times(1)).notify(carriage, "CARRIAGE_ARRIVING");
    }
}

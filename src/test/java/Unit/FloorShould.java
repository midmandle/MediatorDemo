package Unit;

import Components.Floor;
import Mediator.LiftDirector;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class FloorShould {
    @Test
    void notify_the_director_when_doors_open() {
        LiftDirector director = mock(LiftDirector.class);

        Floor floor = new Floor("floor0", 0);
        floor.setDirector(director);

        floor.openDoors();

        InOrder inOrder = Mockito.inOrder(director);
        inOrder.verify(director,times(1)).notify(floor, "DOORS_OPEN");
    }
}

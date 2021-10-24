package guru.sfg.common.events;

import lombok.NoArgsConstructor;

/**
 * Created by s.shinohara on 2021/10/13.
 */
@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent {
    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}

package guru.sfg.beer.inventory.service.services;

import guru.sfg.beer.inventory.service.config.JmsConfig;
import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import guru.sfg.common.events.NewInventoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by s.shinohara on 2021/10/24.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class NewInventoryListener {

    private final BeerInventoryRepository beerInventoryRepository;

    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent event) {

        log.debug("Got Inventory: " + event.toString());

        beerInventoryRepository.save(
                BeerInventory.builder()
                        .beerId(event.getBeerDto().getId())
                        .upc(event.getBeerDto().getUpc())
                        .quantityOnHand(event.getBeerDto().getQuantityOnHand())
                        .build()
        );
    }
}

package ezee.hotel.management.crud.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezee.hotel.management.crud.dto.KitchenOrderDto;
import ezee.hotel.management.crud.repository.KitchenOrderRepo;

@Service
public class KitchenOrderService {

    @Autowired
    private KitchenOrderRepo kitchenOrderRepo;

    public void saveKitchenOrder(KitchenOrderDto kitchenOrder) {
        kitchenOrderRepo.insertKitchenOrder(kitchenOrder);
    }

    public List<KitchenOrderDto> getAllKitchenOrders() {
        return kitchenOrderRepo.findAll();
    }

    public KitchenOrderDto getKitchenOrderById(int id) {
        return kitchenOrderRepo.findById(id);
    }

    public void updateKitchenOrder(int id, KitchenOrderDto kitchenOrder) {
        kitchenOrderRepo.updateKitchenOrder(id, kitchenOrder);
    }

    public int deleteKitchenOrder(int id) {
        return kitchenOrderRepo.deleteKitchenOrder(id);
    }
}

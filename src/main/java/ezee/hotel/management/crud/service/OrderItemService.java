package ezee.hotel.management.crud.service;
import org.springframework.stereotype.Service;

import ezee.hotel.management.crud.dto.OrderItemDTO;
import ezee.hotel.management.crud.repository.OrderItemRepository;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository repository;

    public OrderItemService(OrderItemRepository repository) {
        this.repository = repository;
    }

    public int addOrderItem(OrderItemDTO dto) {
        return repository.save(dto);
    }

    public List<OrderItemDTO> getAllOrderItems() {
        return repository.findAll();
    }

    public OrderItemDTO getOrderItemById(int id) {
        return repository.findById(id);
    }

    public int updateOrderItem(OrderItemDTO dto) {
        return repository.update(dto);
    }

    public int deleteOrderItem(int id) {
        return repository.delete(id);
    }
}

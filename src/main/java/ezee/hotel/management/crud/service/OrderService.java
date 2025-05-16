package ezee.hotel.management.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezee.hotel.management.crud.dto.OrderDto;
import ezee.hotel.management.crud.repository.OrderRepo;
@Service
public class OrderService {
	@Autowired
	private OrderRepo orderRepo;

	public void insertOrder(OrderDto orderdto) {
		orderRepo.insertOrder(orderdto);
	}

	    public List<OrderDto> getAllOrders() {
	        return orderRepo.findAll();
	    }

	    public OrderDto getOrderById(int id) {
	        return orderRepo.findById(id);
	    }

	    public void updateOrder(int id, OrderDto order) {
	        orderRepo.updateOrder(id, order);
	    }

	    public int deleteOrder(int id) {
	        return orderRepo.deleteOrderById(id);
	    }
	}

	


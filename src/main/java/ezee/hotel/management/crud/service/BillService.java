package ezee.hotel.management.crud.service;

import ezee.hotel.management.crud.dto.BillDto;
import ezee.hotel.management.crud.repository.BillRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepo billRepo;

    public void saveBill(BillDto bill) {
        billRepo.insertBill(bill);
    }

    public List<BillDto> getAllBill() {
        return billRepo.findAll();
    }

    public BillDto getBillById(int id) {
        return billRepo.findById(id);
    }

    public void updateBill(int id, BillDto bill) {
        billRepo.updateBill(id, bill);
    }

    public int deleteBill(int id) {
        return billRepo.deleteBill(id);
    }
}

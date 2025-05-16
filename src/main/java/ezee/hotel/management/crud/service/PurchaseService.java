package ezee.hotel.management.crud.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezee.hotel.management.crud.dto.PurchaseDto;
import ezee.hotel.management.crud.repository.PurchaseRepo;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepo purchaseRepo;

    public void savePurchase(PurchaseDto purchase) {
        purchaseRepo.insertPurchase(purchase);
    }

    public List<PurchaseDto> getAllPurchases() {
        return purchaseRepo.findAll();
    }

    public PurchaseDto getPurchaseById(int id) {
        return purchaseRepo.findById(id);
    }

    public void updatePurchase(int id, PurchaseDto purchase) {
        purchaseRepo.updatePurchase(id, purchase);
    }

    public int deletePurchase(int id) {
        return purchaseRepo.deletePurchase(id);
    }
}

package ezee.hotel.management.crud.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ezee.hotel.management.crud.dto.*;

@Repository
public class PurchaseRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final org.springframework.jdbc.core.RowMapper<PurchaseDto> rowMapper = (rs, rowNum) -> {
        SupplierDto supplier = new SupplierDto();
        supplier.setSupplierId(rs.getInt("supplier_id"));
        supplier.setSupplierName(rs.getString("supplier_name"));
        supplier.setSupplierContactPerson(rs.getString("supplier_contact_person"));
        supplier.setSupplierPhone(rs.getString("supplier_phone"));
        supplier.setSupplierEmail(rs.getString("supplier_email"));

        InventryItemDto inventry=new InventryItemDto();
        inventry.setItemId(rs.getInt("inventory_item_id"));
        inventry.setName(rs.getString("inventory_name"));
        inventry.setQuantity(rs.getDouble("inventory_quantity"));
        inventry.setUnit(rs.getString("inventory_unit"));
        inventry.setLastupdated(rs.getString("inventory_last_updated"));

        PurchaseDto purchase = new PurchaseDto();
        purchase.setPurchaseId(rs.getInt("purchase_id"));
        purchase.setPurchaseQuantity(rs.getInt("purchase_quantity"));
        purchase.setPurchaseOrderDate(rs.getString("purchase_order_date"));
        purchase.setPurchaseDeliveryDate(rs.getString("purchase_delivery_date"));
        purchase.setSupplierDto(supplier);
        purchase.setInventryDto(inventry);

        return purchase;
    };

    public int insertPurchase(PurchaseDto purchase) {
        String sql = "INSERT INTO purchase_order (purchase_id, supplier_id, item_id, purchase_quantity, purchase_order_date, purchase_delivery_date) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            purchase.getPurchaseId(),
            purchase.getSupplierDto().getSupplierId(),
            purchase.getInventryDto().getItemId(),
            purchase.getPurchaseQuantity(),
            purchase.getPurchaseOrderDate(),
            purchase.getPurchaseDeliveryDate()
        );
    }

    public List<PurchaseDto> findAll() {
        String sql = "SELECT p.purchase_id, p.purchase_quantity, p.purchase_order_date, p.purchase_delivery_date, "
        		+ " s.supplier_id, s.supplier_name, s.supplier_contact_person, s.supplier_phone, s.supplier_email, "
        		+ " i.inventory_item_id, i.inventory_name, i.inventory_quantity, i.inventory_unit, i.inventory_last_updated " +
                     "FROM purchase_order p " +
                     "JOIN supplier s ON p.supplier_id = s.supplier_id " +
                     "JOIN inventory_item i ON p.item_id = i.inventory_item_id";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public PurchaseDto findById(int id) {
        String sql =  "SELECT p.purchase_id, p.purchase_quantity, p.purchase_order_date, p.purchase_delivery_date, "
        		+ " s.supplier_id, s.supplier_name, s.supplier_contact_person, s.supplier_phone, s.supplier_email, "
        		+ " i.inventory_item_id, i.inventory_name, i.inventory_quantity, i.inventory_unit, i.inventory_last_updated " +
                     "FROM purchase_order p " +
                     "JOIN supplier s ON p.supplier_id = s.supplier_id " +
                     "JOIN inventory_item i ON p.item_id = i.inventory_item_id " +
                     "WHERE p.purchase_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public int updatePurchase(int id, PurchaseDto purchase) {
        String sql = "UPDATE purchase_order SET supplier_id = ?, item_id = ?, purchase_quantity = ?, purchase_order_date = ?, purchase_delivery_date = ? WHERE purchase_id = ?";
        return jdbcTemplate.update(sql,
                purchase.getPurchaseId(),
                purchase.getSupplierDto().getSupplierId(),
                purchase.getInventryDto().getItemId(),
                purchase.getPurchaseQuantity(),
                purchase.getPurchaseOrderDate(),
                purchase.getPurchaseDeliveryDate()
            );
    }

    public int deletePurchase(int id) {
        String sql = "DELETE FROM purchase_order WHERE purchase_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}

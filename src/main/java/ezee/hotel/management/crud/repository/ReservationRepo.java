package ezee.hotel.management.crud.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ezee.hotel.management.crud.dto.*;

@Repository
public class ReservationRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertReservation(ReservationDto reservation) {
        String sql = "INSERT INTO reservation (reservation_id, customer_id, table_id, reservation_time, reservation_guest_count, reservation_status) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            reservation.getReservationId(),
            reservation.getCustomerDto().getCustomerId(),
            reservation.getTableDto().getTableId(),
            reservation.getReservationTime(),
            reservation.getGuestsCount(),
            reservation.getStatus()
        );
    }

    private final org.springframework.jdbc.core.RowMapper<ReservationDto> rowMapper = (rs, rowNum) -> {
        CustomerDto customer = new CustomerDto();
        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setName(rs.getString("customer_name"));
        customer.setPhone(rs.getLong("customer_phone"));
        customer.setEmail(rs.getString("customer_email"));
        customer.setRegisteredOn(rs.getString("customer_registered_on"));

        TableDto table = new TableDto();
        table.setTableId(rs.getInt("table_id"));
        table.setTableNumber(rs.getInt("table_number"));
        table.setCapacity(rs.getInt("capacity"));
        table.setOccupied(rs.getBoolean("is_occupied"));

        ReservationDto reservation = new ReservationDto();
        reservation.setReservationId(rs.getInt("reservation_id"));
        reservation.setCustomerDto(customer);
        reservation.setTableDto(table);
        reservation.setReservationTime(rs.getString("reservation_time"));
        reservation.setGuestsCount(rs.getInt("reservation_guest_count"));
        reservation.setStatus(rs.getString("reservation_status"));

        return reservation;
    };

    public List<ReservationDto> findAll() {
        String sql = "SELECT r.reservation_id, r.reservation_time, r.reservation_guest_count, r.reservation_status, c.customer_id, c.customer_name, c.customer_phone, c.customer_email, c.customer_registered_on, " +
                     "t.table_id, t.table_number, t.capacity, t.is_occupied " +
                     "FROM reservation r " +
                     "JOIN customer c ON r.customer_id = c.customer_id " +
                     "JOIN table_info t ON r.table_id = t.table_id";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public ReservationDto findById(int id) {
    	String sql = "SELECT r.reservation_id, r.reservation_time, r.reservation_guest_count, r.reservation_status, " +
                "c.customer_id, c.customer_name, c.customer_phone, c.customer_email, c.customer_registered_on, " +
                "t.table_id, t.table_number, t.capacity, t.is_occupied " +
                "FROM reservation r " +
                "JOIN customer c ON r.customer_id = c.customer_id " +
                "JOIN table_info t ON r.table_id = t.table_id " +
                "WHERE r.reservation_id = ?";

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public int updateReservation(int id, ReservationDto reservation) {
        String sql = "UPDATE reservation SET customer_id=?, table_id=?, reservation_time=?, reservation_guest_count=?, reservation_status=? WHERE reservation_id=?";
        return jdbcTemplate.update(sql,
            reservation.getCustomerDto().getCustomerId(),
            reservation.getTableDto().getTableId(),
            reservation.getReservationTime(),
            reservation.getGuestsCount(),
            reservation.getStatus(),
            id
        );
    }

    public int deleteReservation(int id) {
        String sql = "DELETE FROM reservation WHERE reservation_id=?";
        return jdbcTemplate.update(sql, id);
    }
}

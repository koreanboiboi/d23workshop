package workshop3.workshop23.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import workshop3.workshop23.models.OrderDetails;

import static workshop3.workshop23.repositories.Queries.*;

@Repository
public class OrderDetailsRepository {
    
    @Autowired
    private JdbcTemplate temp;

    public OrderDetails getDiscountedOrderDetails (Integer orderId) {

        final List<OrderDetails> ords = new LinkedList<>();

        SqlRowSet result = temp.queryForRowSet(SQL_SELECT_ORDER_DETAILS_DISCOUNT, orderId);

        while(result.next()) {
            ords.add(OrderDetails.create(result));
        }

        return ords.get(0);

    }

}

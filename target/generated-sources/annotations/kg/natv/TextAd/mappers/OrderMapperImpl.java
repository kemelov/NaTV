package kg.natv.TextAd.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import kg.natv.TextAd.models.DTOs.AdDTO;
import kg.natv.TextAd.models.DTOs.OrderDTO;
import kg.natv.TextAd.models.Order;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T09:26:18+0600",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDTO toDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId( order.getId() );
        orderDTO.setText( order.getText() );
        orderDTO.setEmail( order.getEmail() );
        orderDTO.setFio( order.getFio() );
        orderDTO.setPhone( order.getPhone() );
        orderDTO.setTotalPrice( order.getTotalPrice() );
        orderDTO.setStatus( order.getStatus() );
        orderDTO.setCreatedDate( order.getCreatedDate() );

        return orderDTO;
    }

    @Override
    public Order toEntity(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( orderDTO.getId() );
        order.setText( orderDTO.getText() );
        order.setCreatedDate( orderDTO.getCreatedDate() );
        order.setEmail( orderDTO.getEmail() );
        order.setFio( orderDTO.getFio() );
        order.setPhone( orderDTO.getPhone() );
        order.setTotalPrice( orderDTO.getTotalPrice() );
        order.setStatus( orderDTO.getStatus() );

        return order;
    }

    @Override
    public List<OrderDTO> toDTOList(List<Order> orderList) {
        if ( orderList == null ) {
            return null;
        }

        List<OrderDTO> list = new ArrayList<OrderDTO>( orderList.size() );
        for ( Order order : orderList ) {
            list.add( toDTO( order ) );
        }

        return list;
    }

    @Override
    public List<Order> toEntityList(List<AdDTO> orderDTOList) {
        if ( orderDTOList == null ) {
            return null;
        }

        List<Order> list = new ArrayList<Order>( orderDTOList.size() );
        for ( AdDTO adDTO : orderDTOList ) {
            list.add( adDTOToOrder( adDTO ) );
        }

        return list;
    }

    protected Order adDTOToOrder(AdDTO adDTO) {
        if ( adDTO == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( adDTO.getId() );
        order.setText( adDTO.getText() );

        return order;
    }
}

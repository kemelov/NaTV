package kg.natv.TextAd.mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import kg.natv.TextAd.models.Ad;
import kg.natv.TextAd.models.DTOs.OrderDateDTO;
import kg.natv.TextAd.models.OrderDate;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T09:26:18+0600",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class OrderDateMapperImpl implements OrderDateMapper {

    @Override
    public OrderDateDTO toDTO(OrderDate orderDate) {
        if ( orderDate == null ) {
            return null;
        }

        OrderDateDTO orderDateDTO = new OrderDateDTO();

        orderDateDTO.setAdId( orderDateAdId( orderDate ) );
        orderDateDTO.setId( orderDate.getId() );
        Set<LocalDate> set = orderDate.getDays();
        if ( set != null ) {
            orderDateDTO.setDays( new LinkedHashSet<LocalDate>( set ) );
        }

        return orderDateDTO;
    }

    @Override
    public OrderDate toEntity(OrderDateDTO orderDateDTOS) {
        if ( orderDateDTOS == null ) {
            return null;
        }

        OrderDate orderDate = new OrderDate();

        orderDate.setId( orderDateDTOS.getId() );
        Set<LocalDate> set = orderDateDTOS.getDays();
        if ( set != null ) {
            orderDate.setDays( new LinkedHashSet<LocalDate>( set ) );
        }

        return orderDate;
    }

    @Override
    public List<OrderDateDTO> toDTOList(List<OrderDate> orderDateList) {
        if ( orderDateList == null ) {
            return null;
        }

        List<OrderDateDTO> list = new ArrayList<OrderDateDTO>( orderDateList.size() );
        for ( OrderDate orderDate : orderDateList ) {
            list.add( toDTO( orderDate ) );
        }

        return list;
    }

    @Override
    public List<OrderDate> toEntityList(List<OrderDateDTO> orderDateDTOS) {
        if ( orderDateDTOS == null ) {
            return null;
        }

        List<OrderDate> list = new ArrayList<OrderDate>( orderDateDTOS.size() );
        for ( OrderDateDTO orderDateDTO : orderDateDTOS ) {
            list.add( toEntity( orderDateDTO ) );
        }

        return list;
    }

    private Long orderDateAdId(OrderDate orderDate) {
        if ( orderDate == null ) {
            return null;
        }
        Ad ad = orderDate.getAd();
        if ( ad == null ) {
            return null;
        }
        Long id = ad.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}

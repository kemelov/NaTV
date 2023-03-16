package kg.natv.TextAd.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import kg.natv.TextAd.models.Channel;
import kg.natv.TextAd.models.DTOs.DiscountDTO;
import kg.natv.TextAd.models.Discount;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T09:26:17+0600",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class DiscountMapperImpl implements DiscountMapper {

    @Override
    public DiscountDTO toDTO(Discount discount) {
        if ( discount == null ) {
            return null;
        }

        DiscountDTO discountDTO = new DiscountDTO();

        discountDTO.setChannelId( discountChannelId( discount ) );
        discountDTO.setId( discount.getId() );
        discountDTO.setDiscount( discount.getDiscount() );
        discountDTO.setFromDayCount( discount.getFromDayCount() );
        discountDTO.setActive( discount.isActive() );
        discountDTO.setStartDate( discount.getStartDate() );
        discountDTO.setEndDate( discount.getEndDate() );

        return discountDTO;
    }

    @Override
    public Discount toEntity(DiscountDTO discountDTO) {
        if ( discountDTO == null ) {
            return null;
        }

        Discount discount = new Discount();

        discount.setId( discountDTO.getId() );
        discount.setDiscount( discountDTO.getDiscount() );
        discount.setFromDayCount( discountDTO.getFromDayCount() );
        discount.setActive( discountDTO.isActive() );
        discount.setStartDate( discountDTO.getStartDate() );
        discount.setEndDate( discountDTO.getEndDate() );

        return discount;
    }

    @Override
    public List<DiscountDTO> toDTOList(List<Discount> discountList) {
        if ( discountList == null ) {
            return null;
        }

        List<DiscountDTO> list = new ArrayList<DiscountDTO>( discountList.size() );
        for ( Discount discount : discountList ) {
            list.add( toDTO( discount ) );
        }

        return list;
    }

    @Override
    public List<Discount> toEntityList(List<DiscountDTO> discountDTOList) {
        if ( discountDTOList == null ) {
            return null;
        }

        List<Discount> list = new ArrayList<Discount>( discountDTOList.size() );
        for ( DiscountDTO discountDTO : discountDTOList ) {
            list.add( toEntity( discountDTO ) );
        }

        return list;
    }

    private Long discountChannelId(Discount discount) {
        if ( discount == null ) {
            return null;
        }
        Channel channel = discount.getChannel();
        if ( channel == null ) {
            return null;
        }
        Long id = channel.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}

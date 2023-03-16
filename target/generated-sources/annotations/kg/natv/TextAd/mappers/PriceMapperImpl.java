package kg.natv.TextAd.mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import kg.natv.TextAd.models.Ad;
import kg.natv.TextAd.models.DTOs.ChannelDateDTO;
import kg.natv.TextAd.models.DTOs.OrderDateDTO;
import kg.natv.TextAd.models.DTOs.PriceDTO;
import kg.natv.TextAd.models.OrderDate;
import kg.natv.TextAd.models.Price;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T09:26:18+0600",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class PriceMapperImpl implements PriceMapper {

    @Override
    public PriceDTO toDTO(Price price) {
        if ( price == null ) {
            return null;
        }

        PriceDTO priceDTO = new PriceDTO();

        priceDTO.setPriceId( price.getPriceId() );
        priceDTO.setPrice( price.getPrice() );
        priceDTO.setPriceWithDiscount( price.getPriceWithDiscount() );

        return priceDTO;
    }

    @Override
    public Price toEntity(PriceDTO priceDTO) {
        if ( priceDTO == null ) {
            return null;
        }

        Price price = new Price();

        price.setPriceId( priceDTO.getPriceId() );
        price.setPrice( priceDTO.getPrice() );
        price.setPriceWithDiscount( priceDTO.getPriceWithDiscount() );

        return price;
    }

    @Override
    public List<PriceDTO> toDTOList(List<Price> priceList) {
        if ( priceList == null ) {
            return null;
        }

        List<PriceDTO> list = new ArrayList<PriceDTO>( priceList.size() );
        for ( Price price : priceList ) {
            list.add( toDTO( price ) );
        }

        return list;
    }

    @Override
    public List<Price> toEntityList(List<PriceDTO> priceDTOList) {
        if ( priceDTOList == null ) {
            return null;
        }

        List<Price> list = new ArrayList<Price>( priceDTOList.size() );
        for ( PriceDTO priceDTO : priceDTOList ) {
            list.add( toEntity( priceDTO ) );
        }

        return list;
    }

    @Override
    public PriceDTO toPriceDTO(ChannelDateDTO channelDateDTO) {
        if ( channelDateDTO == null ) {
            return null;
        }

        PriceDTO priceDTO = new PriceDTO();

        priceDTO.setId( channelDateDTO.getId() );
        priceDTO.setChannelId( channelDateDTO.getChannelId() );
        priceDTO.setChannelName( channelDateDTO.getChannelName() );
        priceDTO.setOrderDateDTO( channelDateDTO.getOrderDateDTO() );
        priceDTO.setDaysCount( channelDateDTO.getDaysCount() );
        priceDTO.setSymbolCount( channelDateDTO.getSymbolCount() );
        priceDTO.setStartDate( channelDateDTO.getStartDate() );
        priceDTO.setEndDate( channelDateDTO.getEndDate() );
        priceDTO.setPriceId( channelDateDTO.getPriceId() );
        priceDTO.setPrice( channelDateDTO.getPrice() );
        priceDTO.setPriceWithDiscount( channelDateDTO.getPriceWithDiscount() );
        priceDTO.setOrderDTO( channelDateDTO.getOrderDTO() );

        return priceDTO;
    }

    @Override
    public ChannelDateDTO toChannelDateDTO(PriceDTO priceDTO) {
        if ( priceDTO == null ) {
            return null;
        }

        ChannelDateDTO channelDateDTO = new ChannelDateDTO();

        channelDateDTO.setId( priceDTO.getId() );
        channelDateDTO.setText( priceDTO.getText() );
        channelDateDTO.setChannelId( priceDTO.getChannelId() );
        channelDateDTO.setChannelName( priceDTO.getChannelName() );
        channelDateDTO.setOrderDateDTO( priceDTO.getOrderDateDTO() );
        channelDateDTO.setDaysCount( priceDTO.getDaysCount() );
        channelDateDTO.setSymbolCount( priceDTO.getSymbolCount() );
        channelDateDTO.setStartDate( priceDTO.getStartDate() );
        channelDateDTO.setEndDate( priceDTO.getEndDate() );
        channelDateDTO.setPriceId( priceDTO.getPriceId() );
        channelDateDTO.setPrice( priceDTO.getPrice() );
        channelDateDTO.setPriceWithDiscount( priceDTO.getPriceWithDiscount() );
        channelDateDTO.setOrderDTO( priceDTO.getOrderDTO() );

        return channelDateDTO;
    }

    @Override
    public Ad toAd(PriceDTO priceDTO) {
        if ( priceDTO == null ) {
            return null;
        }

        Ad ad = new Ad();

        ad.setOrderDate( orderDateDTOToOrderDate( priceDTO.getOrderDateDTO() ) );
        ad.setId( priceDTO.getId() );
        ad.setText( priceDTO.getText() );
        ad.setSymbolCount( priceDTO.getSymbolCount() );
        ad.setDaysCount( priceDTO.getDaysCount() );
        ad.setStartDate( priceDTO.getStartDate() );
        ad.setEndDate( priceDTO.getEndDate() );

        return ad;
    }

    protected OrderDate orderDateDTOToOrderDate(OrderDateDTO orderDateDTO) {
        if ( orderDateDTO == null ) {
            return null;
        }

        OrderDate orderDate = new OrderDate();

        Set<LocalDate> set = orderDateDTO.getDays();
        if ( set != null ) {
            orderDate.setDays( new LinkedHashSet<LocalDate>( set ) );
        }
        orderDate.setId( orderDateDTO.getId() );

        return orderDate;
    }
}

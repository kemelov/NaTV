package kg.natv.TextAd.mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import kg.natv.TextAd.models.Ad;
import kg.natv.TextAd.models.Channel;
import kg.natv.TextAd.models.DTOs.AdDTO;
import kg.natv.TextAd.models.DTOs.OrderDateDTO;
import kg.natv.TextAd.models.OrderDate;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T09:26:18+0600",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class AdMapperImpl implements AdMapper {

    @Override
    public AdDTO toDTO(Ad ad) {
        if ( ad == null ) {
            return null;
        }

        AdDTO adDTO = new AdDTO();

        adDTO.setOrderDateDTO( orderDateToOrderDateDTO( ad.getOrderDate() ) );
        adDTO.setChannelId( adChannelId( ad ) );
        adDTO.setChannelName( adChannelName( ad ) );
        adDTO.setId( ad.getId() );
        adDTO.setText( ad.getText() );
        adDTO.setDaysCount( ad.getDaysCount() );
        adDTO.setSymbolCount( ad.getSymbolCount() );
        adDTO.setStartDate( ad.getStartDate() );
        adDTO.setEndDate( ad.getEndDate() );

        return adDTO;
    }

    @Override
    public Ad toEntity(AdDTO adDTO) {
        if ( adDTO == null ) {
            return null;
        }

        Ad ad = new Ad();

        ad.setOrderDate( orderDateDTOToOrderDate( adDTO.getOrderDateDTO() ) );
        ad.setId( adDTO.getId() );
        ad.setText( adDTO.getText() );
        ad.setSymbolCount( adDTO.getSymbolCount() );
        ad.setDaysCount( adDTO.getDaysCount() );
        ad.setStartDate( adDTO.getStartDate() );
        ad.setEndDate( adDTO.getEndDate() );

        return ad;
    }

    @Override
    public List<AdDTO> toDTOList(List<Ad> adList) {
        if ( adList == null ) {
            return null;
        }

        List<AdDTO> list = new ArrayList<AdDTO>( adList.size() );
        for ( Ad ad : adList ) {
            list.add( toDTO( ad ) );
        }

        return list;
    }

    @Override
    public List<Ad> toEntityList(List<AdDTO> adDTOList) {
        if ( adDTOList == null ) {
            return null;
        }

        List<Ad> list = new ArrayList<Ad>( adDTOList.size() );
        for ( AdDTO adDTO : adDTOList ) {
            list.add( toEntity( adDTO ) );
        }

        return list;
    }

    @Override
    public void updateFromDTO(Ad ad, AdDTO adDTO) {
        if ( adDTO == null ) {
            return;
        }

        ad.setId( adDTO.getId() );
        ad.setText( adDTO.getText() );
        ad.setSymbolCount( adDTO.getSymbolCount() );
        ad.setDaysCount( adDTO.getDaysCount() );
        ad.setStartDate( adDTO.getStartDate() );
        ad.setEndDate( adDTO.getEndDate() );
    }

    protected OrderDateDTO orderDateToOrderDateDTO(OrderDate orderDate) {
        if ( orderDate == null ) {
            return null;
        }

        OrderDateDTO orderDateDTO = new OrderDateDTO();

        Set<LocalDate> set = orderDate.getDays();
        if ( set != null ) {
            orderDateDTO.setDays( new LinkedHashSet<LocalDate>( set ) );
        }
        orderDateDTO.setId( orderDate.getId() );

        return orderDateDTO;
    }

    private Long adChannelId(Ad ad) {
        if ( ad == null ) {
            return null;
        }
        Channel channel = ad.getChannel();
        if ( channel == null ) {
            return null;
        }
        Long id = channel.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String adChannelName(Ad ad) {
        if ( ad == null ) {
            return null;
        }
        Channel channel = ad.getChannel();
        if ( channel == null ) {
            return null;
        }
        String name = channel.getName();
        if ( name == null ) {
            return null;
        }
        return name;
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

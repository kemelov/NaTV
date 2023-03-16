package kg.natv.TextAd.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import kg.natv.TextAd.models.Channel;
import kg.natv.TextAd.models.DTOs.ChannelDTO;
import kg.natv.TextAd.models.DTOs.DiscountDTO;
import kg.natv.TextAd.models.Discount;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T09:26:18+0600",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class ChannelMapperImpl implements ChannelMapper {

    @Override
    public ChannelDTO toDTO(Channel channel) {
        if ( channel == null ) {
            return null;
        }

        ChannelDTO channelDTO = new ChannelDTO();

        channelDTO.setId( channel.getId() );
        channelDTO.setName( channel.getName() );
        channelDTO.setActive( channel.isActive() );
        channelDTO.setLogo( channel.getLogo() );
        channelDTO.setPricePerLetter( channel.getPricePerLetter() );
        channelDTO.setDiscounts( discountListToDiscountDTOList( channel.getDiscounts() ) );

        return channelDTO;
    }

    @Override
    public Channel toEntity(ChannelDTO channelDTO) {
        if ( channelDTO == null ) {
            return null;
        }

        Channel channel = new Channel();

        channel.setId( channelDTO.getId() );
        channel.setName( channelDTO.getName() );
        channel.setActive( channelDTO.isActive() );
        channel.setLogo( channelDTO.getLogo() );
        channel.setPricePerLetter( channelDTO.getPricePerLetter() );
        channel.setDiscounts( discountDTOListToDiscountList( channelDTO.getDiscounts() ) );

        return channel;
    }

    @Override
    public List<ChannelDTO> toDTOList(List<Channel> channelList) {
        if ( channelList == null ) {
            return null;
        }

        List<ChannelDTO> list = new ArrayList<ChannelDTO>( channelList.size() );
        for ( Channel channel : channelList ) {
            list.add( toDTO( channel ) );
        }

        return list;
    }

    @Override
    public List<Channel> toEntityList(List<ChannelDTO> channelDTOList) {
        if ( channelDTOList == null ) {
            return null;
        }

        List<Channel> list = new ArrayList<Channel>( channelDTOList.size() );
        for ( ChannelDTO channelDTO : channelDTOList ) {
            list.add( toEntity( channelDTO ) );
        }

        return list;
    }

    @Override
    public Channel updateFromDTO(Channel channel, ChannelDTO channelDTO) {
        if ( channelDTO == null ) {
            return channel;
        }

        channel.setId( channelDTO.getId() );
        channel.setName( channelDTO.getName() );
        channel.setActive( channelDTO.isActive() );
        channel.setLogo( channelDTO.getLogo() );
        channel.setPricePerLetter( channelDTO.getPricePerLetter() );
        if ( channel.getDiscounts() != null ) {
            List<Discount> list = discountDTOListToDiscountList( channelDTO.getDiscounts() );
            if ( list != null ) {
                channel.getDiscounts().clear();
                channel.getDiscounts().addAll( list );
            }
            else {
                channel.setDiscounts( null );
            }
        }
        else {
            List<Discount> list = discountDTOListToDiscountList( channelDTO.getDiscounts() );
            if ( list != null ) {
                channel.setDiscounts( list );
            }
        }

        return channel;
    }

    protected DiscountDTO discountToDiscountDTO(Discount discount) {
        if ( discount == null ) {
            return null;
        }

        DiscountDTO discountDTO = new DiscountDTO();

        discountDTO.setId( discount.getId() );
        discountDTO.setDiscount( discount.getDiscount() );
        discountDTO.setFromDayCount( discount.getFromDayCount() );
        discountDTO.setActive( discount.isActive() );
        discountDTO.setStartDate( discount.getStartDate() );
        discountDTO.setEndDate( discount.getEndDate() );

        return discountDTO;
    }

    protected List<DiscountDTO> discountListToDiscountDTOList(List<Discount> list) {
        if ( list == null ) {
            return null;
        }

        List<DiscountDTO> list1 = new ArrayList<DiscountDTO>( list.size() );
        for ( Discount discount : list ) {
            list1.add( discountToDiscountDTO( discount ) );
        }

        return list1;
    }

    protected Discount discountDTOToDiscount(DiscountDTO discountDTO) {
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

    protected List<Discount> discountDTOListToDiscountList(List<DiscountDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Discount> list1 = new ArrayList<Discount>( list.size() );
        for ( DiscountDTO discountDTO : list ) {
            list1.add( discountDTOToDiscount( discountDTO ) );
        }

        return list1;
    }
}

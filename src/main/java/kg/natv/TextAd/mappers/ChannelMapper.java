package kg.natv.TextAd.mappers;

import kg.natv.TextAd.models.Ad;
import kg.natv.TextAd.models.Channel;
import kg.natv.TextAd.models.DTOs.AdDTO;
import kg.natv.TextAd.models.DTOs.ChannelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ChannelMapper {
    ChannelMapper INSTANCE = Mappers.getMapper(ChannelMapper.class);
    ChannelDTO ToDTO(Channel channel);
    Channel toEntity(ChannelDTO channelDTO);
    List<ChannelDTO> toDTOList(List<Channel>channelList);
    List<Channel>toEntityList(List<ChannelDTO>channelDTOList);
}

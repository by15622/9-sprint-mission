package service.jcf;

import entity.Channel;
import service.ChannelService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JCFChannelService implements ChannelService {
    private final List<Channel> channels = new ArrayList<>();

    @Override
    public Channel create(String displayName) {
        Channel channel = new Channel(displayName);
        channels.add(channel);
        return channel;

        //channels 리스트에 새로만든 신규 채널을 담아둠
    }

    @Override
    public Channel find(UUID id) {
        return channels.stream()
                .filter(channel -> channel.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Channel> findAll() {
        return new ArrayList<>(channels);
    }
    /* ArrayList쓴 이유 외부에서 함부로 건드리지 못하게 보호하고  외부 리스트 항목을 추가하거나 삭제해도
    관리하는 원본 list에 영향을 주지 않을려고 했다
    findall메서드의
    */


    @Override
    public Channel update(UUID id, String displayName) {
        Channel channel = find(id);
        if (channel != null) {
            channel.setDisplayname(displayName);                   //기존에는 이것만 있음
            channel.setUpdatedAt(System.currentTimeMillis());     //팀장님이 권유해주신 시간추가
        }
        return channel;
    }

    @Override
    public boolean delete(UUID id) {
        return channels.removeIf(channel -> channel.getId().equals(id));
    }
}
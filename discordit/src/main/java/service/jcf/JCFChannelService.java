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

    @Override
    public Channel update(UUID id, String displayName) {
        Channel channel = find(id);
        if (channel != null) {
            channel.setDisplayname(displayName);
        }
        return channel;
    }

    @Override
    public boolean delete(UUID id) {
        return channels.removeIf(channel -> channel.getId().equals(id));
    }
}
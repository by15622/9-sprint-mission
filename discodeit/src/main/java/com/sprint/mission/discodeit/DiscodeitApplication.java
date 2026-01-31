package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.dto.ChannelCreateRequest;
import com.sprint.mission.discodeit.dto.MessageCreateRequest;
import com.sprint.mission.discodeit.dto.UserCreateRequest;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.basic.BasicChannelService;
import com.sprint.mission.discodeit.service.basic.BasicMessageService;
import com.sprint.mission.discodeit.service.basic.BasicUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DiscodeitApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DiscodeitApplication.class, args);

		UserService userService = context.getBean(UserService.class);
		ChannelService channelService = context.getBean(ChannelService.class);
		MessageService messageService = context.getBean(MessageService.class);

		User user = setupUser(userService);
		Channel channel = setupChannel(channelService);
		messageCreateTest(messageService, channel, user);

	}
	private static User setupUser(UserService userService) {
		UserCreateRequest request = new UserCreateRequest(
				"woody",
				"woody@codeit.com",
				"woody1234",
				null
		);
		return userService.create(request);
	}

	private static Channel setupChannel(ChannelService channelService) {
		ChannelCreateRequest request = new ChannelCreateRequest(
				"공지",
				"공지 채널입니다.",
				null
		);
		return channelService.createPublic(request);
	}

	private static void messageCreateTest(MessageService messageService, Channel channel, User author) {
		MessageCreateRequest request = new MessageCreateRequest(
				"안녕하세요.",
				channel.getId(),
				author.getId(),
				null
		);
		Message message = messageService.create(request);
		System.out.println("메시지 생성: " + message.getId());
	}
}
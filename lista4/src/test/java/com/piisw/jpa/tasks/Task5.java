package com.piisw.jpa.tasks;

import com.piisw.jpa.entities.Server;
import com.piisw.jpa.repositories.ServerRepository;
import com.piisw.jpa.services.ServerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class Task5 {

    @Mock
    private ServerRepository serverRepository;

    @InjectMocks
    private ServerService serverService;

    @Test
    void shouldReturnMockServer() {
        // given
        String serverName = "dummyName";
        String mockServerName = "Alex";
        String mockServerIp = "noIp";
        Server dummyServer = new Server(mockServerName, mockServerIp);

        when(serverRepository.findByName(serverName)).thenReturn(Optional.of(dummyServer));

        // when
        Optional<Server> result = serverService.findByName(serverName);

        // then
        assertThat(result.isPresent(), Matchers.is(true));
        assertThat(result.get().getName(), Matchers.is(mockServerName));
        assertThat(result.get().getIp(), Matchers.is(mockServerIp));
    }

}

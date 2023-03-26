package me.dserrano.blockchain.application.handler;

import me.dserrano.blockchain.domain.node.ports.primary.GetSelfNodeService;
import me.dserrano.blockchain.domain.node.ports.primary.PublishNodeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import static me.dserrano.blockchain.application.model.NodeMother.node;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = NotifyChangesHandler.class)
class NotifyChangesHandlerTest {
    @MockBean
    private GetSelfNodeService nodeQueryService;
    @MockBean
    private PublishNodeService publishNodeService;
    @MockBean
    private Supplier<LocalDateTime> nowSupplier;
    @Autowired
    private NotifyChangesHandler notifyChangesHandler;

    @Test
    @DisplayName("Test changes are published")
    void testChangesArePublished() {
        var dateTime = LocalDateTime.of(2022, 12, 31, 0, 0, 0);
        when(nodeQueryService.getSelfNode()).thenReturn(node);
        when(nowSupplier.get()).thenReturn(dateTime);

        notifyChangesHandler.notifyChanges();
        verify(publishNodeService).publish(node, dateTime);
    }
}

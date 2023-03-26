package me.dserrano.blockchain.application.handler;

import me.dserrano.blockchain.domain.node.ports.primary.NodeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = NotifyChangesHandler.class)
class NotifyChangesHandlerTest {
    @MockBean
    private NodeService nodeService;
    @MockBean
    private Supplier<LocalDateTime> nowSupplier;
    @Autowired
    private NotifyChangesHandler notifyChangesHandler;

    @Test
    @DisplayName("Test changes are published")
    void testChangesArePublished() {
        var dateTime = LocalDateTime.of(2022, 12, 31, 0, 0, 0);
        when(nowSupplier.get()).thenReturn(dateTime);

        notifyChangesHandler.notifyChanges();
        verify(nodeService).publishSelfNode(dateTime);
    }
}

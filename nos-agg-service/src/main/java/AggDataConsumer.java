import model.AggData;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import repository.AggDataRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class AggDataConsumer {
    @Inject
    private AggDataRepository repository;

    @Incoming("data")
    @Transactional
    public CompletionStage<Void> consume(Message<AggData> dataMessage) {
        repository.persistAndFlush(dataMessage.getPayload());
        return dataMessage.ack();
    }
}

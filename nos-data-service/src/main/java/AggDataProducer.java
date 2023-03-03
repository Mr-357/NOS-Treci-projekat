import io.smallrye.mutiny.Multi;
import model.AggData;
import model.Data;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import repo.DataRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class AggDataProducer {
    private Date startTime = Date.from(Instant.now().minus(5, ChronoUnit.MINUTES));
    private Date endTime = Date.from(Instant.now());
    @Inject
    private DataRepository repository;

    @Outgoing("data")
    @ActivateRequestContext
    public Multi<Message<AggData>> generate() {
        // Build an infinite stream of random prices
        // It emits a price every second
        return Multi.createFrom().ticks().every(Duration.ofMinutes(1))
                .map(x -> {
                    List<Data> data = repository.getRange(startTime, endTime);
                    AggData toSend = new AggData();
                    if(data.size() > 0){
                        toSend.setData((data.stream().map(Data::getData).reduce(0, Integer::sum) / data.size()));
                    } else {
                        toSend.setData(Integer.MIN_VALUE);
                    }

                    startTime = Date.from(Instant.now());
                    endTime = Date.from(startTime.toInstant().plus(5, ChronoUnit.MINUTES));
                    return Message.of(toSend);
                });
    }

}
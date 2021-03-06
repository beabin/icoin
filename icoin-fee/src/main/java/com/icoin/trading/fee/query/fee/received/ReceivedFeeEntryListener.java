package com.icoin.trading.fee.query.fee.received;

import com.icoin.trading.api.fee.domain.fee.FeeStatus;
import com.icoin.trading.api.fee.events.fee.received.ReceivedFeeCancelledEvent;
import com.icoin.trading.api.fee.events.fee.received.ReceivedFeeConfirmedEvent;
import com.icoin.trading.api.fee.events.fee.received.ReceivedFeeCreatedEvent;
import com.icoin.trading.api.fee.events.fee.received.ReceivedFeeOffsetedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: liougehooa
 * Date: 14-4-13
 * Time: PM5:31
 * To change this template use File | Settings | File Templates.
 */
public class ReceivedFeeEntryListener {
    private static Logger logger = LoggerFactory.getLogger(ReceivedFeeEntryListener.class);
    private ReceivedFeeEntryQueryRepository repository;

    @EventHandler
    public void handleCreated(ReceivedFeeCreatedEvent event) {
        ReceivedFeeEntry entry = new ReceivedFeeEntry();
        entry.copy(event);
        repository.save(entry);
    }

    @EventHandler
    public void handleConfirmed(ReceivedFeeConfirmedEvent event) {
        final ReceivedFeeEntry entry = repository.findOne(event.getFeeId().toString());

        if (entry == null) {
            logger.error("Entry not found with id {}", event.getFeeId());
        }

        entry.setConfirmedDate(event.getConfirmedDate());
        entry.setFeeStatus(FeeStatus.CONFIRMED);
        entry.setAmount(event.getAmount());
        repository.save(entry);
    }

    @EventHandler
    public void handleOffseted(ReceivedFeeOffsetedEvent event) {
        final ReceivedFeeEntry entry = repository.findOne(event.getFeeId().toString());

        if (entry == null) {
            logger.error("Entry not found with id {}", event.getFeeId());
        }

        entry.setOffsetDate(event.getOffsetedDate());
        entry.setOffseted(true);
        entry.setOffsetId(event.getOffsetId().toString());
        repository.save(entry);
    }

    @EventHandler
    public void handleCancelled(ReceivedFeeCancelledEvent event) {
        final ReceivedFeeEntry entry = repository.findOne(event.getFeeId().toString());

        if (entry == null) {
            logger.error("Entry not found with id {}", event.getFeeId());
        }

        entry.setCancelledDate(event.getCancelledDate());
        entry.setFeeStatus(FeeStatus.CANCELLED);
        entry.setCancelledReason(event.getCancelledReason());
        repository.save(entry);
    }

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public void setRepository(ReceivedFeeEntryQueryRepository repository) {
        this.repository = repository;
    }
}

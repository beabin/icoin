package com.icoin.trading.fee.application.command;

import com.icoin.trading.api.fee.command.received.CancelReceivedFeeCommand;
import com.icoin.trading.api.fee.command.received.ConfirmReceivedFeeCommand;
import com.icoin.trading.api.fee.command.received.CreateReceivedFeeCommand;
import com.icoin.trading.api.fee.command.received.OffsetReceivedFeeCommand;
import com.icoin.trading.fee.domain.paid.PaidFee;
import com.icoin.trading.fee.domain.received.ReceivedFee;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: liougehooa
 * Date: 14-3-19
 * Time: PM9:02
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ReceivedPaidFeeCommandHandler {
    private Repository<ReceivedFee> receivedFeeRepository;
    private Repository<PaidFee> paidFeeRepository;

    @CommandHandler
    public void handleCreateReceived(CreateReceivedFeeCommand command) {
//         receivedEntryRepository.save(toReceivedEntry(command));

        receivedFeeRepository.add(new ReceivedFee(
                command.getFeeId(),
                command.getFeeStatus(),
                command.getAmount(),
                command.getFeeType(),
                command.getDueDate(),
                command.getCreatedTime(),
                command.getUserAccountId(),
                command.getBusinessType(),
                command.getBusinessReferenceId(),
                command.getReceivedSource()
        ));

    }

    @CommandHandler
    public void handleConfirmReceived(ConfirmReceivedFeeCommand command) {
        ReceivedFee fee = receivedFeeRepository.load(command.getFeeId());

        fee.confirm(command.getConfirmedDate());
    }

    @CommandHandler
    public void handleOffsetReceived(OffsetReceivedFeeCommand command) {
        ReceivedFee fee = receivedFeeRepository.load(command.getFeeId());

        fee.offset(command.getOffsetedDate());
    }


    @CommandHandler
    public void handleCancelReceived(CancelReceivedFeeCommand command) {
        ReceivedFee fee = receivedFeeRepository.load(command.getFeeId());

        fee.cancel(command.getCancelledReason(), command.getCancelledDate());
    }


    @Resource(name = "receivedFeeRepository")
    public void setReceivedFeeRepository(Repository<ReceivedFee> receivedFeeRepository) {
        this.receivedFeeRepository = receivedFeeRepository;
    }

    @Resource(name = "paidFeeRepository")
    public void setPaidFeeRepository(Repository<PaidFee> paidFeeRepository) {
        this.paidFeeRepository = paidFeeRepository;
    }
}
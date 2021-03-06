/*
 * Copyright (c) 2010-2012. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.icoin.trading.tradeengine.query.transaction;

import com.icoin.trading.api.tradeengine.domain.TransactionType;
import com.icoin.trading.api.tradeengine.events.transaction.AbstractTransactionExecutedEvent;
import com.icoin.trading.api.tradeengine.events.transaction.AbstractTransactionPartiallyExecutedEvent;
import com.icoin.trading.api.tradeengine.events.transaction.AbstractTransactionStartedEvent;
import com.icoin.trading.api.tradeengine.events.transaction.BuyTransactionCancelledEvent;
import com.icoin.trading.api.tradeengine.events.transaction.BuyTransactionConfirmedEvent;
import com.icoin.trading.api.tradeengine.events.transaction.BuyTransactionExecutedEvent;
import com.icoin.trading.api.tradeengine.events.transaction.BuyTransactionPartiallyExecutedEvent;
import com.icoin.trading.api.tradeengine.events.transaction.BuyTransactionStartedEvent;
import com.icoin.trading.api.tradeengine.events.transaction.SellTransactionCancelledEvent;
import com.icoin.trading.api.tradeengine.events.transaction.SellTransactionConfirmedEvent;
import com.icoin.trading.api.tradeengine.events.transaction.SellTransactionExecutedEvent;
import com.icoin.trading.api.tradeengine.events.transaction.SellTransactionPartiallyExecutedEvent;
import com.icoin.trading.api.tradeengine.events.transaction.SellTransactionStartedEvent;
import com.icoin.trading.tradeengine.query.order.repositories.OrderBookQueryRepository;
import com.icoin.trading.tradeengine.query.transaction.repositories.TransactionQueryRepository;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.homhon.util.TimeUtils.currentTime;

/**
 * @author Jettro Coenradie
 */
@Component
public class TransactionEventListener {

    private OrderBookQueryRepository orderBookQueryRepository;
    private TransactionQueryRepository transactionQueryRepository;

    @EventHandler
    public void handleEvent(BuyTransactionStartedEvent event) {
        startTransaction(event, TransactionType.BUY);
    }

    @EventHandler
    public void handleEvent(SellTransactionStartedEvent event) {
        startTransaction(event, TransactionType.SELL);
    }

    @EventHandler
    public void handleEvent(BuyTransactionCancelledEvent event) {
        changeStateOfTransaction(event.getTransactionIdentifier().toString(), TransactionState.CANCELLED);
    }

    @EventHandler
    public void handleEvent(SellTransactionCancelledEvent event) {
        changeStateOfTransaction(event.getTransactionIdentifier().toString(), TransactionState.CANCELLED);
    }

    @EventHandler
    public void handleEvent(BuyTransactionConfirmedEvent event) {
        changeStateOfTransaction(event.getTransactionIdentifier().toString(), TransactionState.CONFIRMED);
    }

    @EventHandler
    public void handleEvent(SellTransactionConfirmedEvent event) {
        changeStateOfTransaction(event.getTransactionIdentifier().toString(), TransactionState.CONFIRMED);
    }

    @EventHandler
    public void handleEvent(BuyTransactionExecutedEvent event) {
        executeTransaction(event);
    }

    @EventHandler
    public void handleEvent(SellTransactionExecutedEvent event) {
        executeTransaction(event);
    }

    @EventHandler
    public void handleEvent(BuyTransactionPartiallyExecutedEvent event) {
        partiallyExecuteTransaction(event);
    }

    @EventHandler
    public void handleEvent(SellTransactionPartiallyExecutedEvent event) {
        partiallyExecuteTransaction(event);
    }

    private void partiallyExecuteTransaction(AbstractTransactionPartiallyExecutedEvent event) {
        TransactionEntry transactionEntry = transactionQueryRepository.findOne(event.getTransactionIdentifier()
                .toString());

        transactionEntry.setState(TransactionState.PARTIALLY_EXECUTED);
        transactionEntry.addAmountOfExecutedItem(event.getAmountOfExecutedItem());
        transactionEntry.addExecutedMoney(event.getExecutedMoney());
        transactionEntry.addCommission(event.getCommission());
        transactionQueryRepository.save(transactionEntry);
    }

    private void executeTransaction(AbstractTransactionExecutedEvent event) {
        TransactionEntry transactionEntry = transactionQueryRepository.findOne(event.getTransactionIdentifier()
                .toString());

        transactionEntry.setState(TransactionState.EXECUTED);
        transactionEntry.addAmountOfExecutedItem(event.getAmountOfItem());
        transactionEntry.addExecutedMoney(event.getExecutedMoney());
        transactionEntry.addCommission(event.getCommission());
        transactionQueryRepository.save(transactionEntry);
    }

    private void changeStateOfTransaction(String primaryKey, TransactionState newState) {
        TransactionEntry transactionEntry = transactionQueryRepository.findOne(primaryKey);
        transactionEntry.setState(newState);
        transactionQueryRepository.save(transactionEntry);
    }

    private void startTransaction(AbstractTransactionStartedEvent event, TransactionType type) {
//        OrderBookEntry orderBookEntry = orderBookQueryRepository.findOne(event.getOrderBookIdentifier().toString());

        TransactionEntry entry = new TransactionEntry();
        entry.setAmountOfItem(event.getTotalItem());
        entry.setCoinId(event.getCoinId() == null ? "" : event.getCoinId().toString());
        entry.setTotalCommission(event.getTotalCommission());
        entry.setTotalMoney(event.getTotalMoney());
        entry.setPrimaryKey(event.getTransactionIdentifier().toString());
        entry.setOrderBookIdentifier(event.getOrderBookIdentifier().toString());
        entry.setPortfolioIdentifier(event.getPortfolioIdentifier().toString());
        entry.setState(TransactionState.STARTED);
        entry.setType(type);
        entry.setCreated(currentTime());
//        entry.setCoinName(orderBookEntry.getCoinName());

        transactionQueryRepository.save(entry);
    }

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public void setOrderBookQueryRepository(OrderBookQueryRepository orderBookQueryRepository) {
        this.orderBookQueryRepository = orderBookQueryRepository;
    }

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public void setTransactionQueryRepository(TransactionQueryRepository transactionQueryRepository) {
        this.transactionQueryRepository = transactionQueryRepository;
    }
}

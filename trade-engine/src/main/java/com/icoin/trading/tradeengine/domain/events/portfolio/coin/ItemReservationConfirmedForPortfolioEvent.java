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

package com.icoin.trading.tradeengine.domain.events.portfolio.coin;

import com.homhon.base.domain.event.EventSupport;
import com.icoin.trading.tradeengine.domain.model.coin.CoinId;
import com.icoin.trading.tradeengine.domain.model.portfolio.PortfolioId;
import com.icoin.trading.tradeengine.domain.model.transaction.TransactionId;
import org.joda.money.BigMoney;

import java.util.Date;

/**
 * @author Jettro Coenradie
 */
public class ItemReservationConfirmedForPortfolioEvent extends EventSupport<ItemReservationConfirmedForPortfolioEvent> {
    private PortfolioId portfolioIdentifier;
    private CoinId coinId;
    private TransactionId transactionIdentifier;
    private BigMoney amount;
    private BigMoney commission;
    private Date time;

    public ItemReservationConfirmedForPortfolioEvent(PortfolioId portfolioIdentifier,
                                                     CoinId coinId,
                                                     TransactionId transactionIdentifier,
                                                     BigMoney amount,
                                                     BigMoney commission,
                                                     Date time) {
        this.portfolioIdentifier = portfolioIdentifier;
        this.coinId = coinId;
        this.transactionIdentifier = transactionIdentifier;
        this.amount = amount;
        this.commission = commission;
        this.time = time;
    }

    public BigMoney getCommission() {
        return commission;
    }

    public BigMoney getAmount() {
        return amount;
    }

    public CoinId getCoinId() {
        return coinId;
    }

    public PortfolioId getPortfolioIdentifier() {
        return portfolioIdentifier;
    }

    public TransactionId getTransactionIdentifier() {
        return transactionIdentifier;
    }

    public Date getTime() {
        return time;
    }
}

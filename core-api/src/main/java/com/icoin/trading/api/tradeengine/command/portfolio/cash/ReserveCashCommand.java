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

package com.icoin.trading.api.tradeengine.command.portfolio.cash;

import com.homhon.base.command.CommandSupport;
import com.icoin.trading.api.tradeengine.domain.PortfolioId;
import com.icoin.trading.api.tradeengine.domain.TransactionId;
import org.joda.money.BigMoney;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Jettro Coenradie
 */
public class ReserveCashCommand extends CommandSupport<ReserveCashCommand> {

    private PortfolioId portfolioIdentifier;
    private TransactionId transactionIdentifier;
    //    @DecimalMin("0.0000001")
    private BigMoney totalMoney;
    private BigMoney totalCommission;
    @NotNull
    private Date time;

    public ReserveCashCommand(PortfolioId portfolioIdentifier,
                              TransactionId transactionIdentifier,
                              BigMoney totalMoney,
                              BigMoney totalCommission,
                              Date time) {
        this.portfolioIdentifier = portfolioIdentifier;
        this.transactionIdentifier = transactionIdentifier;
        this.totalMoney = totalMoney;
        this.totalCommission = totalCommission;
        this.time = time;
    }

    public BigMoney getTotalCommission() {
        return totalCommission;
    }

    public BigMoney getTotalMoney() {
        return totalMoney;
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

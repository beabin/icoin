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

package com.icoin.trading.tradeengine.application.command.portfolio.coin;


import com.icoin.trading.tradeengine.domain.model.order.OrderBookId;
import com.icoin.trading.tradeengine.domain.model.portfolio.PortfolioId;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

/**
 * Try to add new items for a specific OrderBook to the portfolio.
 *
 * @author Jettro Coenradie
 */
public class AddAmountToPortfolioCommand {

    private PortfolioId portfolioIdentifier;
    private OrderBookId orderBookIdentifier;
    @DecimalMin("0.00000001")
    private BigDecimal amountOfItemsToAdd;

    /**
     * Create a new command.
     *
     * @param portfolioIdentifier Identifier of the Portfolio to add items to
     * @param orderBookIdentifier Identifier of the OrderBook to add items for
     * @param amountOfItemsToAdd  AMount of items to add
     */
    public AddAmountToPortfolioCommand(PortfolioId portfolioIdentifier, OrderBookId orderBookIdentifier,
                                       BigDecimal amountOfItemsToAdd) {
        this.portfolioIdentifier = portfolioIdentifier;
        this.orderBookIdentifier = orderBookIdentifier;
        this.amountOfItemsToAdd = amountOfItemsToAdd;
    }

    public BigDecimal getAmountOfItemsToAdd() {
        return amountOfItemsToAdd;
    }

    public OrderBookId getOrderBookIdentifier() {
        return orderBookIdentifier;
    }

    public PortfolioId getPortfolioIdentifier() {
        return portfolioIdentifier;
    }

    @Override
    public String toString() {
        return "AddAmountToPortfolioCommand{" +
                "amountOfItemsToAdd=" + amountOfItemsToAdd +
                ", portfolioIdentifier=" + portfolioIdentifier +
                ", orderBookIdentifier=" + orderBookIdentifier +
                '}';
    }
}
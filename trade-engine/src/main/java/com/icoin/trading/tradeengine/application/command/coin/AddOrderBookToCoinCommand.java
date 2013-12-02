/*
 * Copyright (c) 2012. Axon Framework
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

package com.icoin.trading.tradeengine.application.command.coin;

import com.icoin.trading.tradeengine.domain.model.coin.CoinExchangePair;
import com.icoin.trading.tradeengine.domain.model.coin.CoinId;
import com.icoin.trading.tradeengine.domain.model.order.OrderBookId;

/**
 * <p>Create the relation between a coin and an OrderBook</p>
 *
 * @author Jettro Coenradie
 */
public class AddOrderBookToCoinCommand {
    private CoinId coinId;
    private OrderBookId orderBookId;
    private CoinExchangePair coinExchangePair;

    public AddOrderBookToCoinCommand(CoinId coinId, OrderBookId orderBookId, CoinExchangePair coinExchangePair) {
        this.coinId = coinId;
        this.orderBookId = orderBookId;
        this.coinExchangePair = coinExchangePair;
    }

    public CoinId getCoinId() {
        return coinId;
    }

    public OrderBookId getOrderBookId() {
        return orderBookId;
    }

    public CoinExchangePair getCoinExchangePair() {
        return coinExchangePair;
    }
}
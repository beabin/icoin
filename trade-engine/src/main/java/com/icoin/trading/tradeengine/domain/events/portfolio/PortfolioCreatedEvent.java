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

package com.icoin.trading.tradeengine.domain.events.portfolio;

import com.homhon.base.domain.event.EventSupport;
import com.icoin.trading.tradeengine.domain.model.portfolio.PortfolioId;
import com.icoin.trading.users.domain.model.user.UserId;

import java.util.Date;

/**
 * @author Jettro Coenradie
 */
public class PortfolioCreatedEvent extends EventSupport<PortfolioCreatedEvent> {

    private PortfolioId portfolioId;
    private UserId userId;
    private Date time;

    public PortfolioCreatedEvent(PortfolioId portfolioId, UserId userId, Date time) {
        this.portfolioId = portfolioId;
        this.userId = userId;
        this.time = time;
    }

    public UserId getUserId() {
        return this.userId;
    }

    public PortfolioId getPortfolioId() {
        return this.portfolioId;
    }

    public Date getTime() {
        return time;
    }
}

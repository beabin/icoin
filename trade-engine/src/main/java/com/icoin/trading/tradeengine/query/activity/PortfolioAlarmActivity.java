package com.icoin.trading.tradeengine.query.activity;

import com.homhon.mongo.domainsupport.modelsupport.entity.VersionedEntitySupport;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: jihual
 * Date: 2/26/14
 * Time: 3:30 PM
 * To change this template use File | Settings | File Templates.
 */
@CompoundIndexes({
        @CompoundIndex(name = "executedExceptionActivity_time_checked", def = "{'tradeTime': 1, 'checked': -1}", unique = true)
})
public class PortfolioAlarmActivity extends VersionedEntitySupport<PortfolioAlarmActivity, String, Long> {
    @Indexed
    private String userId;
    @Indexed
    private String username;
    @Indexed
    private String portfolioId;

    private PortfolioAlarmType type;

    private boolean checked;

    private Date time;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public PortfolioAlarmType getType() {
        return type;
    }

    public void setType(PortfolioAlarmType type) {
        this.type = type;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
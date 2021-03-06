package com.icoin.trading.tradeengine.domain.model.order;

import org.joda.money.BigMoney;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: liougehooa
 * Date: 13-12-3
 * Time: AM12:31
 * To change this template use File | Settings | File Templates.
 */
public class OrderComparator<T extends AbstractOrder> implements Comparator<T>, Serializable {

    public int compare(T o1, T o2) {
        // copied from Java 7 Long.compareTo to support java 6
        BigMoney x = o1.getItemPrice();
        BigMoney y = o2.getItemPrice();
        int result = x.compareTo(y);

        result = result == 0 ? o1.getPlaceDate().compareTo(o2.getPlaceDate()) : result;

        //biggest amount first for the largest trade volume
        return result == 0 ? (-1 * o1.getItemRemaining().compareTo(o2.getItemRemaining())) : result;
    }
}
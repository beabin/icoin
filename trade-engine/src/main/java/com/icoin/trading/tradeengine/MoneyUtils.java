package com.icoin.trading.tradeengine;

import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.homhon.util.Asserts.hasLength;
import static com.homhon.util.Asserts.isTrue;
import static com.homhon.util.Asserts.notNull;

/**
 * Created with IntelliJ IDEA.
 * User: liougehooa
 * Date: 13-12-15
 * Time: PM4:50
 * To change this template use File | Settings | File Templates.
 */
public abstract class MoneyUtils {

    public static BigMoney convertToBigMoney(String ccy, long amount) {
        return convertToBigMoney(ccy, amount, RoundingMode.HALF_EVEN);
    }

    public static BigMoney convertToBigMoney(String ccy, double amount, RoundingMode roundingMode) {
        hasLength(ccy);
        final CurrencyUnit currency = CurrencyUnit.of(ccy);

        return convertToBigMoney(currency, amount, roundingMode);
    }

    public static BigMoney convertToBigMoney(CurrencyUnit currency, double amount) {
        return convertToBigMoney(currency, amount, RoundingMode.HALF_EVEN);
    }

    public static BigMoney convertToBigMoney(CurrencyUnit currency, double amount, RoundingMode roundingMode) {
        notNull(currency);
        notNull(roundingMode);

        if (currency.getDecimalPlaces() < 0) {
            return BigMoney.of(currency, amount);
        }

        final long multiplier = getMultiplier(currency);
        return BigMoney.ofScale(currency, BigDecimal.valueOf(amount), currency.getDecimalPlaces()).dividedBy(multiplier, roundingMode);
    }

    public static long getMultiplier(String currency) {
        return getMultiplier(CurrencyUnit.of(currency));
    }

    public static long getMultiplier(CurrencyUnit currency) {
        final int decimalPlaces = currency.getDecimalPlaces();
        if (decimalPlaces < 0) {
            throw new UnsupportedOperationException("not support for ccy " + currency);
        }
        return (long) Math.pow(10, decimalPlaces);
    }

    public static long convertToLong(BigMoney money) {
        notNull(money.getAmount());
        notNull(money.getCurrencyUnit());

        if (money.getCurrencyUnit().getDecimalPlaces() < 0) {
            return money.getAmountMajorLong();
        }

        return convertToLong(money, RoundingMode.HALF_EVEN);
    }

    public static long convertToLong(BigMoney money, RoundingMode roundingMode) {
        final long multiplier = getMultiplier(money.getCurrencyUnit());
        final BigDecimal amount = money.multiplyRetainScale(multiplier, roundingMode).getAmount();
        return amount.setScale(0, RoundingMode.HALF_EVEN).longValue();

    }

    public static Money convertTo(BigMoney amount, BigMoney ratePrice) {
        notNull(amount);
        notNull(ratePrice);
        isTrue(amount.getAmount().compareTo(BigDecimal.ZERO) > 0, "Amount should be greater than zero");
        isTrue(ratePrice.getAmount().compareTo(BigDecimal.ZERO) > 0, "Rate should be greater than zero");

        BigMoney money =
                amount.convertedTo(
                        ratePrice.getCurrencyUnit(),
                        ratePrice.getAmount());

        return money.toMoney(RoundingMode.HALF_EVEN);
    }
}

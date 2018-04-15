package pl.funds;

import java.math.BigDecimal;

import lombok.Value;

@Value
public class Money {

    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    private BigDecimal amount;

    public static Money of(final int amount) {
        return new Money(amount);
    }

    public static Money of(final BigDecimal amount) {
        return new Money(amount);
    }

    Money(final BigDecimal amount) {
        this.amount = amount;
    }

    Money(final int amount) {
        this(new BigDecimal(amount));
    }

    public Money subtract(final Money money) {
        return new Money(this.amount.subtract(money.amount));
    }

    public Money percentage(final int percent) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(percent))
                .divide(ONE_HUNDRED));
    }

    @Override
    public String toString() {
        return String.valueOf(amount) + "PLN";
    }
}

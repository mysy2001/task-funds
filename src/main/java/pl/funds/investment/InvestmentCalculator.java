package pl.funds.investment;

import java.math.BigDecimal;

import com.google.common.base.Preconditions;

import lombok.RequiredArgsConstructor;
import pl.funds.Funds;
import pl.funds.Money;
import pl.funds.investment.strategy.InvestmentStrategy;

@RequiredArgsConstructor
public class InvestmentCalculator {

    private static final BigDecimal MINIMAL_INVESTMENT_AMOUNT = BigDecimal.valueOf(100);

    private final Funds funds;

    private final InvestmentStrategy strategy;

    public InvestmentScenario calculate(final Money amount) {
        final Money moneyToReturn = calculateAmountToReturn(amount);
        final Money moneyToInvest = amount.subtract(moneyToReturn);

        Preconditions.checkArgument(moneyToInvest.getAmount()
                .compareTo(MINIMAL_INVESTMENT_AMOUNT) > 0, "Amount of money to invest is too low. Should be greater than or equal to 100");
        return new InvestmentScenario(strategy.getInvestmentScenarioElements(funds, moneyToInvest), moneyToReturn);
    }

    private Money calculateAmountToReturn(Money amount) {
        final BigDecimal reminder = amount.getAmount()
                .remainder(MINIMAL_INVESTMENT_AMOUNT);
        return Money.of(reminder);
    }

}

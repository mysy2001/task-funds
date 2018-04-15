package pl.funds.investment.strategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import pl.funds.Fund;
import pl.funds.Money;
import pl.funds.investment.InvestmentScenarioElement;

@RequiredArgsConstructor(staticName = "of")
class InvestmentElementsCalculator {

    private final Money amount;

    private final Set<Fund> funds;

    private final int percentageOfAmountToUse;

    public Set<InvestmentScenarioElement> calculateElements() {
        final Money amountToSplit = amount.percentage(percentageOfAmountToUse);
        final List<Money> amountParts = split(amountToSplit);
        return createInvestmentElements(funds, amountParts);
    }

    private List<Money> split(final Money amountForType) {
        final int parts = funds.size();
        final BigDecimal amount = amountForType.getAmount();
        final BigDecimal reminder = amount.remainder(BigDecimal.valueOf(parts));
        final BigDecimal partAmount = amount.subtract(reminder)
                .divide(BigDecimal.valueOf(parts));

        return createParts(parts, partAmount, reminder);
    }

    private List<Money> createParts(int parts, final BigDecimal amount, final BigDecimal reminder) {
        final Money partWithReminder = Money.of(amount.add(reminder));
        final Money[] partArray = createFilledMoneyArray(parts, amount);
        partArray[0] = partWithReminder;
        return Arrays.asList(partArray);
    }

    private Money[] createFilledMoneyArray(int parts, BigDecimal partAmount) {
        final Money[] partArray = new Money[parts];
        Arrays.fill(partArray, Money.of(partAmount));
        return partArray;
    }

    private Set<InvestmentScenarioElement> createInvestmentElements(final Set<Fund> funds, final List<Money> amounts) {
        final Set<InvestmentScenarioElement> elements = new LinkedHashSet<>();
        final List<Fund> fundsList = new ArrayList<>(funds);
        for (int i = 0; i < fundsList.size(); i++) {
            final Money elementAmount = amounts.get(i);
            elements.add(InvestmentScenarioElement.of(elementAmount, fundsList.get(i), calculatePercentage(elementAmount)));
        }
        return elements;
    }

    private BigDecimal calculatePercentage(final Money elementAmount) {
        return elementAmount.getAmount()
                .multiply(BigDecimal.valueOf(100))
                .divide(amount.getAmount());

    }

}

package pl.funds.investment.strategy;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import pl.funds.Fund;
import pl.funds.Fund.Type;
import pl.funds.Funds;
import pl.funds.Money;
import pl.funds.investment.InvestmentScenarioElement;

@RequiredArgsConstructor
public class InvestmentStrategy {

    private final InvestmentStrategyConfiguration configuration;

    public Set<InvestmentScenarioElement> getInvestmentScenarioElements(final Funds funds, final Money amount) {

        final Set<InvestmentScenarioElement> result = new LinkedHashSet<>();
        for (Type type : configuration.getTypes()) {
            Optional<Integer> percentage = configuration.getPercentage(type);
            if ( percentage.isPresent() ) {
                final Set<Fund> typeFunds = funds.filterByType(type);
                final InvestmentElementsCalculator resolver = InvestmentElementsCalculator.of(amount, typeFunds, percentage.get());
                result.addAll(resolver.calculateElements());
            }
        }
        return result;
    }

}


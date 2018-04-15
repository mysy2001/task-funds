package pl.funds.investment;

import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.funds.Money;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class InvestmentScenario {

    private final Set<InvestmentScenarioElement> elements;

    private final Money amountToReturn;


}

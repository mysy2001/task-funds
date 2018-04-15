package pl.funds.investment;

import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.funds.Money;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Getter
public class InvestmentScenario {

    private final Set<InvestmentScenarioElement> elements;

    private final Money amountToReturn;


}

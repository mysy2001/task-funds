package pl.funds.investment;

import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.funds.Fund;
import pl.funds.Money;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "of")
@Getter
public class InvestmentScenarioElement {

    private final Money amount;

    private final Fund fund;

    private final BigDecimal percentage;
}

package pl.funds.investment;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.funds.FundTestObjects.FUND_CURRENCY_1;
import static pl.funds.FundTestObjects.FUND_FOREIGN_1;
import static pl.funds.FundTestObjects.FUND_FOREIGN_2;
import static pl.funds.FundTestObjects.FUND_FOREIGN_3;
import static pl.funds.FundTestObjects.FUND_PL_1;
import static pl.funds.FundTestObjects.FUND_PL_2;
import static pl.funds.FundTestObjects.FUND_PL_3;
import static pl.funds.MoneyTestObjects.MONEY_1;
import static pl.funds.MoneyTestObjects.MONEY_1000;
import static pl.funds.MoneyTestObjects.MONEY_10000;
import static pl.funds.MoneyTestObjects.MONEY_10001;
import static pl.funds.MoneyTestObjects.MONEY_2500;
import static pl.funds.MoneyTestObjects.MONEY_3750;
import static pl.funds.MoneyTestObjects.MONEY_500;
import static pl.funds.MoneyTestObjects.MONEY_666;
import static pl.funds.MoneyTestObjects.MONEY_668;
import static pl.funds.MoneyTestObjects.MONEY_ZERO;
import static pl.funds.investment.strategy.InvestmentStrategyConfiguration.SAFE_INVESTMENT_CONFIGURATION;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.assertj.core.util.Sets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.funds.Fund;
import pl.funds.Funds;
import pl.funds.investment.strategy.InvestmentStrategy;

@Slf4j
class InvestmentTest {

    @Test
    @DisplayName("Styl inwestowania: bezpieczny, fundusze: 2xPL, 3xZagraniczne, 1xPieniężne, kwota zwracana: 0")
    void should_create_safe_investment_scenario() {
        //given
        final Funds funds = Funds.of(FUND_PL_1, FUND_PL_2, FUND_FOREIGN_1, FUND_FOREIGN_2, FUND_FOREIGN_3, FUND_CURRENCY_1);
        final Set<InvestmentScenarioElement> elements = Sets.newLinkedHashSet( //
                InvestmentScenarioElement.of(MONEY_1000, FUND_PL_1, BigDecimal.valueOf(10)), //
                InvestmentScenarioElement.of(MONEY_1000, FUND_PL_2, BigDecimal.valueOf(10)), //
                InvestmentScenarioElement.of(MONEY_2500, FUND_FOREIGN_1, BigDecimal.valueOf(25)), //
                InvestmentScenarioElement.of(MONEY_2500, FUND_FOREIGN_2, BigDecimal.valueOf(25)), //
                InvestmentScenarioElement.of(MONEY_2500, FUND_FOREIGN_3, BigDecimal.valueOf(25)), //
                InvestmentScenarioElement.of(MONEY_500, FUND_CURRENCY_1, BigDecimal.valueOf(5)));
        final InvestmentScenario expected = new InvestmentScenario(elements, MONEY_ZERO);
        final InvestmentStrategy strategy = new InvestmentStrategy(SAFE_INVESTMENT_CONFIGURATION);
        final InvestmentCalculator objectUnderTest = new InvestmentCalculator(funds, strategy);
        //when
        final InvestmentScenario result = objectUnderTest.calculate(MONEY_10000);
        //then
        assertThat(result).isEqualTo(expected);

        log.info("Kwota inwestycji: " + MONEY_10000);
        log.info("Styl inwestowania: bezpieczny");
        print(new FundsPrinter(funds), new InvestmentScenarioPrinter(result));

    }

    @Test
    @DisplayName("Styl inwestowania: bezpieczny, fundusze: 2xPL, 3xZagraniczne, 1xPieniężne, kwota zwracana: 1")
    void should_create_safe_investment_scenario_with_amount_to_return() {

        //given
        final Funds funds = Funds.of(FUND_PL_1, FUND_PL_2, FUND_FOREIGN_1, FUND_FOREIGN_2, FUND_FOREIGN_3, FUND_CURRENCY_1);
        final Set<InvestmentScenarioElement> elements = Sets.newLinkedHashSet( //
                InvestmentScenarioElement.of(MONEY_1000, FUND_PL_1, BigDecimal.valueOf(10)), //
                InvestmentScenarioElement.of(MONEY_1000, FUND_PL_2, BigDecimal.valueOf(10)), //
                InvestmentScenarioElement.of(MONEY_2500, FUND_FOREIGN_1, BigDecimal.valueOf(25)), //
                InvestmentScenarioElement.of(MONEY_2500, FUND_FOREIGN_2, BigDecimal.valueOf(25)), //
                InvestmentScenarioElement.of(MONEY_2500, FUND_FOREIGN_3, BigDecimal.valueOf(25)), //
                InvestmentScenarioElement.of(MONEY_500, FUND_CURRENCY_1, BigDecimal.valueOf(5)));
        final InvestmentScenario expected = new InvestmentScenario(elements, MONEY_1);
        final InvestmentStrategy strategy = new InvestmentStrategy(SAFE_INVESTMENT_CONFIGURATION);
        final InvestmentCalculator objectUnderTest = new InvestmentCalculator(funds, strategy);
        //when
        final InvestmentScenario result = objectUnderTest.calculate(MONEY_10001);
        //then
        assertThat(result).isEqualTo(expected);
        log.info("Kwota inwestycji: " + MONEY_10001);
        log.info("Styl inwestowania: bezpieczny");
        print(new FundsPrinter(funds), new InvestmentScenarioPrinter(result));
    }

    @Test
    @DisplayName("Styl inwestowania: bezpieczny, fundusze: 3xPL, 2xZagraniczne, 1xPieniężne, kwota zwracana: 0")
    void should_create_safe_investment_scenario2() {
        //given
        final Funds funds = Funds.of(FUND_PL_1, FUND_PL_2, FUND_PL_3, FUND_FOREIGN_1, FUND_FOREIGN_2, FUND_CURRENCY_1);
        final Set<InvestmentScenarioElement> elements = Sets.newLinkedHashSet( //
                InvestmentScenarioElement.of(MONEY_668, FUND_PL_1, BigDecimal.valueOf(6.68)), //
                InvestmentScenarioElement.of(MONEY_666, FUND_PL_2, BigDecimal.valueOf(6.66)), //
                InvestmentScenarioElement.of(MONEY_666, FUND_PL_3, BigDecimal.valueOf(6.66)), //
                InvestmentScenarioElement.of(MONEY_3750, FUND_FOREIGN_1, BigDecimal.valueOf(37.5)), //
                InvestmentScenarioElement.of(MONEY_3750, FUND_FOREIGN_2, BigDecimal.valueOf(37.5)), //
                InvestmentScenarioElement.of(MONEY_500, FUND_CURRENCY_1, BigDecimal.valueOf(5)));
        final InvestmentScenario expected = new InvestmentScenario(elements, MONEY_ZERO);
        final InvestmentStrategy strategy = new InvestmentStrategy(SAFE_INVESTMENT_CONFIGURATION);
        final InvestmentCalculator objectUnderTest = new InvestmentCalculator(funds, strategy);
        //when
        final InvestmentScenario result = objectUnderTest.calculate(MONEY_10000);
        //then
        assertThat(result).isEqualTo(expected);

        log.info("Kwota inwestycji: " + MONEY_10000);
        log.info("Styl inwestowania: bezpieczny");
        print(new FundsPrinter(funds), new InvestmentScenarioPrinter(result));
    }

    void print(AbstractFundPrinter... printers) {

        for (AbstractFundPrinter p : printers) {
            log.info(p.print());
        }
    }
}

abstract class AbstractFundPrinter {

    String NEW_LINE = System.getProperty("line.separator");

    private static final Map<Fund.Type, String> types = new HashMap<>();

    static {
        types.put(Fund.Type.PL, "Polskie");
        types.put(Fund.Type.FOREIGN, "Zagraniczne");
        types.put(Fund.Type.CURRENCY, "Pieniężne");
    }

    abstract String print();

    String getFundType(final Fund fund) {
        return fund.getType() != null ? types.get(fund.getType()) : "";
    }

    String getFundName(final Fund fund) {
        return fund.getName();
    }
}

@RequiredArgsConstructor
class FundsPrinter extends AbstractFundPrinter {

    private final Funds funds;

    public String print() {
        final StringBuilder builder = new StringBuilder() //
                .append(printTableHeader());

        int i = 0;
        for (Fund f : funds.getFunds()) {
            builder.append(printFund(f, ++i))
                    .append(NEW_LINE);

        }
        return builder.toString();
    }

    private String printTableHeader() {
        return new StringBuilder() //
                .append(NEW_LINE)
                .append("| LP | Rodzaj | Nazwa |")
                .append(NEW_LINE)
                .append("|:---:|--------|:---|")
                .append(NEW_LINE)
                .toString();
    }

    private String printFund(final Fund fund, int index) {
        final StringBuilder builder = new StringBuilder().append("| ")
                .append(index)
                .append(" | ")
                .append(getFundType(fund))
                .append(" | ")
                .append(getFundName(fund))
                .append(" |");
        return builder.toString();
    }

}

@RequiredArgsConstructor
class InvestmentScenarioPrinter extends AbstractFundPrinter {

    private final InvestmentScenario scenario;

    @Override
    public String print() {
        final StringBuilder builder = new StringBuilder() //
                .append("Kwota zwrotu: ")
                .append(scenario.getAmountToReturn())
                .append(NEW_LINE)
                .append(printTableHeader());

        int i = 0;
        for (InvestmentScenarioElement e : scenario.getElements()) {
            builder.append(printInvestmentScenarioElement(e, ++i));
        }

        return builder.toString();

    }

    private String printTableHeader() {
        final StringBuilder builder = new StringBuilder().append("| LP | Rodzaj | Nazwa  | Kwota | Procent |")
                .append(NEW_LINE)
                .append("|:---:|-------|--------|-------|---------|")
                .append(NEW_LINE);
        return builder.toString();
    }

    private String printInvestmentScenarioElement(final InvestmentScenarioElement element, final int index) {
        final Fund fund = element.getFund();
        final StringBuilder builder = new StringBuilder().append("| ")
                .append(index)
                .append(" | ")
                .append(getFundType(fund))
                .append(" | ")
                .append(getFundName(fund))
                .append(" | ")
                .append(element.getAmount())
                .append(" | ")
                .append(element.getPercentage())
                .append("% |")
                .append(NEW_LINE);
        return builder.toString();
    }

}
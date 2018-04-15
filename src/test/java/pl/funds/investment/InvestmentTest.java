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
import static pl.funds.MoneyTestObjects.MONEY_1001;
import static pl.funds.MoneyTestObjects.MONEY_2500;
import static pl.funds.MoneyTestObjects.MONEY_3750;
import static pl.funds.MoneyTestObjects.MONEY_500;
import static pl.funds.MoneyTestObjects.MONEY_666;
import static pl.funds.MoneyTestObjects.MONEY_668;
import static pl.funds.MoneyTestObjects.MONEY_ZERO;
import static pl.funds.investment.strategy.InvestmentStrategyConfiguration.SAFE_INVESTMENT_CONFIGURATION;

import java.math.BigDecimal;
import java.util.Set;

import org.assertj.core.util.Sets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import pl.funds.Funds;
import pl.funds.investment.strategy.InvestmentStrategy;

//@Slf4j
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
        final InvestmentScenario result = objectUnderTest.calculate(MONEY_1001);
        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Styl inwestowania: bezpieczny, fundusze: 3xPL, 2xZagraniczne, 1xPieniężne, kwota zwracana: 0")
    void should_create_safe_investment_scenario2() {
        //given
        final Funds funds = Funds.of(FUND_PL_1, FUND_PL_2, FUND_PL_3, FUND_FOREIGN_2, FUND_FOREIGN_3, FUND_CURRENCY_1);
        final Set<InvestmentScenarioElement> elements = Sets.newLinkedHashSet( //
                InvestmentScenarioElement.of(MONEY_668, FUND_PL_1, BigDecimal.valueOf(6.68)), //
                InvestmentScenarioElement.of(MONEY_666, FUND_PL_2, BigDecimal.valueOf(6.66)), //
                InvestmentScenarioElement.of(MONEY_666, FUND_PL_3, BigDecimal.valueOf(6.66)), //
                InvestmentScenarioElement.of(MONEY_3750, FUND_FOREIGN_2, BigDecimal.valueOf(37.5)), //
                InvestmentScenarioElement.of(MONEY_3750, FUND_FOREIGN_3, BigDecimal.valueOf(37.5)), //
                InvestmentScenarioElement.of(MONEY_500, FUND_CURRENCY_1, BigDecimal.valueOf(5)));
        final InvestmentScenario expected = new InvestmentScenario(elements, MONEY_ZERO);
        final InvestmentStrategy strategy = new InvestmentStrategy(SAFE_INVESTMENT_CONFIGURATION);
        final InvestmentCalculator objectUnderTest = new InvestmentCalculator(funds, strategy);
        //when
        final InvestmentScenario result = objectUnderTest.calculate(MONEY_10000);
        //then
        assertThat(result).isEqualTo(expected);
    }

}

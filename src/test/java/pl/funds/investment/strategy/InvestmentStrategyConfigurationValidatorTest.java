package pl.funds.investment.strategy;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static pl.funds.Fund.Type.FOREIGN;
import static pl.funds.Fund.Type.PL;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.funds.Fund.Type;

class InvestmentStrategyConfigurationValidatorTest {

    private InvestmentStrategyConfigurationValidator objectUnderTest;

    private Map<Type, Integer> configuration;

    @BeforeEach
    void setUp() {
        this.configuration = new HashMap<>();
        this.objectUnderTest = new InvestmentStrategyConfigurationValidator(configuration);
    }

    @Test
    void should_throw_exception_when_percentage_summary_is_greater_than_100() {

        configure(50, 51);

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> objectUnderTest.validate());

    }

    @Test
    void should_throw_exception_when_percentage_summary_is_lower_than_100() {

        configure(49, 49);

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> objectUnderTest.validate());

    }

    @Test
    void should_not_throw_exception_when_percentage_summary_is_equal_to_100() {

        configure(50, 50);

        assertThatCode(() -> objectUnderTest.validate()).doesNotThrowAnyException();

    }

    private void configure(final int value1, final int value2) {
        configuration.put(PL, value1);
        configuration.put(FOREIGN, value2);
    }

}
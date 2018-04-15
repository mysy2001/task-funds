package pl.funds.investment.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.funds.Fund.Type.CURRENCY;
import static pl.funds.Fund.Type.FOREIGN;
import static pl.funds.Fund.Type.PL;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.funds.Fund.Type;

class InvestmentStrategyConfigurationTest {

    private static final Map<Type, Integer> CONFIGURATION = createTypesMap(PL, FOREIGN);

    private static Map<Type, Integer> createTypesMap(final Type... types) {
        final Map<Type, Integer> configuration = new LinkedHashMap<>();
        for (int i = 0; i < types.length; i++) {
            configuration.put(types[i], i);
        }
        return configuration;
    }

    private InvestmentStrategyConfiguration objectUnderTest;

    @BeforeEach
    void setUp() {
        this.objectUnderTest = new InvestmentStrategyConfiguration(CONFIGURATION);
    }

    @Test
    void should_return_fund_types() {

        final Set<Type> result = objectUnderTest.getTypes();

        assertThat(result).isEqualTo(Sets.newLinkedHashSet(PL, FOREIGN));
    }

    @Test
    void should_return_type_percentage_when_type_exists_in_configuration() {

        final Optional<Integer> result = objectUnderTest.getPercentage(PL);

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(0);
    }

    @Test
    void should_return_type_percentage_when_type_does_not_exist_in_configuration() {

        final Optional<Integer> result = objectUnderTest.getPercentage(CURRENCY);

        assertThat(result.isPresent()).isFalse();
    }

}
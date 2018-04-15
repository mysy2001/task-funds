package pl.funds.investment.strategy;

import static pl.funds.Fund.Type.CURRENCY;
import static pl.funds.Fund.Type.FOREIGN;
import static pl.funds.Fund.Type.PL;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.funds.Fund.Type;

@ToString
@EqualsAndHashCode
public class InvestmentStrategyConfiguration {

    public static final InvestmentStrategyConfiguration SAFE_INVESTMENT_CONFIGURATION = new InvestmentStrategyConfiguration.Builder() //
            .withTypePercentage(PL, 20)
            .withTypePercentage(FOREIGN, 75)
            .withTypePercentage(CURRENCY, 5)
            .build();

    public static InvestmentStrategyConfiguration BALANCED_INVESTMENT_CONFGIURATION = new InvestmentStrategyConfiguration.Builder() //
            .withTypePercentage(PL, 30)
            .withTypePercentage(FOREIGN, 60)
            .withTypePercentage(CURRENCY, 10)
            .build();

    public static InvestmentStrategyConfiguration AGGRESSIVE_INVESTMENT_CONFIGURATION = new InvestmentStrategyConfiguration.Builder() //
            .withTypePercentage(PL, 40)
            .withTypePercentage(FOREIGN, 20)
            .withTypePercentage(CURRENCY, 40)
            .build();

    private final Map<Type, Integer> configuration;

    InvestmentStrategyConfiguration(final Map<Type, Integer> configuration) {
        this.configuration = configuration;
    }

    public Set<Type> getTypes() {
        return configuration.keySet();
    }

    public Optional<Integer> getPercentage(final Type type) {
        return Optional.ofNullable(configuration.get(type));
    }

    public static class Builder {

        private final Map<Type, Integer> configuration;

        public Builder() {
            this.configuration = new LinkedHashMap<>();
        }

        public Builder withTypePercentage(final Type type, final int percentage) {
            configuration.put(type, percentage);
            return this;
        }

        public InvestmentStrategyConfiguration build() {
            new InvestmentStrategyConfigurationValidator(configuration).validate();
            return new InvestmentStrategyConfiguration(configuration);
        }
    }

}

@RequiredArgsConstructor
class InvestmentStrategyConfigurationValidator {

    private static final int PERCENTAGE_REQUIRED_SUM = 100;

    private final Map<Type, Integer> configuration;

    public void validate() {

        if ( !isValid() ) {
            throw new IllegalArgumentException("Configured percentage must be qual to 100");
        }
    }

    private boolean isValid() {
        final int sum = configuration.values()
                .stream()
                .mapToInt(Number::intValue)
                .sum();
        return sum == PERCENTAGE_REQUIRED_SUM;
    }
}


package pl.funds;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.funds.Fund.Type;

@RequiredArgsConstructor(staticName = "of")
@Getter
public class Funds {

    private final Set<Fund> funds;

    public static Funds of(Fund... funds) {
        final Set<Fund> fundSet = new LinkedHashSet<>(Arrays.asList(funds));
        return new Funds(fundSet);
    }

    public Set<Fund> filterByType(final Type type) {
        return funds.stream()
                .filter(fund -> fund.isTypeOf(type))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}

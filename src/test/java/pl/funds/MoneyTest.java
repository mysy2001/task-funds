package pl.funds;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.funds.MoneyTestObjects.MONEY_1000;
import static pl.funds.MoneyTestObjects.MONEY_2500;
import static pl.funds.MoneyTestObjects.MONEY_500;
import static pl.funds.MoneyTestObjects.MONEY_666;
import static pl.funds.MoneyTestObjects.MONEY_668;
import static pl.funds.MoneyTestObjects.MONEY_DEBT_1000;
import static pl.funds.MoneyTestObjects.MONEY_ZERO;

import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.util.Lists;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MoneyTest {

    private static Stream<Arguments> createParametersForSubtract() {
        return Stream.of(Arguments.of(1001, 1, MONEY_1000), Arguments.of(1, 1001, MONEY_DEBT_1000), Arguments.of(1000, 1000, MONEY_ZERO));
    }

//    private static Stream<Arguments> createParametersForSplit() {
//        return Stream.of( //
//                Arguments.of(1000, 1, Lists.newArrayList(MONEY_1000)), Arguments.of(1000, 2, Lists.newArrayList(MONEY_500, MONEY_500)),
//                Arguments.of(2000, 3, Lists.newArrayList(MONEY_668, MONEY_666, MONEY_666)),
//                Arguments.of(7500, 3, Lists.newArrayList(MONEY_2500, MONEY_2500, MONEY_2500)));
//    }

    @ParameterizedTest
    @MethodSource("createParametersForSubtract")
    void should_subtract_amount(final int amount, final int amountToSubtract, final Money expected) {
        final Money objectUnderTest = new Money(amount);

        final Money result = objectUnderTest.subtract(new Money(amountToSubtract));

        assertThat(result).isEqualTo(expected);

    }

//    @ParameterizedTest
//    @MethodSource("createParametersForSplit")
//    void should_split_amount_to_parts(final int amount, final int parts, final List<Money> expected) {
//
//        final Money objectUnderTest = new Money(amount);
//
//        final List<Money> result = objectUnderTest.split(parts);
//
//        assertThat(result).isEqualTo(expected);
//
//    }

}
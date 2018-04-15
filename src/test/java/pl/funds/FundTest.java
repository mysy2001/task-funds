package pl.funds;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.funds.Fund.Type.CURRENCY;
import static pl.funds.Fund.Type.FOREIGN;
import static pl.funds.Fund.Type.PL;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import pl.funds.Fund.Type;

class FundTest {

    private static Stream<Arguments> createParameters() {
        return Stream.of( //
                Arguments.of(PL, PL, true), //
                Arguments.of(PL, FOREIGN, false), //
                Arguments.of(PL, CURRENCY, false), //
                Arguments.of(FOREIGN, PL, false), //
                Arguments.of(FOREIGN, FOREIGN, true), //
                Arguments.of(FOREIGN, CURRENCY, false), //
                Arguments.of(CURRENCY, PL, false), //
                Arguments.of(CURRENCY, FOREIGN, false), //
                Arguments.of(CURRENCY, CURRENCY, true) //

        );
    }

    @ParameterizedTest
    @MethodSource("createParameters")
    void should_compare_fund_type(final Type type, final Type other, final boolean expected) {

        final Fund objectUnderTest = Fund.of("id", "name", type);

        final boolean result = objectUnderTest.isTypeOf(other);

        assertThat(result).isEqualTo(expected);

    }

}
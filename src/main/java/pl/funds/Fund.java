package pl.funds;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "of")
@Getter
public class Fund {

    private final String Id;

    private final String name;

    private final Type type;

    boolean isTypeOf(final Type type) {
        return this.type == type;
    }

    public enum Type {
        PL, FOREIGN, CURRENCY
    }
}

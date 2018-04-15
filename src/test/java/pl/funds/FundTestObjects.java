package pl.funds;

import static pl.funds.Fund.Type.CURRENCY;
import static pl.funds.Fund.Type.FOREIGN;
import static pl.funds.Fund.Type.PL;

public class FundTestObjects {

    public static final Fund FUND_PL_1 = createPlFund("PL1", "Fundusz Polski 1");

    public static final Fund FUND_PL_2 = createPlFund("PL2", "Fundusz Polski 2");

    public static final Fund FUND_PL_3 = createPlFund("PL3", "Fundusz Polski 3");

    public static final Fund FUND_FOREIGN_1 = createForeignFund("FRG1", "Fundusz Zagraniczny 1");

    public static final Fund FUND_FOREIGN_2 = createForeignFund("FRG2", "Fundusz Zagraniczny 2");

    public static final Fund FUND_FOREIGN_3 = createForeignFund("FRG3", "Fundusz Zagraniczny 3");

    public static final Fund FUND_CURRENCY_1 = createCurrencyFund("CUR1", "Fundusz Pieniężny 1");

    private static Fund createPlFund(final String id, final String name) {
        return createFund(id, name, PL);
    }

    private static Fund createForeignFund(final String id, final String name) {
        return createFund(id, name, FOREIGN);
    }

    private static Fund createCurrencyFund(final String id, final String name) {
        return createFund(id, name, CURRENCY);
    }

    private static Fund createFund(final String id, final String name, final Fund.Type type) {
        return Fund.of(id, name, type);
    }

    private FundTestObjects() {

    }
}

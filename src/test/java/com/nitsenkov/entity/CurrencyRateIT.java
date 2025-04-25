package com.nitsenkov.entity;

import com.nitsenkov.BaseIntegrationTest;
import com.nitsenkov.util.TestObjectsBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrencyRateIT extends BaseIntegrationTest {

    @Test
    void create() {
        CurrencyRate currencyRate = TestObjectsBuilder.getCurrencyRate();

        session.persist(currencyRate);
        session.flush();
        session.clear();
        CurrencyRate actualCurrencyRate = session.get(CurrencyRate.class, currencyRate.getId());

        assertThat(actualCurrencyRate).isEqualTo(currencyRate);
    }
}

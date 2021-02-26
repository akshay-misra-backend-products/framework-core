package com.gbss.framework.core.api.service.api;

import com.gbss.framework.core.model.entities.Currency;
import com.gbss.framework.core.model.entities.Locale;

import java.util.List;

public interface LocaleService {

    List<Locale> getLocales();

    Locale getLocaleById(String id);

    Locale createLocale(Locale locale);

    Locale updateLocale(Locale locale);

    boolean deleteLocale(String id);

    List<Currency> getCurrencies();

    Currency getCurrencyById(String id);

    Currency createCurrency(Currency currency);

    Currency updateCurrency(Currency currency);

    boolean deleteCurrency(String id);
}

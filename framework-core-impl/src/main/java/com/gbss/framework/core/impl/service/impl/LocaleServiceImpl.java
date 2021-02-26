package com.gbss.framework.core.impl.service.impl;

import com.gbss.framework.core.api.service.api.LocaleService;
import com.gbss.framework.core.impl.repositories.CurrencyRepository;
import com.gbss.framework.core.impl.repositories.LocaleRepository;
import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.model.entities.Currency;
import com.gbss.framework.core.model.entities.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocaleServiceImpl extends ApplicationAuditServiceImpl implements LocaleService {

    @Autowired
    private LocaleRepository localeRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public List<Locale> getLocales() {
        Sort sortByName = Sort.by(Sort.Direction.DESC, "name");
        return localeRepository.findAll(sortByName);
    }

    @Override
    public Locale getLocaleById(String id) {
        return localeRepository.findById(id).get();
    }

    @Override
    public Locale createLocale(Locale locale) {
        System.out.println("... createLocale, locale: "+ locale);
        locale.setObjectTypeId(SystemConstants.ObjectTypes.LOCALE);
        return localeRepository.save(locale);
    }

    @Override
    public Locale updateLocale(Locale locale) {
        Optional<Locale> localeOp = localeRepository.findById(locale.getId());
        handleAudit(localeOp.get(), locale);
        return localeRepository.save(locale);
    }

    @Override
    public boolean deleteLocale(String id) {
        System.out.println("... deleteLocale, id: "+id);
        Optional<Locale> locale = localeRepository.findById(id);
        if (locale.isPresent()) {
            localeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Currency> getCurrencies() {
        Sort sortByName = Sort.by(Sort.Direction.DESC, "name");
        return currencyRepository.findAll(sortByName);
    }

    @Override
    public Currency getCurrencyById(String id) {
        return currencyRepository.findById(id).get();
    }

    @Override
    public Currency createCurrency(Currency currency) {
        System.out.println("... createCurrency, currency: "+ currency);
        currency.setObjectTypeId(SystemConstants.ObjectTypes.CURRENCY);
        return currencyRepository.save(currency);
    }

    @Override
    public Currency updateCurrency(Currency currency) {
        Optional<Currency> currencyOp = currencyRepository.findById(currency.getId());
        handleAudit(currencyOp.get(), currency);
        return currencyRepository.save(currency);
    }

    @Override
    public boolean deleteCurrency(String id) {
        System.out.println("... deleteCurrency, id: "+ id);
        Optional<Currency> currency = currencyRepository.findById(id);
        if (currency.isPresent()) {
            currencyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

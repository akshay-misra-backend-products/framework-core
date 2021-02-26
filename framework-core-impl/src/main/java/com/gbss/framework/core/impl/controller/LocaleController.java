package com.gbss.framework.core.impl.controller;

import com.gbss.framework.core.api.service.api.LocaleService;
import com.gbss.framework.core.model.entities.Currency;
import com.gbss.framework.core.model.entities.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/application/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LocaleController {

    @Autowired
    LocaleService localeService;

    @GetMapping("/5f41766eb04d4e2b37d435cb/load/all")
    public List<Locale> getObjectTypes() {
        return localeService.getLocales();
    }

    @GetMapping(value="/5f41766eb04d4e2b37d435cb/load/{id}")
    public Locale getLocaleById(@PathVariable("id") String id) {
        return localeService.getLocaleById(id);
    }

    @PostMapping("/5f41766eb04d4e2b37d435cb/add")
    public Locale createLocale(@RequestBody Locale locale) {
        return localeService.createLocale(locale);
    }

    @PutMapping(value="/5f41766eb04d4e2b37d435cb/update")
    public Locale updateLocale(@Valid @RequestBody Locale locale) {
        return localeService.updateLocale(locale);
    }

    @DeleteMapping(value="/5f41766eb04d4e2b37d435cb/delete/{id}")
    public boolean deleteObjectType(@PathVariable("id") String id) {
        return localeService.deleteLocale(id);
    }

    @GetMapping("/5f4176d7b04d4e2b37d435cc/load/all")
    public List<Currency> getCurrencies() {
        return localeService.getCurrencies();
    }

    @GetMapping(value="/5f4176d7b04d4e2b37d435cc/load/{id}")
    public Currency getCurrencyById(@PathVariable("id") String id) {
        return localeService.getCurrencyById(id);
    }

    @PostMapping("/5f4176d7b04d4e2b37d435cc/add")
    public Currency createCurrency(@RequestBody Currency currency) {
        return localeService.createCurrency(currency);
    }

    @PutMapping(value="/5f4176d7b04d4e2b37d435cc/update")
    public Currency updateCurrency(@Valid @RequestBody Currency currency) {
        return localeService.updateCurrency(currency);
    }

    @DeleteMapping(value="/5f4176d7b04d4e2b37d435cc/delete/{id}")
    public boolean deleteCurrency(@PathVariable("id") String id) {
        return localeService.deleteCurrency(id);
    }
}

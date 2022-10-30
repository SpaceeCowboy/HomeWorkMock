package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LocalizationServiceImplTest {


    @Test
    public void localeTest(){
        String text = "Добро пожаловать";
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        assertThat(text, equalTo(localizationService.locale(Country.RUSSIA)));
    }
}

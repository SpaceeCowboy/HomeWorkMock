import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;


public class GeoServiceTest {

    GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
    LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
    MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
    Map<String, String> headers = new HashMap<String, String>();


    @Test
    public void testGeoServiceRu() {
        Location locationRu;
        String textRu;
        //Проверка определения локации
        Mockito.when(geoService.byIp(GeoServiceImpl.MOSCOW_IP))
                .thenReturn(locationRu = new Location("Moscow", Country.RUSSIA, null, 0));
        assertThat(locationRu.getCountry(), Matchers.equalTo(Country.RUSSIA));

        //Проверка возращаемого текста
        Mockito.when(localizationService.locale(locationRu.getCountry()))
                .thenReturn(textRu = "Русский язык");
        assertThat(textRu, Matchers.equalTo("Русский язык"));

        //проверка языка сообщения
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.MOSCOW_IP);
        Mockito.when(messageSender.send(headers))
                .thenReturn(textRu);
        assertThat(textRu, Matchers.equalTo("Русский язык"));

    }

    @Test
    public void testGeoServiceEng() {
        Location locationEng;
        String textEng;

        //Проверка определения локации
        Mockito.when(geoService.byIp(GeoServiceImpl.NEW_YORK_IP))
                .thenReturn(locationEng = new Location("New York", Country.USA, null, 0));
        assertThat(locationEng.getCountry(), Matchers.equalTo(Country.USA));

        //Проверка возращаемого текста
        Mockito.when(localizationService.locale(locationEng.getCountry()))
                .thenReturn(textEng = "English language");
        assertThat(textEng, Matchers.equalTo("English language"));

        //проверка языка сообщения
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.NEW_YORK_IP);
        Mockito.when(messageSender.send(headers))
                .thenReturn(textEng);
        assertThat(textEng, Matchers.equalTo("English language"));

    }

}



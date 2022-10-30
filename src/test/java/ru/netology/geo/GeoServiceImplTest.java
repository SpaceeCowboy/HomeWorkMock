package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GeoServiceImplTest {

    @Test
    public void byIpTest(){
        Location testLocation = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        String ruIp = "172.0.32.11";
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp(ruIp);
        assertThat(testLocation.getCountry(), equalTo(location.getCountry()));


    }
}

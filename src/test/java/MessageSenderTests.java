import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MessageSenderTests {
    @DisplayName("shouldOnlyRussianTextAlwaysReturned")
    @Test
    void shouldOnlyRussianTextAlwaysReturned() {
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);

        String ip = "172.0.0.0";
        Mockito.when(geoService.byIp(ip)).thenReturn(
                new Location(null, Country.RUSSIA, null, 0));

        String expected = "Добро пожаловать";
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn(expected);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        String actual = messageSender.send(headers);

        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("shouldOnlyEnglishTextAlwaysReturned")
    @Test
    void shouldOnlyEnglishTextAlwaysReturned() {
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);

        String ip = "96.0.0.0";
        Mockito.when(geoService.byIp(ip)).thenReturn(
                new Location(null, Country.USA, null, 0));

        String expected = "Welcome";
        Mockito.when(localizationService.locale(Country.USA)).thenReturn(expected);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        String actual = messageSender.send(headers);

        Assertions.assertEquals(expected, actual);
    }
}
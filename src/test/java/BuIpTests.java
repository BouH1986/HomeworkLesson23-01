import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class BuIpTests {

    public static Stream<Arguments> byIpTestWithParameters() {
        return Stream.of(
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("172.0.0.2", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("96.0.0.3", new Location("New York", Country.USA, null,  0)),
                Arguments.of("0.0.0.0", null)
        );
    }

    @ParameterizedTest
    @MethodSource("byIpTestWithParameters")
    public void byIpTest(String ip, Location expected) {
        GeoServiceImpl geoService = new GeoServiceImpl();

        //when
        Location actual = geoService.byIp(ip);

        //then
        Assertions.assertEquals(expected, actual);
    }
}

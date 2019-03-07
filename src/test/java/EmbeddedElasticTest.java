import lombok.SneakyThrows;
import org.junit.Test;
import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;

public class EmbeddedElasticTest {

    @Test
    @SneakyThrows
    public void kan_kjore_embedded_elastic() {

        EmbeddedElastic.builder()
                .withElasticVersion("6.5.2")
                .build()
                .start();
    }
}

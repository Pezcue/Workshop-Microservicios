package lectorArchivo.reader.services.ReaderFiles;

import com.opencsv.bean.CsvToBeanBuilder;
import lectorArchivo.reader.DTO.CsvModel;
import lectorArchivo.reader.DTO.Respuesta;
import lectorArchivo.reader.services.Reader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReaderCsvService extends Reader {

    @Override
    public Respuesta readerFile(String urlFile) throws IOException {
        List<CsvModel> elements = new CsvToBeanBuilder(new FileReader(urlFile))
                .withType(CsvModel.class)
                .build()
                .parse();

        elements.forEach((element) -> {
            boolean response = this.connection.webClient().post().uri("validateData/csv")
                    .bodyValue(element).retrieve()
                    .bodyToMono(boolean.class).block();
            System.out.println(response);
            if (!response) {
                this.invalidLines++;
            }else {
                this.validLines++;
            }
        });

        Respuesta respuesta = new Respuesta(this.validLines, this.invalidLines);
        return respuesta;
    }
}

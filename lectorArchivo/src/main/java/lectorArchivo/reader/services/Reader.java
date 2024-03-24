package lectorArchivo.reader.services;

import lectorArchivo.reader.DTO.Respuesta;
import lectorArchivo.reader.util.Connection;
import java.io.IOException;

public abstract class Reader {

    protected Connection connection = new Connection();
    protected Integer validLines = 0;
    protected Integer invalidLines = 0;
    protected Respuesta respuesta;

    public abstract Respuesta readerFile(String urlFile) throws IOException;

}

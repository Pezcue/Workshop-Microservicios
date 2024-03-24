package lectorArchivo.reader.util;

import lectorArchivo.reader.services.Reader;
import lectorArchivo.reader.services.ReaderFiles.ReaderCsvService;
import lectorArchivo.reader.services.ReaderFiles.ReaderXlsxService;

import java.util.HashMap;
import java.util.Map;
public class MapFiles {

    private Map<String, Reader> readerServices = new HashMap<>();

    public MapFiles() {
        readerServices.put("csv", new ReaderCsvService());
        readerServices.put("xlsx", new ReaderXlsxService());
    }

    public void addReaderService(String fileType, Reader readerService) {
        readerServices.put(fileType, readerService);
    }

    public Reader getReaderService(String fileType) {
        return readerServices.get(fileType);
    }

}

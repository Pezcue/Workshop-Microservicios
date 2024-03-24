package lectorArchivo.reader.services;


import lectorArchivo.reader.DTO.Respuesta;
import lectorArchivo.reader.util.MapFiles;
import org.springframework.stereotype.Service;

@Service
public class ReaderService {

    private Reader readerServices;

    public Respuesta procesarArchivo(String urlFile) {
        String fileType = urlFile.substring(urlFile.lastIndexOf(".") + 1);
        readerServices = new MapFiles().getReaderService(fileType);
        if (readerServices == null) {
            System.out.println("File type not supported");
        }
        try {
            return readerServices.readerFile(urlFile);
        } catch (Exception e) {
            System.out.println("Ocurred a problem reading the file");
        }
    return null;
    }

}

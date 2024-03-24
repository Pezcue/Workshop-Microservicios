package lectorArchivo.reader.controller;

import lectorArchivo.reader.DTO.Request;
import lectorArchivo.reader.DTO.Respuesta;
import lectorArchivo.reader.services.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    //Add ReaderCsvService
    @Autowired
    private ReaderService readerService;

    public Controller(ReaderService readerService) {
        this.readerService = readerService;
    }

    @PostMapping("/cargar")
    public ResponseEntity<Respuesta> procesarArchivo(@RequestBody Request request) {
        System.out.println("Archivo cargado con exito ✅");
        return ResponseEntity.ok(readerService.procesarArchivo(request.getUrl()));
    }

    //prueba
    @GetMapping("/test")
    public String test(){
        return "Test funciona 🌻";
    }

}
package com.validador.validadorIntento.Controller;

import com.validador.validadorIntento.Models.PersonaCsv;
import com.validador.validadorIntento.Models.PersonaExcel;
import com.validador.validadorIntento.Services.CSVValidator;
import com.validador.validadorIntento.Services.ExcelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArchivoController {

    private CSVValidator validadorCSV;
    private ExcelValidator validadorExcel;

    @Autowired
    public ArchivoController(CSVValidator validadorCSV) {
        this.validadorCSV = validadorCSV;
    }
    @Autowired
    public ArchivoController(ExcelValidator validadorExcel) {
        this.validadorExcel = validadorExcel;
    }


    @PostMapping("/CSVValidator")
    public Boolean validarEstructuraCSV(@RequestBody PersonaCsv registroCSV)  {
        return this.validadorCSV.validarEstructura(registroCSV);
    }

    @PostMapping("/ExcelValidator")
    public Boolean validarEstructuraExcel(@RequestBody PersonaExcel registroExcel) {
        return this.validadorExcel.validarEstructura(registroExcel);
    }

}

package com.validador.validadorIntento.Services;

import com.validador.validadorIntento.Models.PersonaCsv;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

@Service
public class CSVValidator {
    //Validar el email
    public Boolean validarEstructuraEmail (String email) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        Boolean validarEstructuraEmail = matcher.find();
        return validarEstructuraEmail;
    }

    //Validar el job title
    public Boolean validarJobTitle(String jobTitle) {
        jobTitle = jobTitle.toLowerCase(Locale.ROOT);
        String [] jobs = {"haematologist", "phytotherapist", "building surveyor", "insurance account manager" +
                "educational psychologist"};
        List<String> jobList = Arrays.asList(jobs);

        Boolean validarJobTitle = jobList.contains(jobTitle);
        return validarJobTitle;
    }

    //Validar el date of birth
    public Boolean validarDateOfBirth (String dateOfBirth) {
        String[] partes = dateOfBirth.split("/");
        String mes = partes[0];
        String dia = partes[1];
        String anio = partes[2];

        Boolean validarDateOfBirth = false;

        if (parseInt(anio) > 1980 && parseInt(mes) >= 1 && parseInt(dia) >= 1) {
            validarDateOfBirth = true;
            return validarDateOfBirth;
        }
        return validarDateOfBirth;
    }

    //Validar la estructura del CSV
    public Boolean validarEstructura(PersonaCsv registroCSV) {
        return validarEstructuraEmail(registroCSV.getEmail()) && validarJobTitle(registroCSV.getJobTitle()) && validarDateOfBirth(registroCSV.getDateOfBirth());
    }
}

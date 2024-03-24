package lectorArchivo.reader.DTO;

public class Respuesta {
    private Integer validLines;
    private Integer invalidLines;

    public Respuesta() {
    }

    public Respuesta(Integer validLines, Integer invalidLines) {
        this.validLines = validLines;
        this.invalidLines = invalidLines;
    }

    public Integer getValidLines() {
        return validLines;
    }

    public Integer getInvalidLines() {
        return invalidLines;
    }

    public void setValidLines(Integer validLines) {
        this.validLines = validLines;
    }

    public void setInvalidLines(Integer invalidLines) {
        this.invalidLines = invalidLines;
    }

}

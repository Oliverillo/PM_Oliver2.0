package com.example.listasejer;

public class Films {
    private String title;
    private String image;
    private String fechaSalida;

    public Films(String title, String image, String fechaSalida) {
        this.title = title;
        this.image = image;
        this.fechaSalida = fechaSalida;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }
}

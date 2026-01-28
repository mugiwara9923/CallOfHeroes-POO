package historia;

import java.util.List;

public class Cena {

    private String titulo;
    private List<String> textos;
    private String imagemPath;

    public Cena(String titulo, List<String> textos, String imagemPath) {
        this.titulo = titulo;
        this.textos = textos;
        this.imagemPath = imagemPath;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<String> getTextos() {
        return textos;
    }

    public String getImagemPath() {
        return imagemPath;
    }
}

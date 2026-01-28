package jogo;

public enum LocalMapa {

    BASE(520, 330),

    INDUSTRIA(820, 260),
    CENTRO(460, 210),
    PORTO(900, 480),
    SUBURBIO(300, 360),
    DATA_CENTER(650, 180);

    public final int x;
    public final int y;

    LocalMapa(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

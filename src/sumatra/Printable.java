package sumatra;

import java.io.OutputStream;

public interface Printable {
    /**
     * Kiírja a megvalósító osztály adatait az átadott streamre
     * @param stream ahova kiírjuk az adatokat
     */
    public void printData(OutputStream stream);
}

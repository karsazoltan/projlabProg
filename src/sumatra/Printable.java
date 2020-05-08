package sumatra;

import java.io.OutputStream;

/**
 * Printable interfész - ami ezt implementálja, annak az állapota elmenthető szöveges formátumban
 */
public interface Printable {
    /**
     * Kiírja a megvalósító osztály adatait az átadott streamre
     * @param stream ahova kiírjuk az adatokat
     * @param prefix Előtag (általában sok space)
     */
    void printData(OutputStream stream, String prefix);
}

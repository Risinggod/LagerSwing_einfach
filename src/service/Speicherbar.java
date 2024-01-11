package service;

import java.util.List;

/**
 * Interface für alle Klassen, die Daten speichern können.
 * 
 * @author Claus Gumbmann (C) Fachschule für Wirtschaftsinformatik,
 *         Herzogenaurach, Mittelfranken
 *
 */

interface Speicherbar<T> {
    /**
     * Liefert alle gespeicherten Daten zurück.
     * 
     * @return Liste mit allen gespeicherten Daten
     */
    abstract List<T> getAll();

    /**
     * Erzeugt einen neuen Datensatz von Typ T und speichert ihn.
     * 
     * @param data
     * @return true, wenn der Datensatz erfolgreich gespeichert wurde, sonst false
     */
    abstract boolean create(T data);

    /**
     * Löscht einen Datensatz von Typ T.
     * 
     * @param data
     * @return true, wenn der Datensatz erfolgreich gelöscht wurde, sonst false
     */
    abstract boolean delete(T data);
}
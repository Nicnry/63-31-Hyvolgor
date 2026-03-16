package ch.hearc.ig.hyvolgor.datastructure;

/**
 * @author Nicolas Henry
 */
public interface List<E> extends Iterable<E> {

    /**
     * Retourne le nombre d'éléments stockés dans la liste.
     * @return Nombre d'éléments dans la liste.
     **/
    int size();

    /**
     * Retourne un booléen qui indique si la liste est vide.
     * @return True si la liste est vide.
     **/
    boolean isEmpty();

    /**
     * Retourne l'élément à l'indice i.
     * @param i Indice
     * @return E
     **/
    E get(int i) throws IndexOutOfBoundsException;

    /**
     * Remplace l'élément à l'indice i par une instance de element et renvoie l'élément remplacé.
     * @param i Indice
     * @param element
     * @return E
     **/
    E set(int i, E element);

    /**
     * Insère un élément de type element à l'indice i, en décalant les éléments suivants d'un indice.
     * @param i Indice ou l'element est ajoutée.
     * @param element
     **/
    void add(int i, E element);

    void add(E element);

    /**
     * Supprime et renvoie l'élément à l'indice i, en décalant les éléments suivants.
     * @param i indice
     * @return Instance de l'élément supprimée
     **/
    E remove(int i);

}

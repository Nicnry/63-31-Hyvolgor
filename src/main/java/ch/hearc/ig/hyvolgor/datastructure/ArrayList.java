package ch.hearc.ig.hyvolgor.datastructure;

import java.util.Iterator;

/**
 * @author Nicolas Henry
 * ArrayList contenant des intances de taille fixe.
 */
public class ArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY=10;

    private E[] elements; // Tableau pour stocker des elements
    private int size = 0; // Nombre d'éléments

    // Constructeurs

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructeur de capacité donnée.
     * @param capacity Capactité du tableau
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        elements = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return elements[i];
    }

    @Override
    public E set(int i, E element) {
        checkIndex(i, size);
        E temp = elements[i];
        elements[i] = element;
        return temp;
    }

    @Override
    public void add(int i, E element) {

        // Vérifie la validité de l'index
        // Une arraylist stocke des éléments contigus, cette méthode permet d'éliminer une insertion à un index trop lointain
        this.checkIndex(i, this.size + 1);

        // Vérifie si le tableau à encore de la place
        this.checkCapacity();

        for(int j = this.size-1; j >= i; j--){ // Déplacement des éléments vers la droite, le plus à droite en 1er.
            this.elements[j+1] = this.elements[j];
        }

        this.elements[i] = element; // Place libre pour le nouvel élément

        this.size++;

    }

    @Override
    public void add(E element) {
        this.add(size, element);
    }

    @Override
    public E remove(int i) {
        checkIndex(i, size);

        E temp = elements[i];

        for(int j=i; j < size-1;j++){ // Décale les éléments vers la gauche pour remplir le trou
            elements[j] = elements[j+1];
        }

        elements[this.size-1] = null; // candidat au garbage collector
        this.size--;

        return temp;
    }

    // Utilitaires

    /** Vérifie si l'indice donné est dans la plage [0, n].
     IndexOutOfBoundsException pour signaler un indice non valide en argument. */
    protected void checkIndex(int i, int n) {
        if (i < 0 || i >= n)
            throw new IndexOutOfBoundsException("Illegal index: " + i);
    }

    /** Vérifie si le tableau à de la place disponible. Sinon l'augmente */
    protected void checkCapacity(){
        if (this.size() == elements.length) {
            E[] newElements = (E[]) new Object[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator<>(this);
    }

}

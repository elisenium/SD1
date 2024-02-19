import java.util.NoSuchElementException;

public interface Deque<E> {

    /**
     * renvoie le nombre d'elements qui se trouvent dans la decque
     * @return nombre d'elements
     */
    public int size();


    /**
     * verifie si la decque est vide
     * @return true si la decque est vide, false sinon
     */
    public boolean isEmpty();


    /**
     * insère l’élément au début de la decque
     * @throws NullPointerException si l’element est nul
     */
    public void addFirst(E e) throws NullPointerException;


    /**
     * ajoute un element en fin de la decque
     * @throws NullPointerException si l’element est nul
     */
    public void addLast(E e) throws NullPointerException;


    /**
     * recupere et supprime le premier element de la deque
     * @return la tete de la decque
     * @throws NoSuchElementException si la decque est vide
     */
    public E removeFirst() throws NoSuchElementException;


    /**
     * recupere et supprime le dernier element de la deque
     * @return la queue de la decque
     * @throws NoSuchElementException si la decque est vide
     */
    public E removeLast() throws NoSuchElementException;


    /**
     * recupere la tete de la decque
     * @return la tete de la decque
     */
    public E getFirst();


    /**
     * recupere la tete de la decque
     * @return la tete de la decque
     */
    public E getLast();

}

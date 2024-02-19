import java.util.NoSuchElementException;

public class DequeImpl<E> implements Deque<E> {
    private Object[] deque;

    private int taille;
    private E tete;
    private E queue;

    public DequeImpl(int capacite) {
        deque = new Object[capacite];
        tete = null;
        queue = null;
        taille = 0;
    }

    public DequeImpl() {
        this(4);
    }

    @Override
    public int size() {
        return taille;
    }

    @Override
    public boolean isEmpty() {
        return taille == 0;
    }

    @Override
    public void addFirst(E e) throws NullPointerException {
        if (e == null || equals(""))
            throw new NullPointerException();

        if (isEmpty()) {
            tete = e;
            queue = e;

        } else {
            if (size() == deque.length) {
                Object[] temp = new Object[deque.length * 2];
                for (int i = 0; i < deque.length; i++) {
                    temp[i] = deque[i];
                }
                deque = temp;
            }
            for (int i = size(); i > 0; i--) {
                deque[i] = deque[i - 1];
            }
            tete = e;
        }
        deque[0] = e;
        taille++;
    }

    @Override
    public void addLast(E e) throws NullPointerException {
        if (e == null || equals(""))
            throw new NullPointerException();

        if (isEmpty()) {
            tete = e;
            queue = e;
        } else {
            if (taille == deque.length) {
                Object[] temp = new Object[deque.length * 2];
                for (int i = 0; i < deque.length; i++) {
                    temp[i] = deque[i];
                }
                deque = temp;
            }
            queue = e;
        }
        deque[taille] = e;
        taille++;
    }

    @Override
    public E removeFirst() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        E temp = tete;

        if (taille > 1) {
            for (int i = 0; i < taille - 1; i++) {
                deque[i] = deque[i + 1];
            }
            tete = (E) deque[0];
        }
        taille--;

        return temp;
    }


    @Override
    public E removeLast() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        E temp = queue;
        if (taille == 1) {
            deque[0] = null;
            tete = null;
        }

        if (taille > 1) {
            queue = (E) deque[taille-2];


        } else {
            tete = null;
            queue = null;
        }
        taille--;


        return temp;
    }

    @Override
    public E getFirst() throws NullPointerException {
        if (isEmpty()) throw new NullPointerException() ;
        return tete;
    }

    @Override
    public E getLast() throws NullPointerException {
        if (isEmpty()) throw new NullPointerException() ;
        return queue;
    }


    public String toString() {
        String strDeque = "";
        if (isEmpty() || deque[0] == null)
            return strDeque;
        if (deque == null && taille == 0) {
            return strDeque;
        }
        else {

            for (int i = 0; i < taille; i++) {
                if (i < deque.length) {
                    strDeque += " " + deque[i];
                }
            }
        }
        return strDeque;
    }

}

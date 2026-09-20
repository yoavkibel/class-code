package edu.unc.comp210.List;

/**
 * A simple list of elements stored in order and accessed by index.
 *
 * Indices start at 0. Methods that take an index throw an
 * IndexOutOfBoundsException when the index is outside the range
 * listed for that method.
 *
 * @param <E> the type of element stored in the list
 */
public interface MyList<E> {

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements, 0 if the list is empty
     */
    int size();

    /**
     * Adds an element to the end of the list.
     *
     * @param elem the element to add
     * @return true once the element has been added
     */
    boolean add(E elem);

    /**
     * Inserts an element at the given index. The element already at that
     * index, and every element after it, moves one position to the right.
     *
     * @param idx  the position to insert at, from 0 to size() inclusive
     *             (size() adds to the end)
     * @param elem the element to insert
     * @throws IndexOutOfBoundsException if idx < 0 or idx > size()
     */
    void add(int idx, E elem);

    /**
     * Removes the element at the given index. Every element after it moves
     * one position to the left.
     *
     * @param idx the position to remove, from 0 to size() - 1
     * @return the element that was removed
     * @throws IndexOutOfBoundsException if idx < 0 or idx >= size()
     */
    E remove(int idx);

    /**
     * Returns the element at the given index without changing the list.
     *
     * @param idx the position to read, from 0 to size() - 1
     * @return the element at that position
     * @throws IndexOutOfBoundsException if idx < 0 or idx >= size()
     */
    E get(int idx);

    /**
     * Returns the index of the first element equal to elem, compared with
     * equals().
     *
     * @param elem the element to search for
     * @return the index of the first match, or -1 if elem is not in the list
     */
    int indexOf(E elem);
}

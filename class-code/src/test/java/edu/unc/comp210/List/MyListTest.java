package edu.unc.comp210.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for any implementation of MyList.
 *
 * This class is abstract so the same tests can run against every
 * implementation. To test a new implementation, extend this class and
 * return a new, empty instance from createList(). See MyArrayListTest.
 */
public abstract class MyListTest {

    private MyList<String> list;

    /** Returns a new, empty list of the implementation under test. */
    protected abstract MyList<String> createList();


    @BeforeEach
    void setUp() {
        list = createList();
    }

    // ----- size -----

    @Test
    @DisplayName("size: a new list is empty")
    void sizeOfNewListIsZero() {
        assertEquals(0, list.size());
    }

    @Test
    @DisplayName("size: goes up by one with each add")
    void sizeGrowsWithAdds() {
        list.add("Waddy");
        assertEquals(1, list.size());
        list.add("Kali");
        assertEquals(2, list.size());
        list.add(0, "Ziggy");
        assertEquals(3, list.size());
    }

    @Test
    @DisplayName("size: goes down by one with each remove")
    void sizeShrinksWithRemoves() {
        addAll("Waddy", "Kali", "Ziggy");
        list.remove(0);
        assertEquals(2, list.size());
        list.remove(1);
        assertEquals(1, list.size());
        list.remove(0);
        assertEquals(0, list.size());
    }

    // ----- add(E elem) -----

    @Test
    @DisplayName("add(elem): returns true")
    void addReturnsTrue() {
        assertTrue(list.add("Waddy"));
    }

    @Test
    @DisplayName("add(elem): appends to the end, keeping order")
    void addAppendsInOrder() {
        addAll("Waddy", "Kali", "Ziggy");
        assertContents("Waddy", "Kali", "Ziggy");
    }

    @Test
    @DisplayName("add(elem): allows duplicates")
    void addAllowsDuplicates() {
        addAll("Kali", "Kali");
        assertContents("Kali", "Kali");
    }

    @Test
    @DisplayName("add(elem): keeps working past any starting capacity")
    void addManyElements() {
        for (int i = 0; i < 1000; i++) {
            list.add("item" + i);
        }
        assertEquals(1000, list.size());
        for (int i = 0; i < 1000; i++) {
            assertEquals("item" + i, list.get(i));
        }
    }

    // ----- add(int idx, E elem) -----

    @Test
    @DisplayName("add(idx, elem): index 0 on an empty list")
    void addAtIndexIntoEmptyList() {
        list.add(0, "Waddy");
        assertContents("Waddy");
    }

    @Test
    @DisplayName("add(idx, elem): at the front shifts everything right")
    void addAtFront() {
        addAll("Kali", "Ziggy");
        list.add(0, "Waddy");
        assertContents("Waddy", "Kali", "Ziggy");
    }

    @Test
    @DisplayName("add(idx, elem): in the middle shifts later elements right")
    void addInMiddle() {
        addAll("Waddy", "Ziggy");
        list.add(1, "Kali");
        assertContents("Waddy", "Kali", "Ziggy");
    }

    @Test
    @DisplayName("add(idx, elem): index size() adds to the end")
    void addAtEnd() {
        addAll("Waddy", "Kali");
        list.add(2, "Ziggy");
        assertContents("Waddy", "Kali", "Ziggy");
    }

    @Test
    @DisplayName("add(idx, elem): many front inserts keep correct order")
    void addManyAtFront() {
        for (int i = 0; i < 1000; i++) {
            list.add(0, "item" + i);
        }
        assertEquals(1000, list.size());
        assertEquals("item999", list.get(0));
        assertEquals("item0", list.get(999));
    }

    @Test
    @DisplayName("add(idx, elem): negative index throws")
    void addAtNegativeIndexThrows() {
        addAll("Waddy");
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "Kali"));
        assertContents("Waddy");
    }

    @Test
    @DisplayName("add(idx, elem): index past size() throws")
    void addPastEndThrows() {
        addAll("Waddy");
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(2, "Kali"));
        assertContents("Waddy");
    }

    @Test
    @DisplayName("add(idx, elem): index 1 on an empty list throws")
    void addAtOneOnEmptyThrows() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, "Kali"));
        assertEquals(0, list.size());
    }

    // ----- remove(int idx) -----

    @Test
    @DisplayName("remove: returns the removed element")
    void removeReturnsElement() {
        addAll("Waddy", "Kali", "Ziggy");
        assertEquals("Kali", list.remove(1));
    }

    @Test
    @DisplayName("remove: from the front shifts everything left")
    void removeFront() {
        addAll("Waddy", "Kali", "Ziggy");
        list.remove(0);
        assertContents("Kali", "Ziggy");
    }

    @Test
    @DisplayName("remove: from the middle shifts later elements left")
    void removeMiddle() {
        addAll("Waddy", "Kali", "Ziggy");
        list.remove(1);
        assertContents("Waddy", "Ziggy");
    }

    @Test
    @DisplayName("remove: the last element")
    void removeLast() {
        addAll("Waddy", "Kali", "Ziggy");
        assertEquals("Ziggy", list.remove(2));
        assertContents("Waddy", "Kali");
    }

    @Test
    @DisplayName("remove: the only element leaves an empty list")
    void removeOnlyElement() {
        addAll("Waddy");
        assertEquals("Waddy", list.remove(0));
        assertEquals(0, list.size());
    }

    @Test
    @DisplayName("remove: the list still works after removes")
    void addAfterRemove() {
        addAll("Waddy", "Kali");
        list.remove(0);
        list.add("Ziggy");
        list.add(0, "Doobi");
        assertContents("Doobi", "Kali", "Ziggy");
    }

    @Test
    @DisplayName("remove: on an empty list throws")
    void removeFromEmptyThrows() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    @DisplayName("remove: negative index throws")
    void removeNegativeThrows() {
        addAll("Waddy");
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertContents("Waddy");
    }

    @Test
    @DisplayName("remove: index size() throws")
    void removeAtSizeThrows() {
        addAll("Waddy", "Kali");
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(2));
        assertContents("Waddy", "Kali");
    }

    // ----- get(int idx) -----

    @Test
    @DisplayName("get: returns the element at each index")
    void getEachIndex() {
        addAll("Waddy", "Kali", "Ziggy");
        assertEquals("Waddy", list.get(0));
        assertEquals("Kali", list.get(1));
        assertEquals("Ziggy", list.get(2));
    }

    @Test
    @DisplayName("get: does not change the list")
    void getDoesNotModify() {
        addAll("Waddy", "Kali");
        list.get(0);
        list.get(1);
        assertContents("Waddy", "Kali");
    }

    @Test
    @DisplayName("get: on an empty list throws")
    void getFromEmptyThrows() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    @DisplayName("get: negative index throws")
    void getNegativeThrows() {
        addAll("Waddy");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    @DisplayName("get: index size() throws")
    void getAtSizeThrows() {
        addAll("Waddy", "Kali");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
    }

    @Test
    @DisplayName("get: index of a removed slot throws")
    void getAfterRemoveThrows() {
        addAll("Waddy", "Kali");
        list.remove(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    // ----- indexOf(E elem) -----

    @Test
    @DisplayName("indexOf: finds elements at the front, middle, and end")
    void indexOfFindsElements() {
        addAll("Waddy", "Kali", "Ziggy");
        assertEquals(0, list.indexOf("Waddy"));
        assertEquals(1, list.indexOf("Kali"));
        assertEquals(2, list.indexOf("Ziggy"));
    }

    @Test
    @DisplayName("indexOf: returns -1 when the element is missing")
    void indexOfMissing() {
        addAll("Waddy", "Kali");
        assertEquals(-1, list.indexOf("Mimi"));
    }

    @Test
    @DisplayName("indexOf: returns -1 on an empty list")
    void indexOfEmpty() {
        assertEquals(-1, list.indexOf("Waddy"));
    }

    @Test
    @DisplayName("indexOf: returns the first match when there are duplicates")
    void indexOfFirstDuplicate() {
        addAll("Waddy", "Kali", "Ziggy", "Kali");
        assertEquals(1, list.indexOf("Kali"));
    }

    @Test
    @DisplayName("indexOf: compares with equals(), not ==")
    void indexOfUsesEquals() {
        addAll("Waddy", "Kali");
        // new String(...) makes a different object with the same contents
        assertEquals(1, list.indexOf(new String("Kali")));
    }

    @Test
    @DisplayName("indexOf: reflects removes")
    void indexOfAfterRemove() {
        addAll("Waddy", "Kali", "Ziggy");
        list.remove(0);
        assertEquals(-1, list.indexOf("Waddy"));
        assertEquals(1, list.indexOf("Ziggy"));
    }


    /** Adds each value to the end of the list, in order. */
    private void addAll(String... values) {
        for (String v : values) {
            list.add(v);
        }
    }

    /** Checks that the list holds exactly these values, in this order. */
    private void assertContents(String... expected) {
        assertEquals(expected.length, list.size(), "size");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], list.get(i), "element at index " + i);
        }
    }


}

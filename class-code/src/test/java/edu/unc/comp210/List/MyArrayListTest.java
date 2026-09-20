package edu.unc.comp210.List;

/**
 * Runs every test in MyListTest against MyArrayList.
 */
public class MyArrayListTest extends MyListTest {

    @Override
    protected MyList<String> createList() {
        return new MyArrayList<>();
    }
}

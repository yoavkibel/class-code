package edu.unc.comp210.List;

public class MyArrayListTest extends MyListTest{
    @Override
    protected MyList<String> createList() {
        return new MyArrayList<>();
    }
}

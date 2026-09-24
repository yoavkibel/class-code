# MyArrayList Design Document

## What is it?
A flexible list that is type safe, but can grow and shrink in size based on the number of items in the list.

## Fields
E[] array
int size

## Invariants
Array should have enough space to hold all the items, all items should be typesafe (of the same kind), 
it should have only the items in the list and nothing extra.

Size - Non-negative, should keep track of the number of items in the list.


## Constructor
public MyArrayList(){
    this.array = new E[];
    this.size = 0;
}

## Methods

Size
add(e)
add(idx,e)
remove(idx)
get(idx)
indexOf(e)

## Testing
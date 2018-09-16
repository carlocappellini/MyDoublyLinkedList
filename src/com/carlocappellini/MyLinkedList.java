package com.carlocappellini;

public class MyLinkedList implements NodeList {

    ListItem root = null;

    public MyLinkedList(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem newItem) {

        if (this.root == null) {
            this.root = newItem;
        }
        ListItem current = this.root;

//        7   / 6
        while (current != null) {

            int comparison = current.compareTo(newItem);
            if (comparison < 0) {
                //newItem is greater, move right
                if (current.next() != null) {
                    current = current.next();
                } else {
                    // current.next is null
                    current.setNext(newItem);
                    newItem.setPrevious(current);
                    return true;

                }

            } else if (comparison > 0) {
                // newItem is less / move left
                if (current.previous() != null) {
                    current.previous().setNext(newItem);
                    newItem.setPrevious(current.previous());
                    newItem.setNext(current);
                    current.setPrevious(newItem);
                } else {
                    // no left link
                    newItem.setNext(this.root);
                    this.root.setPrevious(newItem);
                    this.root = newItem;

                }
                return true;

            } else {
                System.out.println("Is already present, cannot add duplicates");
                return false;

            }

        }
        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        if (item != null) {
            System.out.println("Deleting item " + item.getValue());
        }

        ListItem currentItem = this.root;


        while (currentItem != null) {
            int comparison = currentItem.compareTo(item);
            if (comparison == 0) {
                if (currentItem == this.root) {
                    this.root = this.root.next();
                } else {
                    currentItem.previous().setNext(currentItem.next());

                    if (currentItem.next() != null) {
                        currentItem.next().setPrevious(currentItem.previous());

                    }
                }
                return true;
            } else if (comparison < 0) {

                currentItem = currentItem.next();
            } else {
                return false;
            }
        }
        return false;

    }

    @Override
    public void traverse(ListItem root) {
        if (root == null) {
            System.out.println("List is empty");
        } else {
            while (root != null) {
                System.out.println(root.getValue());
                root = root.next();

            }
        }

    }
}

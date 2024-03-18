package com.data_structure.binary_trees;

import java.util.Objects;

public class BinaryTreeService {

    private Node root;

    private Node addRecursive(Node current, int value) {
        // if node is null new a node
        if (current == null) {
            return new Node(value);
        }

        if (value < current.getValue()) {
            // call again method with left node (left node is null and above condition will create instant for node)
            current.setLeft(addRecursive(current.getLeft(), value));
        } else if (value > current.getValue()) {
            //the same above
            current.setRight(addRecursive(current.getRight(), value));
        } else {
            // value already exist
            return current;
        }
        return current;
    }

    public void add(int value){
        //add on root
        root = addRecursive(root , value);
    }

    public Node getHeadNode(){
        return root;
    }

    private boolean containsNodeRecursive(Node current, int value){
        if(Objects.isNull(current)){
            return false;
        }
        if(value == current.getValue()){
            return true;
        }

        // if value < current value then transmit left into method to perform again check until find result or nothing
        return value < current.getValue() ?
        containsNodeRecursive(current.getLeft(), value) :
        containsNodeRecursive(current.getRight(), value);
    }

    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }



    // no completed
    private Node deleteRecursive(Node current, int value){
        if(current == null){
            return null;
        }

        if(value == current.getValue()){
         //todo
        }

        if (value < current.getValue()) {
            current.setLeft(deleteRecursive(current.getLeft(), value));
            return current;
        }
        current.getRight().setRight(deleteRecursive(current.getRight(), value));
        return current;
    }

}

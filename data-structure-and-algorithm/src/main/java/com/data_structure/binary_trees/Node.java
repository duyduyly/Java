package com.data_structure.binary_trees;

import com.data_structure.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor@AllArgsConstructor
public class Node {
    private int value;

    //right greater than or equal left
    private Node right;

    //left less than or equal right
    private Node left;

    Node(int value){
        this.value = value;
        this.right = null;
        this.left = null;
    }
}

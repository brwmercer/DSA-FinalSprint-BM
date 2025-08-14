package org.example.bst;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BSTBuilderTest {
    @Test
    void buildsIterativeBST() {
        BSTBuilder b = new BSTBuilder();
        BSTBuilder.Node root = b.buildTree(Arrays.asList(10,5,15,3,7,12,18));

        assertNotNull(root);
        assertEquals(10, root.value);
        assertEquals(5, root.left.value);
        assertEquals(15, root.right.value);
        assertEquals(3, root.left.left.value);
        assertEquals(7, root.left.right.value);
        assertEquals(12, root.right.left.value);
        assertEquals(18, root.right.right.value);
    }
}

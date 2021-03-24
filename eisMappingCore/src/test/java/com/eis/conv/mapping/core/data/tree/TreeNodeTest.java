package com.eis.conv.mapping.core.data.tree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TreeNodeTest {

    @Test
    public void stringTest() {

        TreeNode<String> rootNode = new TreeNode<>("Root");
        TreeNode<String> firstNode = new TreeNode<>("Child 1 (under Root)", rootNode);
        TreeNode<String> secondNode = new TreeNode<>("Child 2 (under Root)", rootNode);
        TreeNode<String> thirdNode = new TreeNode<>("Child 3 (under Child 2)", secondNode);
        TreeNode<String> fourthNode = new TreeNode<>("Child 4 (under Child 3)", thirdNode);
        TreeNode<String> fifthNode = new TreeNode<>("Child 5 (under Root, but with a later call)");
        fifthNode.setParent(rootNode);

        //System.out.println(rootNode.toString());
        //System.out.println(firstNode.toString());
        //System.out.println(secondNode.toString());
        //System.out.println(thirdNode.toString());
        //System.out.println(fourthNode.toString());
        //System.out.println(fifthNode.toString());
        assertThat(rootNode.isRootNode()).isEqualTo(true);
        assertThat(firstNode.isRootNode()).isEqualTo(false);
        assertThat(thirdNode.isLeafNode()).isEqualTo(false);
        assertThat(fourthNode.isRootNode()).isEqualTo(false);
        assertThat(fifthNode.isLeafNode()).isEqualTo(true);
    }

}
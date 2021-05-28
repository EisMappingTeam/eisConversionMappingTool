package com.eis.conv.mapping.core.data.tree;

import java.util.ArrayList;
import java.util.List;

//https://stackoverflow.com/questions/19330731/tree-implementation-in-java-root-parents-and-children
public class TreeNode<T> {
    private final List<TreeNode<T>> children;
    private TreeNode<T> parent;
    private T data;
    private int depth;

    public TreeNode(T data) {
        // a fresh node, without a parent reference
        this.children = new ArrayList<>();
        this.parent = null;
        this.data = data;
        this.depth = 0; // 0 is the base level (only the root should be on there)
    }

    public TreeNode(T data, TreeNode<T> parent) {
        // new node with a given parent
        this.children = new ArrayList<>();
        this.data = data;
        this.parent = parent;
        this.depth = (parent.getDepth() + 1);
        parent.addChild(this);
    }

    public int getDepth() {
        return this.depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void setParent(TreeNode<T> parent) {
        this.setDepth(parent.getDepth() + 1);
        parent.addChild(this);
        this.parent = parent;
    }

    public TreeNode<T> getParent() {
        return this.parent;
    }

    public void addChild(T data) {
        TreeNode<T> child = new TreeNode<>(data);
        this.children.add(child);
    }

    public void addChild(TreeNode<T> child) {
        this.children.add(child);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isRootNode() {
        return (this.parent == null);
    }

    public boolean isLeafNode() {
        return (this.children.size() == 0);
    }

    public void removeParent() {
        this.parent = null;
    }

    @Override
    public String toString() {
        String out = "";
        out += "Node: " + this.getData().toString() + " | Depth: " + this.depth + " | Parent: " + (this.getParent() == null ? "None" : this.parent.getData().toString()) + " | Children: " + (this.getChildren().size() == 0 ? "None" : "");
        for (TreeNode<T> child : this.getChildren()) {
            out += "\n\t" + child.getData().toString() + " | Parent: " + (child.getParent() == null ? "None" : child.getParent().getData());
        }
        return out;
    }
}
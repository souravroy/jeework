package com.wcs.base.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenericTreeNode<T> {

    private T data;
    private GenericTreeNode<T> parent;
    private List<GenericTreeNode<T>> children;

	public GenericTreeNode() {
        super();
        children = new ArrayList<GenericTreeNode<T>>();
    }

    public GenericTreeNode(T data) {
        this();
        setData(data);
    }
    
	public GenericTreeNode(T data, GenericTreeNode<T> parent) {
		this.setData(data);
		children = new ArrayList<GenericTreeNode<T>>();
		this.parent = parent;
		if(this.parent != null)
			this.parent.getChildren().add(this);
	}

    public GenericTreeNode<T> getParent() {
        return this.parent;
    }
    
	public void setParent(GenericTreeNode<T> parent) {
        if(this.parent != null) {
            this.parent.getChildren().remove(this);
        }
        
        this.parent = parent;
        
        if(this.parent != null) {
            this.parent.getChildren().add(this);
        }
	}    

    public List<GenericTreeNode<T>> getChildren() {
        return this.children;
    }

    public int getChildCount() {
        return getChildren().size();
    }

    public boolean hasChildren() {
        return (getChildCount() > 0);
    }

    public void setChildren(List<GenericTreeNode<T>> children) {
        for(GenericTreeNode<T> child : children) {
           child.parent = this;
        }

        this.children = children;
    }

    public void addChild(GenericTreeNode<T> child) {
        child.parent = this;
        children.add(child);
    }

    public void addChildAt(int index, GenericTreeNode<T> child) throws IndexOutOfBoundsException {
        child.parent = this;
        children.add(index, child);
    }

    public void removeChildren() {
        this.children = new ArrayList<GenericTreeNode<T>>();
    }

    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        children.remove(index);
    }

    public GenericTreeNode<T> getChildAt(int index) throws IndexOutOfBoundsException {
        return children.get(index);
    }
    
	public boolean isLeaf() {
		if(children == null)
			return true;
		
		return children.isEmpty();
	}

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
		if(data != null)
			return data.toString();
		else
			return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
           return true;
        }
        if (obj == null) {
           return false;
        }
        if (getClass() != obj.getClass()) {
           return false;
        }
        GenericTreeNode<?> other = (GenericTreeNode<?>) obj;
        if (data == null) {
           if (other.data != null) {
              return false;
           }
        } else if (!data.equals(other.data)) {
           return false;
        }
        return true;
    }

    /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
    @Override
    public int hashCode() {
       final int prime = 31;
       int result = 1;
       result = prime * result + ((data == null) ? 0 : data.hashCode());
       return result;
    }

    public String toStringVerbose() {
        String stringRepresentation = getData().toString() + ":[";

        for (GenericTreeNode<T> node : getChildren()) {
            stringRepresentation += node.getData().toString() + ", ";
        }

        //Pattern.DOTALL causes ^ and $ to match. Otherwise it won't. It's retarded.
        Pattern pattern = Pattern.compile(", $", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(stringRepresentation);

        stringRepresentation = matcher.replaceFirst("");
        stringRepresentation += "]";

        return stringRepresentation;
    }
}
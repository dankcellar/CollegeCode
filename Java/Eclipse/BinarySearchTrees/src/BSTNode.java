
public class BSTNode 
{
	BSTNode m_objLeftNode, m_objRightNode;
	BSTNode parent;
	int m_nKeyValue;
	int N;
	
	public BSTNode()
	{
		// Explicit set to null may not be necessary,
		//   but provided for clarity.
		m_objLeftNode = m_objRightNode = null;
		parent = null;
		
		// Set this node's key value to default of 0.
		m_nKeyValue = 0;
	}

	public BSTNode(int nKeyValue)
	{
		// Explicit set to null may not be necessary,
		//   but provided for clarity.
		m_objLeftNode = m_objRightNode = null;
		parent = null;
		
		// Set this node's key value
		m_nKeyValue = nKeyValue;
	}

	// Accessor method to set the left node.
	public void SetLeftNode( BSTNode objLeftNode)
	{
		// Assign the left node object reference.
		m_objLeftNode = objLeftNode;
	}
	
	// Accessor method to set the right node.
	public void SetRightNode( BSTNode objRightNode)
	{
		// Assign the right node object reference.
		m_objRightNode = objRightNode;
	}
	
	// Accessor method to get the left node object.
	public BSTNode GetLeftNode()
	{
		// Return the object.
		return( m_objLeftNode );
	}
	
	// Accessor method to get the right node object.
	public BSTNode GetRightNode()
	{
		// Return the object.
		return( m_objRightNode );
	}

	// Accessor method to set the node's key value.
	public void SetKeyValue( int nKeyValue )
	{
		// Set the value.
		m_nKeyValue = nKeyValue;
	}
	
	// Accessor method to get the node's key value.
	public int GetKeyValue()
	{
		// Return the value.
		return( m_nKeyValue );
	}
	
	// Accessor method to get the parent node object.
	public BSTNode getParent() 
	{		
		// Return the object.
		return parent;
	}
	
	// Accessor method to set the parent node.	
	public void setParent(BSTNode node) 
	{
		// Assign the parent node object reference.
		parent = node;
	}
	
	// Get sub tree size
	public int getSubtreeSize()
	{  			
		return getSubtreeSize(this);
	}
	
	// Get sub tree size
	public int getSubtreeSize(BSTNode node)
	{  	
		if (node == null) {
			return 0;
		} else {
			return 1 + getSubtreeSize(node.GetLeftNode()) + getSubtreeSize(node.GetRightNode());
		}
	}
	
    public BSTNode getRootNode() 
    {   
    	if (getParent() != null)
    	{
    		BSTNode node = getParent();
    		while(node.getParent() != null)
    		{
    			node = node.getParent();
    		}
    		return node;
    	} else 
    	{
    		BSTNode temp = GetLeftNode();
    		BSTNode node = temp.getParent(); 
    		return node;
    	}    	
    }
}

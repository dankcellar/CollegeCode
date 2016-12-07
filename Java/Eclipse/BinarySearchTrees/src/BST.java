
public class BST 
{
	// This is the m_objRootNode node, which starts off as null
	//   when the BST is empty.
	BSTNode m_objRootNode;
	
	// Class constructor.
	public BST()
	{
		// Not really necessary, provided for clarity.
		m_objRootNode = null;
	}

	// Method to see if the tree is empty.
	public boolean IsEmpty()
	{
		// Return a boolean indicating whether the
		//   three is empty or not.
		return( m_objRootNode == null );
	}

	/* Functions to search for an element */
    public BSTNode Search( int nKeyValue )
    {
        return( Search( m_objRootNode, nKeyValue ) );
    }
    
    // Method to search for an element recursively.
    private BSTNode Search( BSTNode objNode, int nKeyValue )
    {
    	
    	if( objNode == null )
    	{
    		return( null );
    	}
    	
    	// First, we get the key value for this node.
    	int nThisKeyValue = objNode.GetKeyValue();
            
    	// See if the passed in key value is less. If so,
    	//   this indicates that we need to go left.
    	if( nKeyValue < nThisKeyValue )
    	{
    		// Get the left node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetLeftNode();
    	}
            
    	// See if the passed in key value is greater. If so,
    	//   this indicates that we need to go right.
    	else if( nKeyValue > nThisKeyValue )
    	{
    		// Get the right node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetRightNode();
    	}

    	// Here we have found the node with the key
    	//   value that was passed in.
    	else
    	{
    		return( objNode );
    	}
            
    	// Now call Search recursively.
    	return( Search( objNode, nKeyValue ) );
	}
    
    // This method inserts a node based on the key value.
    public void Insert( int nKeyValue ) 
    {
    	BSTNode x, y;
    	BSTNode objNode;
    	objNode = Search(nKeyValue);
    	x = y = m_objRootNode; 
    	
        if( objNode == null )
        {
        	objNode = new BSTNode( nKeyValue );
        }
        
    	while (x != null)
    	{
    		if (x.GetKeyValue() > nKeyValue)
    		{
    			y = x;
    			x = x.GetLeftNode();
    		}
    		else
    		{
    			y = x;
    			x = x.GetRightNode();
    		}
    	}
  
    	// y will be the parent of node
    	if (y == null) 
    	{
    		m_objRootNode = objNode;
    		return;
    	}
  
    	if (y.GetKeyValue() > nKeyValue)
    		y.SetLeftNode(objNode);
    	else
    		y.SetRightNode(objNode);
  
    	objNode.setParent(y);    
    }    

    // Class protected (internal) method to insert nodes. This method
    //   will be called recursively.
    protected void Insert( int nKeyValue, BSTNode objNode ) 
    {    	    	    	    	    	    	    	   
    	BSTNode x, y;       
    	x = y = m_objRootNode; 
        
    	while (x != null)
    	{
    		if (x.GetKeyValue() > objNode.GetKeyValue())
    		{
    			y = x;
    			x = x.GetLeftNode();
    		}
    		else
    		{
    			y = x;
    			x = x.GetRightNode();
    		}
    	}
  
    	// y will be the parent of node
    	if (y == null) 
    	{
    		m_objRootNode = objNode;
    		return;
    	}
  
    	if (y.GetKeyValue() > objNode.GetKeyValue())
    		y.SetLeftNode(objNode);
    	else
    		y.SetRightNode(objNode);
  
    	objNode.setParent(y);
    }
    
    // Delete function
    public void Delete(int nKeyValue)
    {
    	BSTNode ds = Search(nKeyValue);
    	Delete(ds);
    }
    
    // Delete function
    public void Delete(BSTNode ds)
    {
        // Case 1: node does not have a child
        if (ds.GetLeftNode() == null && ds.GetRightNode() == null)
        {
            if (ds.getParent() != null && ds.getParent().GetLeftNode() == ds)
                ds.SetLeftNode(null);
            else if (ds.getParent() != null && ds.getParent().GetRightNode() == ds)
                ds.SetRightNode(null);            
        }
        // Case 2: node has only one child
        else if (ds.GetLeftNode() == null || ds.GetRightNode() == null)
        {
            if (ds.getParent() != null)
            {
                BSTNode x = ds.GetLeftNode() == null ? ds.GetRightNode() : ds.GetLeftNode();
                if (ds.getParent().GetLeftNode() == ds)
                    ds.getParent().SetLeftNode(x);
                else
                    ds.getParent().SetRightNode(x);
            }            
        }
        // Case 3: node has both children
        else
        {
        	if (ds == m_objRootNode)
        	{
        		ds.SetKeyValue((findMinimum(ds.GetRightNode())).GetKeyValue());
        		BSTNode temp;
        		temp = findMinimum(m_objRootNode.GetRightNode()).getParent();
        		temp.SetLeftNode(null);
        		return;
        	}
            BSTNode x = findSuccessor(ds);
            ds.SetKeyValue(x.GetKeyValue());
            BSTNode nodeChild = x.GetLeftNode() == null ? x.GetRightNode() : x.GetLeftNode();
            if (x.GetLeftNode() != null)
            {
                if (x.getParent().GetLeftNode() == x)
                    x.getParent().SetLeftNode(nodeChild);
                else
                    x.getParent().SetRightNode(nodeChild);
            }
            else
            {
                if (x.getParent().GetLeftNode() == x)
                    x.getParent().SetLeftNode(nodeChild);
                else
                    x.getParent().SetRightNode(nodeChild);
            }
        }
    }
    
    // Find next key
    public static BSTNode findSuccessor(BSTNode node)
    {
        if (node == null)
            return null;
        
        if (node.GetRightNode() != null)
            return findMinimum(node.GetRightNode());
       
        BSTNode y = node.getParent();
        BSTNode x = node;
        while (y != null && x == y.GetRightNode())
        {
            x = y;
            y = y.getParent();
        }

        return y;
    }
    
    // Find next key
    public BSTNode findPredecessor (BSTNode node) 
    {
    	if (node == null)
            return null;
        
        if (node.GetLeftNode() != null)
            return findMaximum(node.GetLeftNode());
       
        BSTNode y = node.getParent();
        BSTNode x = node;
        while (y != null && x == y.GetLeftNode())
        {
            x = y;
            y = y.getParent();
        }

        return y;
	}
	
    // Get min node based off a given root
    public static BSTNode findMinimum(BSTNode root)
    {
        if (root == null)
            return null;
      
        if (root.GetLeftNode() != null)
            return findMinimum(root.GetLeftNode());
      
        return root;
    }
    
    // Get max node based off a given root
    public static BSTNode findMaximum(BSTNode root)
    {
        if (root == null)
            return null;
       
        if (root.GetRightNode() != null)
            return findMaximum(root.GetRightNode());
       
        return root;
    }

	// Find the root node
    public BSTNode getRootNode() 
    {   
    	return m_objRootNode;
    }

    // Get the minimum value
    public int getMin()
    {
    	
    	if (m_objRootNode.GetLeftNode() == null) 
            return m_objRootNode.GetKeyValue();
    	 
    	BSTNode tempNode = m_objRootNode;
    	     
        while (tempNode.GetLeftNode() != null) {
        	tempNode = tempNode.GetLeftNode();
        }
        return tempNode.GetKeyValue();
    }

    // Get the maximum value
    public int getMax()
    {
    	if (m_objRootNode.GetRightNode() == null) 
            return m_objRootNode.GetKeyValue();
    	 
    	BSTNode tempNode = m_objRootNode;
    	     
        while (tempNode.GetRightNode() != null) {
        	tempNode = tempNode.GetRightNode();
        }
        return tempNode.GetKeyValue();
    }
    
    // Get the rank of the node
    public int getRank(BSTNode ds)
    {
    	int i = ds.GetKeyValue();
    	return getRank(i);
    }
    
    // Get the rank of the node
    public int getRank (int nValue)
    {
        BSTNode node = Search(nValue);
        int i = 0;
        if (node.GetKeyValue() == nValue) i++;
        while (node != null) {
            node = findPredecessor(node); i++;
        }
        return i;
    }
}

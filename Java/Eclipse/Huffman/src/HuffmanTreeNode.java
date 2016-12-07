@SuppressWarnings("rawtypes")

public class HuffmanTreeNode implements Comparable
{
	public String letter; 
	public Double frequency;
	public HuffmanTreeNode left, right;

	public HuffmanTreeNode (String letter, Double frequency)
	{	   
		this.letter = letter;
		this.frequency = frequency;
		this.left = null;
		this.right = null;
	}	
	
	public HuffmanTreeNode(HuffmanTreeNode left, HuffmanTreeNode right)
	{
		this.letter = left.letter + right.letter;
		this.frequency = left.frequency + right.frequency;
		this.left = null;
		this.right = null;
	}	
	
	public String toString()
	{
		String nodeString = "<" + this.letter + "," + this.frequency + ">";
		return nodeString;
	}

	public int compareTo(Object o)
	{
		HuffmanTreeNode huff = (HuffmanTreeNode) o;
		return this.frequency.compareTo(huff.frequency);
	}
  
}
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Huffman
{ 
	public static final int NUMBER_OF_CHARACTERS = 256;
	private String contents;
	private HuffmanTree huffmanTree;
	private int compressionTable[];
	private String code[];
	private int uniqueChars = 0;
	private static FileInputStream f;

	public Huffman(String input)
  	{
		this.contents = input;
		this.compressionTable = new int[NUMBER_OF_CHARACTERS];
		this.code = new String[NUMBER_OF_CHARACTERS];
  	} 

  	public void recordFrequencies()
  	{	
  		char[] contentsChars = this.contents.toCharArray();
  		char ch; 
	
  		for ( int i = 0; i < contentsChars.length; i++ ) 
  		{
  			ch = contentsChars[i]; 
  			if((int)ch < this.compressionTable.length)
  				this.compressionTable[(int) ch]++; 
  		}
  		
  		for(int j = 0; j < compressionTable.length; j++)
  		{
  			if(compressionTable[j] != 0)
  				this.uniqueChars++;
  		}
  	} 

	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	public void frequenciesToTree()     
	{
		HuffmanTreeNode[] letterNodes = new HuffmanTreeNode[this.uniqueChars];
		int i = 0;
		int nodeIdx = 0;
		while(i < compressionTable.length)
		{  
			if(this.compressionTable[i] != 0)
	  		{
			 	char littleChar = (char) i;
			 	Character charObject = (Character) littleChar;
			 	String letter = charObject.toString();
			 	double littlecompressionTable = (double) compressionTable[i];
			 	Double freq = (Double) littlecompressionTable;
			 	letterNodes[nodeIdx] = new HuffmanTreeNode(letter,freq);
			 	nodeIdx++;
	  		}
	  		i++;
		}

		BinaryHeap theHeap = new BinaryHeap (letterNodes);
		this.huffmanTree = huffmanTree.createFromHeap(theHeap);
	}

  	public void treeToCode()  
  	{
  		int i = 0;
  		while(i < this.code.length)
  		{
  			this.code[i] = "";
  			i++;
  		}
  		this.treeToCode(this.huffmanTree.root, this.code[0]);	
  	} 
 
  	private void treeToCode(HuffmanTreeNode t, String s)
  	{
  		 char c = 0;
		 if(t.letter.length() > 1)
		 {	 
			 
			 this.treeToCode(t.left,s + "0");
		 	 this.treeToCode(t.right, s + "1");
		 }
		 else
			 c = t.letter.charAt(0);
		 	 int index = (int) c;
			 this.code[index] = s;
	 } 
 
  	public String encodeMessage()     
  	{
  		String message = "";
  		String[] encodedMessage = new String[this.contents.length()];
  		int i = 0;
  		while(i < this.contents.length())
  		{
  			int x =(int) this.contents.charAt(i);
  			encodedMessage[i] = code[x];
  			message = message + encodedMessage[i];
  			i++;
  		}
	  return message;
  	}
 
  	private static String readContents(String filePath) throws java.io.IOException
  	{
  		byte[] buffer = new byte[(int) new File(filePath).length()];
  		f = new FileInputStream(filePath);
  		f.read(buffer);
  		return new String(buffer);    
  	} 

  	public String decodeMessage(String encodedStr) 
  	{
  		int idx = 0;
  		HuffmanTreeNode n = this.huffmanTree.root;
		String outStr = " ";
		return treeTraversal(encodedStr,n,idx,outStr);
	 } 
	
	 private String treeTraversal(String str,HuffmanTreeNode n,int idx,String outStr)
	 {
		
		 char[] test = str.toCharArray();
		 if(n.letter.length() > 1 && idx < str.length())
		 {		
			 if(test[idx] == '0')
			 {
				 n = n.left;
				 idx++;
				 return this.treeTraversal(str,n,idx,outStr);
			 }
			 else if(test[idx] == '1')
			 {
				 n=n.right;
				 idx++;
				 return this.treeTraversal(str, n,idx ,outStr);
			 }
		 }		 

		 if(idx < str.length())
		 {
			 outStr = outStr.concat(n.letter);
			 n = this.huffmanTree.root;
			 return this.treeTraversal(str, n, idx,outStr);
		 }
		 else 
			 return outStr;		
	 }
		 
	public static void main(String args[])
	{
		final String filename = args[0];
		String theContents = null;
		try 
		{
			theContents = readContents(filename);
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}	
			 
		Huffman converter = new Huffman(theContents);
		converter.recordFrequencies();
		converter.frequenciesToTree();
		converter.treeToCode();
		String encodedMessage = converter.encodeMessage();
		System.out.println(converter.decodeMessage(encodedMessage));
		System.out.println(encodedMessage);		
	}
	
}

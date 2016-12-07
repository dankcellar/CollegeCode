import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class HuffmanTree 
{
	HuffmanTreeNode root;
	private static FileInputStream f;
	  
	public HuffmanTree(HuffmanTreeNode huff)
	{
		this.root = huff;	 
	 
	} 

	public void printLegend()
	{
	
		this.printLegend(root, ""); 
	} 
	 
	private void printLegend(HuffmanTreeNode t, String s)
	{
		if(t.letter.length() > 1)
		{	
			this.printLegend(t.left,s + "0");
			this.printLegend(t.right, s + "1");
		}
		else
			System.out.print(t.letter + "=" + s + " ");
		 
	} 
	
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public static BinaryHeap fileToHeap(String filename) throws IOException 
	{

		File file = new File(filename);
		Object[] inputStream = null;
		String fileContent = null;
		try {
			fileContent = 	readFileAsString(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ArrayList letterFrequency = new ArrayList();
		StringTokenizer tokens = new StringTokenizer(fileContent);
		while(tokens.hasMoreTokens())
		{
			letterFrequency.add(tokens.nextElement());	 
		}
			 
		Object[] letterFreqs = letterFrequency.toArray();
		
		HuffmanTreeNode[] letterNodes = new HuffmanTreeNode[letterFreqs.length/2];
		int id = 0;
		int nodeIdx = 0;
		while(id < letterFreqs.length)
		{
			String letter = (String) letterFreqs[id];
			String f = (String) letterFreqs[id+1];
			double freq = Double.parseDouble(f);
			HuffmanTreeNode insert = new HuffmanTreeNode(letter,freq);
			letterNodes[nodeIdx] = insert;
			id = id+2;
			nodeIdx++;
		}
		
		BinaryHeap theHeap = new BinaryHeap (letterNodes);
		return theHeap;		 
	 } 
	 
	 private static String readFileAsString(String filePath) throws java.io.IOException
	 {
		 byte[] buffer = new byte[(int) new File(filePath).length()];
		 f = new FileInputStream(filePath);
		 f.read(buffer);
		 return new String(buffer);	    
	 } 
	 

	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 public static Object[] read(String input)
	 {
		 ArrayList arrList = new ArrayList();
		 int i = 0;
		 while (i <input.length() )
		 {
			 Character nextInput = input.charAt(i);
			 if (nextInput != null)
			 {  
				 arrList.add(nextInput);
			 } 
			 i++;
		 }  
		 Object[] inputArr = arrList.toArray();
		 return inputArr;
	  } 
		 
	@SuppressWarnings({ "unchecked","rawtypes" })
	public static HuffmanTree createFromHeap(BinaryHeap b) 
	{
		 HuffmanTreeNode huffRoot = null;
		 while(!b.isEmpty())
		{
			 huffRoot = (HuffmanTreeNode) b.deleteMin();
			 if(!b.isEmpty())
			 {
				 HuffmanTreeNode min2 = (HuffmanTreeNode) b.deleteMin();
				 HuffmanTreeNode merged = new HuffmanTreeNode(huffRoot,min2);
				 merged.left = min2;
				 merged.right = huffRoot;
				 b.insert((HuffmanTreeNode) merged);
			 } 
		 } 
		 return new HuffmanTree(huffRoot);	 
	 }	
}


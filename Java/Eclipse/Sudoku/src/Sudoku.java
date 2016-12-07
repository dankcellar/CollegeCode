import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sudoku extends Applet implements Runnable
{
	private static final long serialVersionUID = 1L;

	  static int width;  // Grid dimensions in pixels

	static int height;
	  static Graphics gr;  // global graphics
	  static int cursor=-1;  // if 0-80, position on 9x9 grid
	  
	  public void init() {
		    width=getBounds().width-3;  // Grid dimensions
		    height=getBounds().height*5/6-3;
		    setBackground(Color.WHITE);
		    gr=getGraphics();
			try{ LoadData(); } catch(Exception ex){}
	  }
	
	  
	final static int N = 9;
	static int Grid[][] = new int[9][9];
	
	static void LoadData() throws FileNotFoundException
	{
		// Open the file for reading. Will almost always be input.txt
		Scanner objScanner = new Scanner(new File("input.txt"));
		
		// Iterate through the rows.
		for( int nRow=0; nRow<N; nRow++ )
		{
			// Iterate through the columns.
			for( int nColumn=0; nColumn<N; nColumn++ )
			{
				// Read each value. Note that this is [row][col] but could be [col][row] in
				//   other contexts.
				Grid[nRow][nColumn] = objScanner.nextInt();
			}
		}
		// Closing is good practice and avoids an eclipse warning.
		objScanner.close();
	}
	
	/* Searches the grid to find an entry that is still unassigned. If
	   found, the reference parameters row, col will be set the location
	   that is unassigned, and true is returned. If no unassigned entries
	   remain, false is returned. */
	static int FindUnassignedLocation( int grid[][] )
	{
		  for( int row=0; row<N; row++ )
		  {
		    for( int col=0; col<N; col++ )
		    {
		      if( grid[row][col] == 0 )
		      {
		        return( col | ( row << 8 ) );
		      }
		    }
		  }

		return( -1 );
	}
	
	/* Returns a boolean which indicates whether any assigned entry
	   in the specified row matches the given number. */
	static boolean UsedInRow(int grid[][], int row, int num)
	{
		for( int col=0; col<9; col++ )
		{
			if( grid[row][col] == num ) return true;
		}
		
		return false;
	}
	 
	/* Returns a boolean which indicates whether any assigned entry
	   in the specified column matches the given number. */
	static boolean UsedInCol(int grid[][], int col, int num)
	{
		for( int row=0; row<9; row++ )
		{
			if( grid[row][col] == num ) return true;
		}

		return false;
	}
	 
	/* Returns a boolean which indicates whether any assigned entry
	   within the specified 3x3 box matches the given number. */
	static boolean UsedInBox( int grid[][], int boxStartRow, int boxStartCol, int num )
	{
		for( int row=boxStartRow; row<boxStartRow+3; row++ )
		{
			for( int col=boxStartCol; col<boxStartCol+3; col++ )
			{
				if( grid[row][col] == num ) return true;
			}
		}
		
		return false;
	}
	 
	/* Returns a boolean which indicates whether it will be legal to assign
	   num to the given row,col location. */
	static boolean IsPromising( int grid[][], int row, int col, int num )
	{
		if( !UsedInRow( grid, row, num ) &&
			!UsedInCol( grid, col, num ) &&
			!UsedInBox( grid, row-(row%3), col-(col%3), num ) )
		{
			return( true );
		}
		
		return false;
	}
	
	/* Takes a partially filled-in grid and attempts to assign values to
	  all unassigned locations in such a way to meet the requirements
	  for Sudoku solution (non-duplication across rows, columns, and boxes) */
	static boolean SolveSudoku(int grid[][])
	{
		int result = FindUnassignedLocation(grid);
		if( result == -1 ) return true;
		int row = result >> 8;
		int col = result & 0xff;
		
		for( int num=1; num<=9; num++ )
		{
			if( IsPromising( grid, row, col, num ) )
			{
				grid[row][col] = num;
				if( SolveSudoku( grid ) )
				{
					return true;
				}
				grid[row][col] = 0;
			}
		}

		return false; // this triggers backtracking
	}
	
	/* A utility function to print grid  */
	static void printGrid( int grid[][] )
	{
	    for (int row = 0; row < N; row++)
	    {
	       for (int col = 0; col < N; col++)
	             System.out.print(""+grid[row][col]+" ");
	        System.out.println("");;
	    }
	}	
	
	  // Draw the puzzle
	  public void paint(Graphics g) {

	    // Draw cursor
	    if (cursor>=0 && cursor<81) {
	      g.setColor(Color.YELLOW);
	      g.fillRect(cursor%9*width/9, cursor/9*height/9, width/9, height/9);
	      g.setColor(Color.BLACK);
	    }

	    // Draw the grid lines
	    g.setColor(Color.BLACK);
	    for (int i=0; i<=9; ++i) {
	      g.drawLine(0, height*i/9, width, height*i/9);  // horizontal
	      g.drawLine(width*i/9, 0, width*i/9, height);  // vertical
	      if (i%3==0) {  // draw bold lines around 3x3 boxes
	        g.drawLine(0, height*i/9+1, width, height*i/9+1);
	        g.drawLine(0, height*i/9+2, width, height*i/9+2);
	        g.drawLine(width*i/9+1, 0, width*i/9+1, height);
	        g.drawLine(width*i/9+2, 0, width*i/9+2, height);
	      }
	    }

	    // Fill in the numbers
	    for (int i=0; i<9; ++i) {
	      for (int j=0; j<9; ++j) {
	        if (Grid[i][j]>0) {
	          g.drawString(Grid[i][j]+"", 
	            width*(j*3+1)/27, height*(i*5+4)/45);
	        }
	      }
	    }
	  }
	/*  
	static public void main(String[] args)
	{
		try{ LoadData(); } catch(Exception ex){}
		
		printGrid( Grid );
		System.out.println( "" );
		
		if( SolveSudoku( Grid ) == true )
	          printGrid( Grid );
	    else
	         System.out.println("No solution exists");		
		
	}*/

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		if( SolveSudoku( Grid ) == true )
	          printGrid( Grid );
	    else
	         System.out.println("No solution exists");	
			

	}
}

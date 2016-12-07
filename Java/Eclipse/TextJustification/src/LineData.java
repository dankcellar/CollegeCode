
public class LineData
{
	String strText = "";
	int badness;
	
	LineData next;
	
	public LineData()
	{
	}
	
	public LineData( String strText )
	{
		this.strText = strText;
		badness = Utility.GetBadness( strText );
	}
	
	public String getText()
	{
		return strText;
	}
	
	public void setText(String strText)
	{
		this.strText = strText;
	}
	
	public int getBadness()
	{
		return badness;
	}
	
	public void setBadness(int badness)
	{
		this.badness = badness;
	}
	
}

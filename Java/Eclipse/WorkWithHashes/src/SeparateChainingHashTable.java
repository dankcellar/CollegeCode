
public class SeparateChainingHashTable {

	int m_nTableSize = 10000;
	DataObject[][] m_ObjectArray;
	int j = 0;

	public SeparateChainingHashTable()
	{
		m_ObjectArray = new DataObject[m_nTableSize][100];
	}
		
	public SeparateChainingHashTable(int nTableSize)
	{
		m_nTableSize = nTableSize;
		m_ObjectArray = new DataObject[m_nTableSize][100];
	}
	
	public void put( String strKey, DataObject[] objData )
	{
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		
		while( m_ObjectArray[(int)(lHash%m_nTableSize)] != null)
		{
			lHash++;
		}
			
		m_ObjectArray[(int)lHash] = objData;
		j++;
	}

	public DataObject get( String strKey )
	{
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
			
		while( m_ObjectArray[(int)(lHash%m_nTableSize)][j].GetKey() != strKey)
		{
			lHash++;
		}

		return( m_ObjectArray[(int)(lHash%m_nTableSize)][j] );
	}
}

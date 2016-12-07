
public class QuadraticProbeHashTable {

	int m_nTableSize = 10000;
	DataObject[] m_ObjectArray;
	
	public QuadraticProbeHashTable()
	{
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	public QuadraticProbeHashTable(int nTableSize)
	{
		m_nTableSize = nTableSize;
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	public void put( String strKey, DataObject objData )
	{
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		int j = 0;

		while(m_ObjectArray[(int)((lHash+j*j)%m_nTableSize)] != null &&
				!m_ObjectArray[(int)(lHash%m_nTableSize)].GetKey().equals(strKey))
		{
			j++;
		}
		
		if (m_ObjectArray[(int)((lHash+j*j)%m_nTableSize)] == null)
			m_nTableSize++;
		
		m_ObjectArray[(int)(lHash+j*j)%m_nTableSize] = objData;
	}

	public DataObject get( String strKey )
	{
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		int j = 0;
		
		while(m_ObjectArray[(int)((lHash+j*j)%m_nTableSize)] != null &&
				!m_ObjectArray[(int)(lHash%m_nTableSize)].GetKey().equals(strKey))
		{
			j++;
		}

		return( m_ObjectArray[(int)((lHash+j*j)%m_nTableSize)] );
	}
}


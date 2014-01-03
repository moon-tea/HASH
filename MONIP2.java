import java.text.DecimalFormat;
import java.util.Scanner;
public class MONIP2 
{
	//--Decimal format for easy reading
	static DecimalFormat d = new DecimalFormat("########0.0");		
	
	//--Constants	
	private static final int LINEAR = 0;
	private static final int QUADRATIC = 1;
	private static final int DOUBLE = 2;
	//--Variables
	int size;
	int type;
	int doubHash;
	boolean[] touched;
	HashNode[] table;
	//--Statistics
	int probeCount;	
	
	float avgInsert;
	float totalInserts;
	float totalInsertProbes;
	
	float avgUnsuccessfulSearches;
	float totalUnsuccessfulSearches;
	float totalUnsuccessfulSearchProbes;
	
	float avgSuccessfulSearches;
	float totalSuccessfulSearches;
	float totalSuccessfulSearchProbes;
	
	int membership;	
	//--Constructors
	MONIP2(int sz, int _type) // type is Linear or Quadratic
	{
		size = sz;
		type = _type;
		touched = new boolean[size];
		for(int i = 0; i < size; i++)
		{
			touched[i] = false;
		}
		table = new HashNode[size];
		clear();		
	}
	MONIP2(int sz, int _type, int r) // type is double
	{
		doubHash = r;
		size = sz;
		type = _type;
		touched = new boolean[size];
		for(int i = 0; i < size; i++)
		{
			touched[i] = false;
		}
		table = new HashNode[size];
		clear();			
	} 	
	//--Public Methods
	boolean insert(String key, String record) 	// returns false if key is present, true otherwise.
											 	// key is a string of lower case alphabetic chars w/o spaces.
	{		
		// Generate HashCode
		int hashCode = h_1(key, size);
		if( probeCount != 0 )
		{
			hashCode = h_next(hashCode, key);			
		}
		if( probeCount >= size)
		{			
			probeCount = 0;			
			System.out.println("Table has overflowed");
			return true;
		}
				
		if( table[hashCode] == null) //Table is empty here, let's place it here
		{
			insertStats(probeCount);
			table[hashCode] = new HashNode(key, record);
			touched[hashCode] = true;
			probeCount = 0;			
			membership++;
			System.out.println("Key " + key + " inserted");
			return true;
		}
		else //table is not empty here, if it contains what we are inserting, return false
			 //otherwise, up the ProbeCount and recursively call insert
		{
			if( table[hashCode].getKey().equalsIgnoreCase(key) )
			{
				probeCount = 0;				
				System.out.println("Key " + key + " already exists");
				return false;
			}
			else
			{
				probeCount++;
				insert(key, record);				
			}
		}
		//This code is never touched, due to recursion
		return false;
	}
	
	String isPresent(String key) // returns the record if key is present, null otherwise
	{
		// Generate HashCode
		int hashCode = h_1(key, size);
		if( probeCount != 0 )
		{
			hashCode = h_next(hashCode, key);
		}
		if (probeCount >= size)
		{
			unsuccessfulSearchStats(probeCount);
			probeCount = 0;			
			System.out.println("Key " + key + " not found");
			return null;
		}
						
		if( touched[hashCode] == false) //Table has always been empty here, so it is not present
		{
			unsuccessfulSearchStats(probeCount);			
			probeCount = 0;			
			System.out.println("Key " + key + " not found");
			return null;
		}
		else //table has been touched here, if it contains what we are looking for, return record
			 //otherwise, up the ProbeCount and recursively call isPresent
		{
			if(table[hashCode] == null)
			{
				probeCount++;
				isPresent(key);				
			}
			else
			{
				if( table[hashCode].getKey().equalsIgnoreCase(key) )
				{
					successfulSearchStats(probeCount);
					probeCount = 0;					
					System.out.println("Key " + key + ":" + table[hashCode].getRecord() );
					return table[hashCode].getRecord();
				}
				else
				{
					probeCount++;
					isPresent(key);
					//This code is never touched, due to recursion
					return null;
				}
			}
		}
		//This code is never touched, due to recursion
		return null;
	}
	boolean delete(String key) // returns false if key is not present, true otherwise
	{
		// Generate HashCode
		int hashCode = h_1(key, size);
		if( probeCount != 0 )
		{
			hashCode = h_next(hashCode, key);
		}
		if (probeCount >= size)
		{			
			probeCount = 0;			
			System.out.println("Key " + key + " not present");
			return false;
		}		
						
		if( touched[hashCode] == false) //Table has always been empty here, so it is not present
		{						
			probeCount = 0;			
			System.out.println("Key " + key + " not present");
			return false;
		}
		else //table has been touched here, if it contains what we are looking for, return true and delete
			 //otherwise, up the ProbeCount and recursively call delete
		{
			if(table[hashCode] == null)
			{
				probeCount++;
				delete(key);				
			}
			else
			{
				if( table[hashCode].getKey().equalsIgnoreCase(key) )
				{
					probeCount = 0;					
					System.out.println("Key " + key + " deleted");
					table[hashCode] = null;
					return true;
				}
				else
				{
					probeCount++;
					delete(key);
					//This code is never touched, due to recursion
					return false;
				}
			}
		}
		//This code is never touched, due to recursion
		return false;		
	}
	int membership() // returns the number of records in the data structure
	{
		return membership;		
	}
	void listAll() 	// list all members of the hash table in the order that
					// they are stored. Precede each one with an integer giving
					// the index in the table of that record
	{
		for( int i = 0; i < table.length; i++)
		{
			if( table[i] != null)
			{
				System.out.println(i + " " + table[i].getKey() + ":" + table[i].getRecord());
			}
		}
	}
	void printStats() 	// prints three lines:
						// Average probes in insert = #.#
						// Average probes in unsuccessful search = #.#
						// Average probes in successful search = #.#
						// where the values are rounded to one decimal place.
	{
		System.out.println("Average probes in insert =  " + d.format(avgInsert));
		System.out.println("Average probes in unsuccessful search =  " + d.format(avgUnsuccessfulSearches));
		System.out.println("Average probes in successful search =  " + d.format(avgSuccessfulSearches));
	}
	void clear()		// Reset all values
	{
		for(int i = 0; i < size; i++)
		{
			touched[i] = false;
			table[i] = null;
		}		
		probeCount = 0;		
		
		avgInsert = 0;
		totalInserts = 0;
		totalInsertProbes = 0;
		
		avgUnsuccessfulSearches = 0;
		totalUnsuccessfulSearches = 0;
		totalUnsuccessfulSearchProbes = 0;
		
		avgSuccessfulSearches = 0;
		totalSuccessfulSearches = 0;
		totalSuccessfulSearchProbes = 0;
		
		membership = 0;
	}
	//--End Public Methods
	/////////////////////////////////////////////////////////////////////////
	//--Private Methods
	private int h_1(String key, int tableSize) // Generates a Java hashCode
	{
		int h = key.hashCode() % tableSize;
		if(h < 0)
		{
			h += tableSize;
		}		
		return h;
	}
	private int h_next( int _h_1, String key) // Generates a second hashCode based on the first
											  // Uses a switch statement to figure out what to do
	{
		int hashCode = _h_1;
		switch(type)
		{
			case LINEAR:						
				hashCode += probeCount;
				hashCode %= size;
				break;
			case QUADRATIC:
				hashCode += (probeCount*probeCount);				
				hashCode %= size;
				break;
			case DOUBLE:
				int h1 = h_1(key, size);
				int temp = h1 % doubHash;
				if(temp < 0)
				{
					temp += doubHash;
				}
				int h2 = doubHash - temp;				
				hashCode += (probeCount) * h2;				
				hashCode %= size;
				break;
			default:
				break;
		}		
		return hashCode;
	}
	private void insertStats(int _probecount)
	{
		totalInserts++;
		totalInsertProbes += ( 1 + _probecount);
		avgInsert = (totalInsertProbes/totalInserts);		
	}
	private void unsuccessfulSearchStats(int _probecount)
	{
		totalUnsuccessfulSearches++;
		totalUnsuccessfulSearchProbes += ( 1 + _probecount);
		avgUnsuccessfulSearches = (totalUnsuccessfulSearchProbes/totalUnsuccessfulSearches);		
	}
	private void successfulSearchStats(int _probecount)
	{
		totalSuccessfulSearches++;
		totalSuccessfulSearchProbes += ( 1 + _probecount);
		avgSuccessfulSearches = (totalSuccessfulSearchProbes/totalSuccessfulSearches);
	}
	//--End Private Methods
	///////////////////////////////////////////////////////////////////////////////
	//--Interior Class "Node"
	class HashNode 
	{
		private String key;
		private String record;
		//int touched;
		HashNode(String k, String r)
		{
			key = k;
			record = r;
			//touched = FULL;
		}
	
		public String getKey()
		{
			return key;
		}
		public String getRecord()
		{
			return record;
		}	
	}
	//--End Node
	////////////////////////////////////////////////////////////////////////////////	
	//--Start Main
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		boolean notDone = true;
		MONIP2 hash = null;
		while(notDone) // read commands and obey them
		{ 
			String line = in.nextLine();
			String [] tokens = line.split("[ ]+"); // there is a space in between the brackets
			int command = (int)line.charAt(0); // in Java 7 you case switch on Strings
			int sz;
			int r;
	    	String record;
	    	String key;	    				
			switch(command) 
			{	
	        	case (int)'N': //Print my name
	        		System.out.println("Monte Nichols");
	        		break;    
	        	case (int) 'L': // create a hash table of size sz that uses linear probing
	        		sz = Integer.parseInt(tokens[1]);	   	
	        		hash = new MONIP2(sz, LINEAR);	        		
	        		break;
	        	case (int) 'Q': // create a hash table of size sz that uses quadratic probing
	        		sz = Integer.parseInt(tokens[1]);
	        		hash = new MONIP2(sz, QUADRATIC);
	        		break;
	        	case (int) 'D': // create a hash table of size sz that uses double hashing with
	        					// h_2(y) = R - (y mod R)
	        		sz = Integer.parseInt(tokens[1]);
	        		r = Integer.parseInt(tokens[2]);
	        		hash = new MONIP2(sz, DOUBLE, r);	        		
	        		break;
	        	case (int) 'C': // clear the hash table to empty and reset the statistics
	        		hash.clear();
	        		break;
	        	case (int) 'A': // Insert record "Keeps you clean" with "soap" as its key.
	        					// Print one of the lines "Key soap inserted", "Key soap already exists", OR
	        					// "Table has overflowed". In the last case, the record isn't inserted.
	        		String [] tokens2 = tokens[1].split("[:]+");	        		
	        		key = tokens2[0];
	        		record = tokens2[1];
	        		//System.out.print("inserting... ");
	        		hash.insert(key, record);
	        		break;
	        	case (int) 'R': // Delete the record that has "tin" as a key.
	        					// Print one of the lines "Key tin deleted" OR "Key tin not present".
	        		key = tokens[1];
	        		//System.out.print("deleting... ");
	        		hash.delete(key);
	        		break;
	        	case (int) 'S': // Search for the key "fortune".
	        					// Print one of the lines "Key fortune:" then the record corresponding to that key, OR
	        					// "Key fortune not found".
	        		key = tokens[1];
	        		//System.out.print("searching... ");
	        		hash.isPresent(key);
	        		break;
	        	case (int) 'M': // Print the line "Membership is M" where M is the number of records stored in the table.
	        		System.out.println("Membership is " + hash.membership());
	        		break;
	        	case (int) 'P': // Print a list of the elements in the Table in the order of their position in the table,
	        					// one record per line in the form "# key:Record"
	        		hash.listAll();
	        		break;
	        	case (int) 'T': // Print the following three lines
	        					// Average probes in insert = #.#
	        					// Average probes in unsuccessful search = #.#
	        					// Average probes in successful search = #.#
	        					// where the values are rounded to one decimal place.
	        		hash.printStats();
	        		break;
	        	case (int) 'E':
	        		notDone = false;
	        	default: 
	        		break;        	
	        }//	end switch
        }//		end while       
	}//			end main	
}//				end

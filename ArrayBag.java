/**
    A class of bags whose entries are stored in a fixed-size array.
    @author Frank M. Carrano, Timothy M. Henry
    @version 5.0
*/
public final class ArrayBag<T> implements BagInterface<T>
{
	//class data fields
   //Since the bag will hold a group of objects, one field can be an array of these objects. 
   private final T[] bag; 
   //The length of the array defines the bag’s capacity.
   //We can let the client specify this capacity, and we can also provide a default capacity
	private int numberOfEntries;
   private boolean integrityOK = false;//if it is true:There is no empty cell in the middle of the array and array has beern initialized
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;

	/** Creates an empty bag whose initial capacity is 25 by calling the second constructor. */
	public ArrayBag() 
	{
		this(DEFAULT_CAPACITY);
	} // end default constructor

	/** Creates an empty bag having a given capacity.
	    @param desiredCapacity  The integer capacity desired. */
	public ArrayBag(int desiredCapacity)//new ArrayBag(7)
	{
      if (desiredCapacity <= MAX_CAPACITY)//Making the Implementation Secure
      {
         // The cast is safe because the new array contains null entries
         /*
         An unchecked warning tells a programmer that a cast may cause a program to throw an 
         exception somewhere else. Suppressing the warning with @SuppressWarnings("unchecked")
         tells the compiler that the programmer believes the code to be safe and won't cause 
         unexpected exceptions.
         */
         @SuppressWarnings("unchecked")
         T[] tempBag = (T[])new Object[desiredCapacity]; // Unchecked cast
         bag = tempBag;//make sure that bag data filed is pointing  to the array that you created 
         numberOfEntries = 0;//no items in the bag yet
         integrityOK = true;// integrity is true as soon as you initialize the data fields
      }
      else
         throw new IllegalStateException("Attempt to create a bag " +
                                         "whose capacity exceeds " +
                                         "allowed maximum.");
	} // end constructor
   
	/** Adds a new entry to this bag.
       @param newEntry  The object to be added as a new entry.
       @return  True if the addition is successful, or false if not. */
	public boolean add(T newEntry)
	{
		checkIntegrity(); //Check integrity before adding a new entry in order to prevent holes in the array. 
      boolean result = true;
      //If the bag is full, we cannot add anything to it. In that case, the method add should return false. 
      if (isArrayFull())
      {
         result = false;
      }
      /*
      If we are adding to an empty bag, numberOfEntries would be zero, and the assignment 
      would be to bag[0]. If the bag contained one entry, an additional entry would be 
      assigned to bag[1], and so on. After each addition to the bag, we increase the counter numberOfEntries. 
      */
      else
      {  // Assertion: result is true here
         bag[numberOfEntries] = newEntry;
         numberOfEntries++;
      } // end if
      
      return result;
	} // end add

   // Returns true if the array bag is full, or false if not.
	private boolean isArrayFull()
	{
		return numberOfEntries >= bag.length;
	} // end isArrayFull  
      // Throws an exception if this object is not initialized.
   private void checkIntegrity()
   {
      if (!integrityOK == true )
         throw new SecurityException("ArrayBag object is corrupt.");
   } // end checkIntegrity
   
   /*
   toArray retrieves the entries that are in a bag and returns them to the client within a 
   newly allocated array. The length of this new array can equal the number of entries in 
   the bag—that is, numberOfEntries—rather than the length of the array bag. After toArray 
   creates the new array, a simple loop can copy the references in the array bag to this 
   new array before returning it. 
   */
	/** Retrieves all entries that are in this bag.
       @return  A newly allocated array of all the entries in this bag. */
   
	public T[] toArray()
	{
		checkIntegrity();
      
      // The cast is safe because the new array contains null entries.
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast

      for (int index = 0; index < numberOfEntries; index++) 
      {
         result[index] = bag[index];
      } // end for
      return result;
      // Note: The body of this method could consist of one return statement,
      // if you call Arrays.copyOf
	} // end toArray

	/** Sees whether this bag is empty.
       @return  True if this bag is empty, or false if not. */
	public boolean isEmpty()
	{
      return numberOfEntries == 0;
	} // end isEmpty

	/** Gets the current number of entries in this bag.
       @return  The integer number of entries currently in this bag. */
	public int getCurrentSize()
	{
      return numberOfEntries;
	} // end getCurrentSize

	/** Counts the number of times a given entry appears in this bag.
       @param anEntry  The entry to be counted.
       @return  The number of times anEntry appears in this ba. */
	public int getFrequencyOf(T anEntry)
	{
		checkIntegrity();
      int counter = 0;
      /*
      To count the number of times a given object occurs in a bag, we count the number of 
      times the object occurs in the array bag. Using a for loop to cycle through the 
      array’s indices from 0 to numberOfEntries – 1, we compare the given object to every 
      object in the array. Each time we find a match, we increment a counter. When the loop 
      nds, we simply return the value of the counter. Note that we must use the method 
      equals to compare objects. 

      */
      for (int index = 0; index < numberOfEntries; index++) 
      {
         if (anEntry.equals(bag[index]))
         {
            counter++;
         } // end if
      } // end for

      return counter;
	} // end getFrequencyOf

	/** Tests whether this bag contains a given entry.
       @param anEntry  The entry to locate.
       @return  True if this bag contains anEntry, or false otherwise. */
   public boolean contains(T anEntry)
	{
		checkIntegrity();
      boolean found = false;
		int index = 0;  
      //while (anEntry is not found and we have more array elements to check)    
      while (!found == true && (index < numberOfEntries))
		{
      /*This loop terminates under one of two conditions: Either anEntry has been found in 
      the array or the entire array has been searched without success. */
			if (anEntry.equals(bag[index]))
			{
				found = true;
			} // end if
         index++;
		} // end while
      return found;
   } // end contains

   
	/** Removes all entries from this bag. */
	public void clear() 
	{
      while (!isEmpty())//The method clear removes all entries from a bag, one at a time. 
         remove();
	} // end clear
	
	/** Removes one unspecified entry from this bag, if possible.
       @return  Either the removed entry, if the removal
                was successful, or null. */
	public T remove()
	{
		checkIntegrity();
      T result = removeEntry(numberOfEntries - 1);
      return result;
	} // end remove
	
   
   // Removes and returns the entry at a given index within the array.
   // If no such entry exists, returns null.
   // Precondition: 0 <= givenIndex < numberOfEntries.
   // Precondition: checkInitialization has been called.
	private T removeEntry(int givenIndex)
	{
		T result = null;
      
		if (!isEmpty() && (givenIndex >= 0))
		{
         result = bag[givenIndex];          // Entry to remove
         int lastIndex = numberOfEntries - 1;
         bag[givenIndex] = bag[lastIndex];  // Replace entry to remove with last entry
         bag[lastIndex] = null;             // Remove reference to last entry
         numberOfEntries--;
		} // end if
      
      return result;
	} // end removeEntry
   
   	/** Removes one occurrence of a given entry from this bag.
    @param anEntry  The entry to be removed.
    @return  True if the removal was successful, or false if not. */
	public boolean remove(T anEntry)
	{
		checkIntegrity();
      int index = getIndexOf(anEntry);
      T result = removeEntry(index);         
      return anEntry.equals(result);//true or flase
	} // end remove   
	
 	// Locates a given entry within the array bag.
	// Returns the index of the entry, if located,
	// or -1 otherwise.
   // Precondition: checkInitialization has been called.
	private int getIndexOf(T anEntry)
	{
		int where = -1;
		boolean found = false;      
		int index = 0;
      
      while (!found && (index < numberOfEntries))
		{
			if (anEntry.equals(bag[index]))
			{
				found = true;
				where = index;
			} // end if
         index++;
		} // end while
      
      // Assertion: If where > -1, anEntry is in the array bag, and it
      // equals bag[where]; otherwise, anEntry is not in the array.
      
		return where;
	} // end getIndexOf

} // end ArrayBag




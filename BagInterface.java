/**
   Notes: 
   1. a data type such as int or double is a group of values and operations on those values 
   that is defined within a specific programming language
   2. An ADT, is a specification for a group of values and the operations on those values 
   that is defined conceptually and independently of any programming language
   3. A data structure is an implementation of an ADT within a programming language.
   4. A collection is an object that groups other objects and provides services to its client
   5. A typical collection enables a client to add, remove, retrieve, and query the objects 
   it represents. 
   6. Behaviors are specified abstractly and can differ in purpose according to the collection.
   Thus, a collection is an abstraction and is an abstract data type. 
   7. To provide an example of a collection and of an abstract data type, we will specify 
   and use the ADT bag.
   8. Now, write Java headers for the bag’s methods and organize them into a Java interface 
   for the class that will implement the ADT. 
   9. The Java interface contains the methods for an ADT bag and detailed comments that 
   describe their behaviors. 

*/
/*
To accommodate entries of any one class type, the bag methods use a generic type T for 
each entry. To give meaning to the identifier T, we must write <T> after the name of the 
interface. Once the actual data type is chosen within a client, the compiler will use that 
data type wherever T appears. . All entries in the bag then must have either that data type 
or a subtype of that data type. The compiler will enforce this restriction for us. 
*/
public interface BagInterface<T>
{
	/** Gets the current number of entries in this bag.
		 @return  The integer number of entries currently in the bag. */
	public int getCurrentSize();
	
	/** Sees whether this bag is empty.
		 @return  True if the bag is empty, or false if not. */
	public boolean isEmpty();
	
	/** Adds a new entry to this bag.
	    @param newEntry  The object to be added as a new entry.
	    @return  True if the addition is successful, or false if not. */
	public boolean add(T newEntry);

	/** Removes one unspecified entry from this bag, if possible.
       @return  Either the removed entry, if the removal.
                was successful, or null. */
	public T remove();
   
	/** Removes one occurrence of a given entry from this bag, if possible.
       @param anEntry  The entry to be removed.
       @return  True if the removal was successful, or false if not. */
   public boolean remove(T anEntry);
	
	/** Removes all entries from this bag. */
	public void clear();
	
	/** Counts the number of times a given entry appears in this bag.
		 @param anEntry  The entry to be counted.
		 @return  The number of times anEntry appears in the bag. */
	public int getFrequencyOf(T anEntry);
	
	/** Tests whether this bag contains a given entry.
		 @param anEntry  The entry to find.
		 @return  True if the bag contains anEntry, or false if not. */
	public boolean contains(T anEntry);
   
	/** Retrieves all entries that are in this bag.
		 @return  A newly allocated array of all the entries in the bag.
                Note: If the bag is empty, the returned array is empty. */
	public T[] toArray();
} // end BagInterface

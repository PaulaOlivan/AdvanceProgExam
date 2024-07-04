import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

public class PhoneBook {
    private TreeSet<Person> entries;
    
    // On the question 1.h we need to print the PhoneBook entries in alphabetical
    // order so the best way to implement that is with a Tree, between TreeSet and
    // TreeMap we will use a TreeSet cause we don't have a key, value structure.
    // We won't be able to have two times the same name but is not usual to have two
    // people added equally to our PhoneBook because we can not distinct which number
    // correspond to each equal name + surname
 
    public static final ArrayList<String> phoneTypes = new ArrayList<>();
      
    public PhoneBook()
    {
        entries = new TreeSet<>(new Person.PersonComparator());
        //entries = new ArrayList<>(new Person.PersonComparator());
        phoneTypes.add("mobile");
        phoneTypes.add("home");
        phoneTypes.add("work");
        phoneTypes.add("other");
    }
    
    /**
     * Add a person to the phone book
     */
    public void addPerson(Person p)
    {
        entries.add(p);
    }
    
    /**
     * Prints the names of the persons in the phone book in a numbered list
     * sorted by surname / name
     */
    public void printPhoneBookAlpha()
    {
        int i = 1;
        for (Person p : entries){
            System.out.println(i + ". " + p);
            i++;
        }
    }
    
    /**
     * Returns the person with index i from the list (as last sorted)
     * 
     * @param i The index of the person to return
     * @return The person
     */
    public Person getPerson(int i)
    {
        i--; // As we do in the getUnsortedPhone like the printed
                // indexes go from 1 to n and the Array indexes start in 0
                // we reduce the parameter by one
        if (i > entries.size()){
            return null;
        }
        else{
            int index = 0;
            for (Person p : entries){
                if (index == i){
                    return p;
                }
                else{
                    index++;
                }
            }
            return null; //If we reach this line the index is not in the arraylist
            // Not possible option but needed because all the execution branches must
            // have a return
        }
        
    }

    public String getPhoneType(int index){
        return PhoneBook.phoneTypes.get(index);
    }
    
}

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Comparator;

public class Person {
    private String surname;
    private String name;
    private LinkedHashMap<String, String> phones;
    
    // 1a) In the 1.c its required to print the person's phone in the inserted order
    // so the best way to do that without changing the structure of the code is with a
    // LinkedHashMap due to it's a HashMap with pointers between the added elements so
    // we can follow the pointers getting the elements order by when they have been added.
    
    
    
    public Person(String surname, String name) {
        this.surname = surname;
        this.name = name;
        this.phones = new LinkedHashMap<String, String>();
    }
    
    public String getSurname() {
        return surname;
    }
    
    public String getName() {
        return name;
    }
    
    /** 
     * Adds the phone number (with the corresponding type) to the list of phones
     * of the person.
     * 
     * @param phone The phone number
     * @param type The type of the phone number (home, work, etc.)
     * @return false if the same phone number with the same type exists, true otherwise
     */
    public boolean addPhone(String phone, String type) {
        String result = phones.put(phone, type);
        if (result == null){ // The result is null when there is no element with that key
            return true; // Like there is no element previously we return true
        }
        else if (result == type){ // The result is different from null but the type does not change
            return false; //The key is already in the Map and the type has no change
        }
        else { // (result != type) The result is different from null and different from the new value
            return true; // The type has be changed 
        }
    }
    
    /** 
     * Prints the phone numbers of the person, in the order they were added.
     */
    public void printPhonesUnsorted()
    {   
        int i = 1;
        for (Map.Entry<String, String> item: phones.entrySet()){
            System.out.println(i + ". " + item.getKey() + " (" + item.getValue() + ")");
            i++;
        }
    }
    
    /**
     * Prints the phone numbers of the person, grouped by type.
     */
    public void printPhonesGrouped()
    {
        int num = 1;
        PhoneBook phoneBook = new PhoneBook();

        // We will go thought the phones map for all the phone's types
        for (int i = 0; i < PhoneBook.phoneTypes.size(); i++){
            for (Map.Entry<String, String> item: phones.entrySet()){
                String type = item.getValue();
                if (type == phoneBook.getPhoneType(i)){
                    System.out.println(num + ". " + item.getKey() + " (" + type + ")");
                num++;
                } 
            }
        }
    }

    /**
     * Returns the phone number with index i from the list of phones
     * (as the list would be printed by printPhonesUnsorted() )
     * 
     * @param i The index of the phone number to get
     * @return The phone number
     */
    public String getUnsortedPhoneNumber(int i)
    {
        i--; // Like we show the phones from 1 to n and the indexes of an array
            // goes from 0 to n-1 we will decrease the index to make the program
            // more comprensible for the user.
        int index = 0;
        String result = "";
        if (i < phones.size()){
            for (Map.Entry<String, String> item: phones.entrySet()){
                if (index == i){ // Its the element that we are looking for
                    //System.out.println("Estoy en el index " + index + " y el número es: " + item.getKey());
                    result =  item.getKey();
                    break;
                }
                else{
                    //System.out.println("Estoy en el index " + index + " y continuo iterando");
                    index++;
                }
            }
        }
        // If the index is bigger than the phone list there isn't a phone
        return result;        
    }

    @Override
    public String toString(){
        return surname + ", " + name + " (" + phones.size() + ")";
    }

    public static class PersonComparator implements Comparator<Person>{
        @Override
        public int compare(Person p1, Person p2){
            int result = p1.getSurname().compareTo(p2.getSurname());   
            if (result == 0){ //Both have the same surname
                return p1.getName().compareTo(p2.getName()); // Compare the name
                // The PhoneBook store person in a TreeSet so they cant be equal fullname
            }
            else { // p1 or p2 are bigger than the other so we just return the comparing
                return result;
            }
        }
    }   
}

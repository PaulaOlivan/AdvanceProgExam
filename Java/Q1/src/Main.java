public class Main
{

    public static void main(String[] args) {

        System.out.println("FIRST PART: METHOD TO RUN THE EXAM EXAMPLES");
        Person Pedro = new Person("Perez", "Pedro");
        Pedro.addPhone("6977895632", "mobile");
        Pedro.addPhone("2710271112", "home");
        Pedro.addPhone("2710375656", "work");
        Pedro.addPhone("2710271113", "home");

        System.out.println("\nPhones' list unsorted");
        Pedro.printPhonesUnsorted();

        System.out.println("\nPhones' list grouped");
        Pedro.printPhonesGrouped();

        System.out.println("\nGet a phone from the unsorted list");
        System.out.println("The phone number 4 is: " + Pedro.getUnsortedPhoneNumber(4));
        System.out.println("The phone number 1 is: " + Pedro.getUnsortedPhoneNumber(1));
        System.out.println("The phone number 3 is: " + Pedro.getUnsortedPhoneNumber(3));
        System.out.println("The phone number 5 (doesn't exist) is: " + Pedro.getUnsortedPhoneNumber(5));

        System.out.println("\n\nSECOND PART: 1j) MAIN PROGRAM IMPLEMENTATION");
        Person p1 = new Person("Banderas", "Antonio");
        Person p2 = new Person ("Cruz", "Penelope");
        Person p3 = new Person("Segura", "Santiago");
        Person p4 = new Person ("Cruz", "Monica");
        Person p5 = new Person ("Banderas", "Antonio");
        
        p1.addPhone("123456789", "mobile");
        p1.addPhone("987654321", "other");
        p1.addPhone("112233445", "work");
        
        p2.addPhone("998877665", "other");
        p2.addPhone("111111111", "home");
        
        p3.addPhone("123123123", "other");
        p3.addPhone("987987987", "mobile");

        p4.addPhone("456456456", "mobile");
        p4.addPhone("098098098", "work");

        PhoneBook contacts = new PhoneBook();
        contacts.addPerson(p4);
        contacts.addPerson(p1);
        contacts.addPerson(p3);
        contacts.addPerson(p2);
        contacts.addPerson(p5); //Its not added because it already exist in the PhoneBook

        System.out.println("\nPrinting the Phone Book in alphabetic way");
        contacts.printPhoneBookAlpha();

        System.out.println("\nGet a person from the Phone Book");
        System.out.println("Note: like the Person class has toString method the reference to person will be show as a String");
        System.out.println("The person with index 4 is: " + contacts.getPerson(4));
        System.out.println("The person with index 2 is: " + contacts.getPerson(2));
        System.out.println("The person with index 1 is: " + contacts.getPerson(1));
        System.out.println("The person with index 7 (doesn't exist) is: " + contacts.getPerson(7));
    }

}

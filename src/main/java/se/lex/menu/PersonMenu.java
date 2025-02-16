package se.lex.menu;

import se.lex.dao.AppUserDAO;
import se.lex.dao.AppUserDAOCollection;
import se.lex.dao.PersonDAO;
import se.lex.dao.PersonDAOCollection;
import se.lex.model.AppUser;
import se.lex.model.Person;
import se.lex.sequencer.PersonIdSequencer;

import java.util.Collection;
import java.util.Scanner;



public class PersonMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PersonDAO personDAO = PersonDAOCollection.getInstance();
    private static final AppUserDAO appUserDAO = AppUserDAOCollection.getInstance();
    public static void managePersons() {
        while (true) {
            System.out.println("\n=== Person Management ===");
            System.out.println("1. Create Person");
            System.out.println("2. View All Persons");
            System.out.println("3. Find Person by ID");
            System.out.println("4. Find Person by Email");
            System.out.println("5. Update Person");
            System.out.println("6. Delete Person");
            System.out.println("7. Return to Main Menu");
            System.out.print("Select option: ");

            int choice = getIntInput();
            scanner.nextLine();

            switch (choice) {
                case 1: createPerson(); break;
                case 2: listAllPersons(); break;
                case 3: findPersonById(); break;
                case 4: findPersonByEmail(); break;
                case 5: updatePerson(); break;
                case 6: deletePerson(); break;
                case 7: return;
                default: System.out.println("Invalid option!");
            }
        }
    }

    private static void createPerson() {
        int id = PersonIdSequencer.nextId();

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter associated username: ");
        String username = scanner.nextLine();
        AppUser credentials = appUserDAO.findByUsername(username);

        if (credentials == null) {
            System.out.println("Username not found!");
            return;
        }

        Person newPerson = new Person(id, firstName, lastName, email);
        newPerson.setCredentials(credentials);
        personDAO.persist(newPerson);
        System.out.println("Person created successfully!");
    }

    private static void listAllPersons() {
        Collection<Person> persons = personDAO.findAll();
        if (persons.isEmpty()) {
            System.out.println("No persons found.");
            return;
        }
        persons.forEach(System.out::println);
    }

    private static void findPersonById() {
        System.out.print("Enter ID: ");
        int id = getIntInput();

        Person person = personDAO.findById(id);
        if (person == null) {
            System.out.println("Person not found.");
            return;
        }
        System.out.println(person);
    }

    private static void findPersonByEmail() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        Person person = personDAO.findByEmail(email);
        if (person == null) {
            System.out.println("Person not found.");
            return;
        }
        System.out.println(person);
    }

    private static void updatePerson() {
        System.out.print("Enter ID to update: ");
        int id = getIntInput();
        scanner.nextLine(); // Consume the newline character

        Person person = personDAO.findById(id);
        if (person == null) {
            System.out.println("Person not found.");
            return;
        }

        System.out.print("Enter new first name: ");
        String firstName = scanner.nextLine();
        if (!firstName.trim().isEmpty()) {
            person.setFirstName(firstName);
        }

        System.out.print("Enter new last name: ");
        String lastName = scanner.nextLine();
        if (!lastName.trim().isEmpty()) {
            person.setLastName(lastName);
        }

        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        if (!email.trim().isEmpty()) {
            person.setEmail(email);
        }

        System.out.println("Person updated successfully!");
    }

    private static void deletePerson() {
        System.out.print("Enter ID to delete: ");
        int id = getIntInput();

        personDAO.remove(id);
        System.out.println("Person deleted successfully!");
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}

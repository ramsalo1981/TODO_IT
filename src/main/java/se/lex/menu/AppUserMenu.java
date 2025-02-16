package se.lex.menu;

import se.lex.dao.AppUserDAO;
import se.lex.dao.AppUserDAOCollection;
import se.lex.model.AppRole;
import se.lex.model.AppUser;

import java.util.Collection;
import java.util.Scanner;

public class AppUserMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AppUserDAO appUserDAO = AppUserDAOCollection.getInstance();
    public static void manageAppUsers() {
        while (true) {
            System.out.println("\n=== App User Management ===");
            System.out.println("1. Create App User");
            System.out.println("2. View All App Users");
            System.out.println("3. Find App User by Username");
            System.out.println("4. Update App User");
            System.out.println("5. Delete App User");
            System.out.println("6. Return to Main Menu");
            System.out.print("Select option: ");

            int choice = getIntInput();
            scanner.nextLine();

            switch (choice) {
                case 1: createAppUser(); break;
                case 2: listAllAppUsers(); break;
                case 3: findAppUserByUsername(); break;
                case 4: updateAppUser(); break;
                case 5: deleteAppUser(); break;
                case 6: return;
                default: System.out.println("Invalid option!");
            }
        }
    }

    private static void createAppUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter role (USER/ADMIN): ");
        String roleInput = scanner.nextLine().toUpperCase();

        try {
            AppRole role = AppRole.valueOf("ROLE_APP_" + roleInput);
            AppUser newUser = new AppUser(username, password, role);
            appUserDAO.persist(newUser);
            System.out.println("User created successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role! Use USER or ADMIN.");
        }
    }

    private static void listAllAppUsers() {
        Collection<AppUser> users = appUserDAO.findAll();
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }
        users.forEach(System.out::println);
    }

    private static void findAppUserByUsername() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        AppUser user = appUserDAO.findByUsername(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.println(user);
    }

    private static void updateAppUser() {
        System.out.print("Enter username to update: ");
        String username = scanner.nextLine().toLowerCase();

        AppUser user = appUserDAO.findByUsername(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.print("Enter new username: ");
        String newUsername = scanner.nextLine();

        System.out.print("Enter new password: ");
        String password = scanner.nextLine();

        System.out.print("Enter new role (USER/ADMIN): ");
        String roleInput = scanner.nextLine().toUpperCase();

        try {
            AppRole role = AppRole.valueOf("ROLE_APP_" + roleInput);
            user.setUsername(newUsername);
            user.setPassword(password);
            user.setRole(role);
            System.out.println("User updated successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role! Use USER or ADMIN.");
        }
    }

    private static void deleteAppUser() {
        System.out.print("Enter username to delete: ");
        String username = scanner.nextLine();

        appUserDAO.remove(username);
        System.out.println("User deleted successfully!");
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}

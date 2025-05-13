
package POO;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AircraftManagement aircraftManagement = new AircraftManagement(50); // Gerencia até 50 aeronaves
    private static final MissionManagement missionManagement = new MissionManagement(100);   // Gerencia até 100 missões
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Formato padrão para datas

    // Requests a valid aircraft ID (minimum 4 digits and unique)
    private static String getValidAircraftId() {
        while (true) {
            System.out.print("Aircraft ID (minimum 4 digits): ");
            String id = scanner.nextLine();

            if (id.length() < 4) {
                System.out.println("ID must have at least 4 digits. Please try again.");
                continue;
            }

            if (aircraftManagement.getAircraftById(id) != null) {
                System.out.println("ID already exists, please enter another");
            } else {
                return id;
            }
        }
    }
    // Method to obtain a valid mission ID
    private static String getValidMissionId() {
        while (true) {
            System.out.print("Mission ID (minimum 4 digits): ");
            String id = scanner.nextLine();

            if (id.length() < 4) {
                System.out.println("ID must have at least 4 digits. Please try again.");
                continue;
            }

            if (missionManagement.getMissionById(id) != null) {
                System.out.println("ID already exists, please enter another");
            } else {
                return id;
            }
        }
    }


    // Shows the average number of missions per aircraft type for a specific mission type
    private static void showAverageMissionsPerAircraftType() {
        System.out.println("\n=== Available Mission Types ===");
        MissionType[] missionTypes = MissionType.values();
        for (int i = 0; i < missionTypes.length; i++) {
            System.out.println((i + 1) + ". " + missionTypes[i]);
        }

        System.out.print("\nSelect the mission type: ");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            if (choice < 1 || choice > missionTypes.length) {
                System.out.println("Invalid option! Please select a number between 1 and " + missionTypes.length);
                return;
            }

            MissionType selectedType = missionTypes[choice - 1];
            String[] aircraftTypes = aircraftManagement.getAllAircrafts();

            if (aircraftTypes == null || aircraftTypes.length == 0) {
                System.out.println("No aircraft types registered in the system.");
                return;
            }

            double[] averages = missionManagement.getAverageMissionsPerAircraftType(selectedType, aircraftTypes);

            if (averages == null || averages.length != aircraftTypes.length) {
                System.out.println("Error calculating averages. The returned data is inconsistent.");
                return;
            }

            System.out.println("\n=== Average " + selectedType + " Missions per Aircraft Type ===");
            boolean hasValidData = false;

            for (int i = 0; i < aircraftTypes.length; i++) {
                if (averages[i] >= 0) {
                    System.out.printf("%-15s: %.2f missions per aircraft%n",
                            aircraftTypes[i], averages[i]);
                    hasValidData = true;
                }
            }

            if (!hasValidData) {
                System.out.println("No valid data available for the selected mission type.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter numbers only.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
    public static void main(String[] args) throws ParseException {
        //=---------------------------------------TESTE-----------------------------------------------
//        Aircraft nave1 = new Fighter("F120", "se", null, null, null,null ,50,50,50 ,null);
//        Aircraft nave2 = new Fighter("F121", "se", null, null, null,null ,50,50,50 ,null);
//        Aircraft nave3 = new Fighter("F122", "se", null, null, null,null ,50,50,50 ,null);
//
//        Pilot piloto1 = new Pilot(1234 , "Leticia",Rank.GENERAL ,"1234");
//
//       Mission missao1 = new Mission(null, null, null, null,null,null ,null ,null ,null ,10 ,null );


    mainMenu();
    }

    private static void mainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n==== MAIN MENU ====");
            System.out.println("1. Manage Aircraft");
            System.out.println("2. Manage Crew");
            System.out.println("3. Manage Missions");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> menuAircraft(); // Not yet implemented
                case 2 -> menuCrew();  // Not yet implemented
                case 3 -> menuMission();
                case 0 -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
    }
// Implementation in compliance with the restriction of not using predefined Java collections.
// Instead of ArrayList or other Java Collections Framework collections, we use plain arrays (Aircraft[]).

    // Menu for aircraft management
    private static void menuAircraft() {
        System.out.println("\n== Aircraft Management ==");
        System.out.println("1. Create new aircraft");
        System.out.println("2. List aircraft");
        System.out.println("3. Edit aircraft");
        System.out.println("4. Remove aircraft");
        System.out.println("5. Consult aircraft by annual missions");
        System.out.println("6. Aircraft with estimated lifetime");
        System.out.println("0. Back");
        System.out.print("Choose: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> createAircraft();
            case 2 -> listAircraft();
            case 3 -> editAircraft();
            case 4 -> removeAircraft();
            case 5 -> consultAircraftsByMissions();
            case 6 -> showAircraftsByEstimatedLife();
            case 0 -> mainMenu();
            default -> System.out.println("Invalid option.");
        }
    }
    // Method to create a new aircraft
    private static void createAircraft() {
        try {
            String id = getValidAircraftId();
            System.out.print("Manufacturer: ");
            String manufacturer = scanner.nextLine();
            System.out.print("Incorporation date (dd-MM-yyyy): ");
            Date incorporationDate = sdf.parse(scanner.nextLine());

            System.out.println("Select aircraft type:");
            System.out.println("1. Fighter");
            System.out.println("2. Transport");
            System.out.println("3. Helicopter");
            System.out.print("Option: ");
            int option = Integer.parseInt(scanner.nextLine());

            Aircraft aircraft = null;

            switch (option) {
                case 1 -> {
                    System.out.println("Select fighter type:");
                    System.out.println("1. MULTIROLE");
                    System.out.println("2. INTERCEPTOR");
                    System.out.println("3. AIRSUPERIORITY");
                    System.out.print("Option: ");
                    int fighterTypeOption = Integer.parseInt(scanner.nextLine());

                    FighterType fighterType = null;
                    switch (fighterTypeOption) {
                        case 1 -> fighterType = FighterType.MULTIROLE;
                        case 2 -> fighterType = FighterType.FIGHTER_BOMBER;
                        case 3 -> fighterType = FighterType.AIR_SUPERIORITY;
                        default -> System.out.println("Invalid option.");
                    }

                    if (fighterType != null) {
                        aircraft = new Fighter(id, manufacturer, incorporationDate, null, null, null, 5, 12000, 2000, fighterType);
                        System.out.println("Aircraft of type " + fighterType + " created successfully.");
                    }
                }
                case 2 -> {
                    System.out.println("Select transport type:");
                    System.out.println("1. TACTICAL");
                    System.out.println("2. STRATEGIC");
                    System.out.println("3. REFUELING");
                    System.out.print("Option: ");
                    int transportationTypeOption = Integer.parseInt(scanner.nextLine());

                    TransportationType transportationType = null;
                    switch (transportationTypeOption) {
                        case 1 -> transportationType = TransportationType.TACTICAL;
                        case 2 -> transportationType = TransportationType.STRATEGIC;
                        case 3 -> transportationType = TransportationType.REFUELING;
                        default -> System.out.println("Invalid option.");
                    }

                    if (transportationType != null) {
                        aircraft = new Transportation(id, manufacturer, incorporationDate, incorporationDate, incorporationDate, incorporationDate, transportationTypeOption, option, transportationTypeOption, transportationType);
                        System.out.println("Aircraft of type " + transportationType + " created successfully.");
                    }
                }
                case 3 -> {
                    System.out.println("Select helicopter type:");
                    System.out.println("1. SEARCH_RESCUE");
                    System.out.println("2. COMBAT_SUPPORT");
                    System.out.println("3. TACTICAL_TRANSPORT");
                    System.out.print("Option: ");
                    int helicopterTypeOption = Integer.parseInt(scanner.nextLine());

                    HelicopterType helicopterType = null;
                    switch (helicopterTypeOption) {
                        case 1 -> helicopterType = HelicopterType.SEARCH_RESCUE;
                        case 2 -> helicopterType = HelicopterType.COMBAT_SUPPORT;
                        case 3 -> helicopterType = HelicopterType.TACTICAL_TRANSPORT;
                        default -> System.out.println("Invalid option.");
                    }

                    if (helicopterType != null) {
                        aircraft = new Helicopter(id, manufacturer, incorporationDate, null, null, null, incorporationDate, 4, 300, 250, helicopterType);
                        System.out.println("Aircraft of type " + helicopterType + " created successfully.");
                    }
                }
                default -> {
                    System.out.println("Invalid option.");
                    return;
                }
            }

            if (aircraft != null) {
                // Storage done using plain array, not using ArrayList or other Java collections
                aircraftManagement.addAircraft(aircraft);
                System.out.println("Aircraft added successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error creating aircraft: " + e.getMessage());
        }
    }

    // Lists all registered aircraft
    private static void listAircraft() {
        // Using plain array instead of collection
        Aircraft[] all = aircraftManagement.getAircrafts();
        if (all.length == 0) {
            System.out.println("No aircraft registered.");
        } else {
            for (Aircraft a : all) {
                System.out.println("ID: " + a.getIdAircraft() + ", Type: " + a.getClass().getSimpleName());
            }
        }
    }

    // Edit aircraft
    private static void editAircraft() {
        Aircraft[] aircrafts = aircraftManagement.getAircrafts(); // Plain array
        if (aircrafts.length == 0) {
            System.out.println("No aircraft registered.");
            return;
        }

        for (Aircraft a : aircrafts) {
            System.out.println("ID: " + a.getIdAircraft() + " - " + a.getClass().getSimpleName() + " (" + a.getManufacturer() + ")");
        }

        System.out.print("\nEnter the ID of the aircraft to edit: ");
        String id = scanner.nextLine();

        if (id.length() < 4) {
            System.out.println("ID must have at least 4 digits.");
            return;
        }

        Aircraft aircraft = aircraftManagement.getAircraftById(id); // Manual search, no HashMap

        if (aircraft == null) {
            System.out.println("Aircraft not found!");
            return;
        }

        boolean editing = true;
        while (editing) {
            System.out.println("\n=== Editing Aircraft " + aircraft.getIdAircraft() + " ===");
            System.out.println("1. Edit manufacturer");
            System.out.println("2. Edit capacity");
            System.out.println("3. Edit incorporation date");

            if (aircraft instanceof Fighter) {
                System.out.println("4. Edit fighter type");
            } else if (aircraft instanceof Transportation) {
                System.out.println("4. Edit transport type");
            } else if (aircraft instanceof Helicopter) {
                System.out.println("4. Edit helicopter type");
            }

            System.out.println("5. View all details");
            System.out.println("0. Save and exit");
            System.out.print("Choose: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> editAircraftManufacturer(aircraft);
                    case 2 -> editAircraftCapacity(aircraft);
                    case 3 -> editAircraftIncorporationDate(aircraft);
                    case 4 -> editAircraftSpecificType(aircraft);
                    case 5 -> displayAircraftDetails(aircraft);
                    case 0 -> {
                        editing = false;
                        System.out.println("Changes saved successfully!");
                    }
                    default -> System.out.println("Invalid option!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Estimated lifetime query without using collections
    private static void showAircraftsByEstimatedLife() {
        System.out.println("\n=== Estimated Lifetime Query ===");
        System.out.println("1. Aircraft with less than 5 years of service life");
        System.out.println("2. Aircraft with less than 10 years of service life");
        System.out.println("3. Set another period");
        System.out.println("0. Back");
        System.out.print("Choose: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        int years;
        switch (choice) {
            case 1 -> years = 5;
            case 2 -> years = 10;
            case 3 -> {
                System.out.print("Enter number of years: ");
                years = scanner.nextInt();
                scanner.nextLine();
            }
            case 0 -> { return; }
            default -> {
                System.out.println("Invalid option!");
                return;
            }
        }

        // Search with array, no streams or collections
        Aircraft[] expiringAircrafts = aircraftManagement.getAircraftsExpiringInYears(years);

        System.out.println("\n=== Aircraft with less than " + years + " years of service life ===");
        if (expiringAircrafts.length == 0) {
            System.out.println("No aircraft found.");
        } else {
            System.out.printf("%-10s %-15s %-20s %-15s %-15s%n",
                    "ID", "Type", "Manufacturer", "Incorporation", "Years Remaining");
            System.out.println("---------------------------------------------------------------");

            for (Aircraft a : expiringAircrafts) {
                long remainingYears = calculateRemainingYears(a.getIncorporationDate());
                System.out.printf("%-10s %-15s %-20s %-15s %-15d%n",
                        a.getIdAircraft(),
                        a.getClass().getSimpleName(),
                        a.getManufacturer(),
                        sdf.format(a.getIncorporationDate()),
                        remainingYears);
            }
        }
    }

    /**
     * Calculates remaining years of service life based on incorporation date.
     */
    private static long calculateRemainingYears(Date incorporationDate) {
        Date now = new Date();
        long diffInMillis = now.getTime() - incorporationDate.getTime();
        return 30 - (diffInMillis / (1000L * 60 * 60 * 24 * 365)); // Assuming 30-year service life
    }

    /**
     * Edits the manufacturer of an aircraft.
     */
    private static void editAircraftManufacturer(Aircraft aircraft) {
        System.out.print("New manufacturer (current: " + aircraft.getManufacturer() + "): ");
        String manufacturer = scanner.nextLine();
        if (!manufacturer.isEmpty()) {
            aircraft.setManufacturer(manufacturer);
            System.out.println("Manufacturer updated!");
        }
    }

    /**
     * Edits the capacity of an aircraft.
     */
    private static void editAircraftCapacity(Aircraft aircraft) {
        System.out.print("New capacity (current: " + aircraft.getCapacity() + "): ");
        try {
            int capacity = Integer.parseInt(scanner.nextLine());
            if (capacity > 0) {
                aircraft.setCapacity(capacity);
                System.out.println("Capacity updated!");
            } else {
                System.out.println("Capacity must be greater than zero!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid value! Enter a number.");
        }
    }

    /**
     * Edits the incorporation date of an aircraft.
     */
    private static void editAircraftIncorporationDate(Aircraft aircraft) {
        System.out.print("New incorporation date (dd-MM-yyyy) (current: " +
                sdf.format(aircraft.getIncorporationDate()) + "): ");
        try {
            Date newDate = sdf.parse(scanner.nextLine());
            aircraft.setIncorporationDate(newDate);
            System.out.println("Incorporation date updated!");
        } catch (ParseException e) {
            System.out.println("Invalid date format! Use dd-MM-yyyy");
        }
    }

    /**
     * Edits the specific type of an aircraft (Fighter, Transport, Helicopter).
     */
    private static void editAircraftSpecificType(Aircraft aircraft) {
        if (aircraft instanceof Fighter) {
            editFighterType((Fighter) aircraft);
        } else if (aircraft instanceof Transportation) {
            editTransportationType((Transportation) aircraft);
        } else if (aircraft instanceof Helicopter) {
            editHelicopterType((Helicopter) aircraft);
        }
    }

    /**
     * Edits the type of a fighter.
     */
    private static void editFighterType(Fighter fighter) {
        System.out.println("\nSelect the new fighter type:");
        FighterType[] types = FighterType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i]);
        }

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice > 0 && choice <= types.length) {
                fighter.setType(types[choice - 1]);
                System.out.println("Fighter type updated to " + types[choice - 1]);
            } else {
                System.out.println("Invalid option!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }

    /**
     * Edits the type of transport.
     */
    private static void editTransportationType(Transportation transportation) {
        System.out.println("\nSelect the new transport type:");
        TransportationType[] types = TransportationType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i]);
        }

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice > 0 && choice <= types.length) {
                transportation.setType(types[choice - 1]);
                System.out.println("Transport type updated to " + types[choice - 1]);
            } else {
                System.out.println("Invalid option!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }

    /**
     * Edits the type of a helicopter.
     */
    private static void editHelicopterType(Helicopter helicopter) {
        System.out.println("\nSelect the new helicopter type:");
        HelicopterType[] types = HelicopterType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i]);
        }

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice > 0 && choice <= types.length) {
                helicopter.setType(types[choice - 1]);
                System.out.println("Helicopter type updated to " + types[choice - 1]);
            } else {
                System.out.println("Invalid option!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }

    /**
     * Displays details of an aircraft.
     */
    private static void displayAircraftDetails(Aircraft aircraft) {
        System.out.println("\n=== Aircraft Details ===");
        System.out.println("ID: " + aircraft.getIdAircraft());
        System.out.println("Type: " + aircraft.getClass().getSimpleName());
        System.out.println("Manufacturer: " + aircraft.getManufacturer());
        System.out.println("Capacity: " + aircraft.getCapacity());
        System.out.println("Incorporation Date: " + sdf.format(aircraft.getIncorporationDate()));

        if (aircraft instanceof Fighter) {
            System.out.println("Fighter Type: " + ((Fighter) aircraft).getType());
        } else if (aircraft instanceof Transportation) {
            System.out.println("Transport Type: " + ((Transportation) aircraft).getType());
        } else if (aircraft instanceof Helicopter) {
            System.out.println("Helicopter Type: " + ((Helicopter) aircraft).getType());
        }
    }

    /**
     * Removes an aircraft based on the provided ID.
     */
    private static void removeAircraft() {
        System.out.print("ID of aircraft to remove: ");
        String id = scanner.nextLine();
        boolean removed = aircraftManagement.removeAircraft(id);
        if (removed) {
            System.out.println("Aircraft removed.");
        } else {
            System.out.println("Aircraft not found.");
        }
    }

    private static void menuCrew(){
        System.out.println("\n== Crew Management ==");
        System.out.println("1. Create new Crew Member");
        System.out.println("0. Back");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: createCrewMember();

            case 0: mainMenu();
        }

    }

    private static void createCrewMember() {
        System.out.println("\nCreate Pilot?");
        System.out.println("1. Create Pilot");
        System.out.println("2. Create generic Crew Member");
        System.out.println("0. Back");
        System.out.println("Choose: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        CrewMember crewMember = null;
        switch (choice) {
            case 1: {
                System.out.println("What is the Pilot's name? ");
                String name = scanner.nextLine();
                Rank rank = selectRank("What is the CrewMember's rank? (CADET, LIEUTENANT, CAPTAIN, MAJOR, COLONEL): ");
                crewMember = new Pilot(name, rank);
                System.out.println("Pilot added");
                createCrewMember();
            }
            case 2: {
                System.out.println("What is the CrewMember's name? ");
                String name = scanner.nextLine();
                Rank rank = selectRank("What is the CrewMember's rank? (CADET, LIEUTENANT, CAPTAIN, MAJOR, COLONEL): ");
                crewMember = new Pilot(name, rank);
                System.out.println("Pilot added");
                createCrewMember();

            }
            case 0: menuCrew();
            default: {
                System.out.println("Invalid option!");
                createCrewMember();
            }
        }
    }





    /**
     * Mission management menu.
     */
    private static void menuMission() {
        System.out.println("\n== Mission Management ==");
        System.out.println("1. Create new mission");
        System.out.println("2. List missions");
        System.out.println("3. Edit mission");
        System.out.println("4. Remove mission");
        System.out.println("5. Aircraft mission report");
        System.out.println("6. Average missions per aircraft type");
        System.out.println("7. Missions by context (National/NATO/UN)");
        System.out.println("8. Missions by crew member");
        System.out.println("0. Back");
        System.out.print("Choose: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> createMission();
            case 2 -> listMissions();
            case 3 -> editMission();
            case 4 -> removeMission();
            case 5 -> showAircraftMissionReport();
            case 6 -> showAverageMissionsPerAircraftType();
            case 7 -> showMissionsByContext();
            case 8 -> showMissionsByCrewMember();
            case 0 -> mainMenu();
            default -> System.out.println("Invalid option.");
        }
    }

    private static void createMission() {
        try {
            System.out.println("\n=== Create New Mission ===");

            String id = getValidMissionId();

            System.out.print("ID of aircraft used: ");
            String aircraftId = scanner.nextLine();
            Aircraft aircraft = aircraftManagement.getAircraftById(aircraftId);
            if (aircraft == null) {
                System.out.println("Aircraft not found.");
                return;
            }

            System.out.print("ID of pilot used: ");
            int pilotId = scanner.nextInt();
            Pilot pilot = Pilot.getPilotById(pilotId);
            if (pilot == null) {
                System.out.println("Pilot not found.");
                return;
            }

            MissionType type = selectMissionType();
            MissionContext context = selectMissionContext();

            System.out.print("Start date (dd-MM-yyyy): ");
            Date startDate = sdf.parse(scanner.nextLine());

            System.out.print("End date (dd-MM-yyyy): ");
            Date endDate = sdf.parse(scanner.nextLine());

            System.out.print("Authorized by: ");
            String authorizedBy = scanner.nextLine();

            Rank authorizerRank = selectRank("Authorizer's rank");

            Mission mission = new Mission(
                    id, startDate, endDate, "Operational mission",
                    authorizedBy, authorizerRank.toString(), type, context, aircraft, 10, pilot
            );

            // Initial crew management
            manageMissionCrew(mission);

            // Add additional crew members
            while (true) {
                System.out.print("\nWould you like to add another crew member? (Y/N): ");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("Y")) {
                    addCrewMember(mission);
                } else {
                    break;
                }
            }

            // Add pilots
            while (true) {
                System.out.print("\nWould you like to add a pilot? (Y/N): ");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("Y")) {
                    addPilot(mission);
                } else {
                    break;
                }
            }

            missionManagement.addMission(mission);

            System.out.println("Mission " + id + " created successfully with " +
                    mission.getCrew().length + " crew members.");

        } catch (ParseException e) {
            System.out.println("Date format error. Use dd-MM-yyyy");
        } catch (Exception e) {
            System.out.println("Error creating mission: " + e.getMessage());
        }
    }

    // Shows missions by context (National/NATO/UN)
    private static void showMissionsByContext() {
        // List available aircraft
        System.out.println("\n=== Available Aircraft ===");
        Aircraft[] aircrafts = aircraftManagement.getAircrafts();
        if (aircrafts.length == 0) {
            System.out.println("No aircraft registered.");
            return;
        }
        for (Aircraft a : aircrafts) {
            System.out.println("ID: " + a.getIdAircraft() + " - " + a.getClass().getSimpleName());
        }

        // Select aircraft
        System.out.print("\nEnter the aircraft ID: ");
        String aircraftId = scanner.nextLine();
        Aircraft aircraft = aircraftManagement.getAircraftById(aircraftId);
        if (aircraft == null) {
            System.out.println("Aircraft not found!");
            return;
        }

        // Select context
        System.out.println("\n=== Available Contexts ===");
        System.out.println("1. National");
        System.out.println("2. NATO");
        System.out.println("3. ONU");
        System.out.print("Choose context (1-3): ");
        int contextChoice = scanner.nextInt();
        scanner.nextLine();

        String context;
        switch (contextChoice) {
            case 1 -> context = "NATIONAL";
            case 2 -> context = "NATO";
            case 3 -> context = "ONU";
            default -> {
                System.out.println("Invalid option!");
                return;
            }
        }

        // Display filtered missions
        System.out.println("\n=== Missions for Aircraft " + aircraftId + " in Context " + context + " ===");
        Mission[] missions = missionManagement.getMissionsByContext(aircraft, context);
        if (missions.length == 0) {
            System.out.println("No missions found.");
        } else {
            for (Mission m : missions) {
                System.out.println("- " + m.getIdMission() + ": " + m.getPurpose() +
                        " (Start: " + sdf.format(m.getStartDate()) + ")");
            }
        }
    }

    // Method for managing mission crew with menus
    private static void manageMissionCrew(Mission mission) {
        boolean managingCrew = true;
        while (managingCrew && mission.getCrew().length < 10) {
            System.out.println("\n=== Crew Management ===");
            System.out.println("Current members: " + mission.getCrew().length + "/10");
            System.out.println("1. Add crew member");
            System.out.println("2. Add pilot");
            System.out.println("3. List crew");
            System.out.println("0. Finish");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    addCrewMemberWithMenu(mission, false);
                    break;
                case 2:
                    addCrewMemberWithMenu(mission, true);
                    break;
                case 3:
                    listCrewMembers(mission);
                    break;
                case 0:
                    managingCrew = false;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    /**
     * Shows the missions in which a specific crew member participated
     */
    private static void showMissionsByCrewMember() {
        // Collect all unique crew members from missions
        Mission[] missions = missionManagement.getAllMissions();
        CrewMember[] allCrew = new CrewMember[100]; // Fixed size, increase if needed
        int crewCount = 0;

        for (Mission m : missions) {
            CrewMember[] crew = m.getCrew();
            if (crew != null) {
                for (CrewMember member : crew) {
                    if (member != null && !isCrewAlreadyAdded(allCrew, crewCount, member)) {
                        allCrew[crewCount++] = member;
                    }
                }
            }
        }

        if (crewCount == 0) {
            System.out.println("\nNo crew members found in missions.");
            return;
        }

        // Show list of available crew members
        System.out.println("\n=== Available Crew Members ===");
        for (int i = 0; i < crewCount; i++) {
            CrewMember member = allCrew[i];
            System.out.printf("%d. %s (ID: %d, %s",
                    (i + 1),
                    member.getName(),
                    member.getNumber(),
                    member.getRank());
            if (member instanceof Pilot) {
                System.out.print(", Pilot");
            }
            System.out.println(")");
        }

        // Select crew member
        System.out.print("\nEnter the crew member number: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > crewCount) {
                System.out.println("Invalid option!");
                return;
            }

            CrewMember selectedMember = allCrew[choice - 1];
            Mission[] missionsByMember = missionManagement.getMissionsByCrewMember(selectedMember);

            System.out.println("\n=== Missions of " + selectedMember.getName() + " ===");
            if (missionsByMember.length == 0) {
                System.out.println("No missions found for this crew member.");
            } else {
                System.out.printf("%-10s %-15s %-12s %-12s %-15s %-20s%n",
                        "Mission ID", "Type", "Start", "End", "Aircraft", "Purpose");
                System.out.println("----------------------------------------------------------------");

                for (Mission m : missionsByMember) {
                    System.out.printf("%-10s %-15s %-12s %-12s %-15s %-20s%n",
                            m.getIdMission(),
                            m.getType(),
                            sdf.format(m.getStartDate()),
                            sdf.format(m.getEndDate()),
                            m.getAircraft().getIdAircraft(),
                            m.getPurpose().length() > 20 ?
                                    m.getPurpose().substring(0, 17) + "..." :
                                    m.getPurpose());
                }

                // Option to save report to file
                System.out.print("\nWould you like to save this report to a file? (Y/N): ");
                String saveOption = scanner.nextLine();
                if (saveOption.equalsIgnoreCase("Y")) {
                    System.out.print("Enter file name (without extension): ");
                    String filename = scanner.nextLine();
                    saveCrewMemberReportToFile(selectedMember, missionsByMember, filename);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    // Helper to check if a crew member is already added
    private static boolean isCrewAlreadyAdded(CrewMember[] array, int size, CrewMember member) {
        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].getNumber() == member.getNumber()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Saves a crew member's mission report to a file
     */
    private static void saveCrewMemberReportToFile(CrewMember member, Mission[] missions, String baseFilename) {
        String filename = "crew_" + baseFilename + ".txt";
        String fullPath = Paths.get(filename).toAbsolutePath().toString();

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("=== Mission Report of " + member.getName() + " ===");
            writer.println("ID: " + member.getNumber());
            writer.println("Rank: " + member.getRank());
            if (member instanceof Pilot) {
                writer.println("Role: Pilot (License: " + ((Pilot) member).getIdPilot() + ")");
            }
            writer.println("\n=== Missions Participated ===");
            writer.printf("%-10s %-15s %-12s %-12s %-15s %-20s%n",
                    "Mission ID", "Type", "Start", "End", "Aircraft", "Purpose");
            writer.println("----------------------------------------------------------------");

            for (Mission m : missions) {
                writer.printf("%-10s %-15s %-12s %-12s %-15s %-20s%n",
                        m.getIdMission(),
                        m.getType(),
                        sdf.format(m.getStartDate()),
                        sdf.format(m.getEndDate()),
                        m.getAircraft().getIdAircraft(),
                        m.getPurpose());
            }

            writer.println("\nTotal missions: " + missions.length);
            System.out.println("Report saved at: " + fullPath);
        } catch (IOException e) {
            System.err.println("Error saving report: " + e.getMessage());
        }
    }

    // Checks if a crew member ID already exists
    private static boolean isCrewMemberIdExists(int id) {
        for (Mission mission : missionManagement.getAllMissions()) {
            for (CrewMember member : mission.getCrew()) {
                if (member.getNumber() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    // Method to add crew member via menu
    private static void addCrewMemberWithMenu(Mission mission, boolean isPilot) {
        try {
            System.out.println("\n=== Add " + (isPilot ? "Pilot" : "Crew Member") + " ===");

            int id;
            while (true) {
                System.out.print("Numeric ID (minimum 4 digits): ");
                id = scanner.nextInt();
                scanner.nextLine();

                if (String.valueOf(id).length() < 4) {
                    System.out.println("ID must have at least 4 digits. Please try again.");
                    continue;
                }

                if (isCrewMemberIdExists(id)) {
                    System.out.println("ID already exists, please enter another");
                } else {
                    break;
                }
            }

            System.out.print("Full name: ");
            String name = scanner.nextLine();

            Rank rank = selectRank("Crew member's rank");

            if (isPilot) {
                System.out.print("License number: ");
                String license = scanner.nextLine();
                Pilot pilot = new Pilot(name, rank);
                mission.addCrewMember(pilot);
            } else {
                CrewMember member = new CrewMember(name, rank);
                mission.addCrewMember(member);
            }

            System.out.println(name + " added as " + (isPilot ? "pilot" : "crew member") + " (" + rank + ")");

        } catch (InputMismatchException e) {
            System.out.println("ID must be numeric!");
            scanner.nextLine();
        }
    }

    // New method to query aircraft by number of missions
    private static void consultAircraftsByMissions() {
        try {
            System.out.print("\nEnter the year to query: ");
            int year = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            System.out.print("Enter the minimum number of missions: ");
            int minMissions = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            int count = aircraftManagement.getAircraftsWithMinMissionsInYear(year, minMissions, missionManagement);

            System.out.println("\n=== Query Result ===");
            System.out.println("Number of aircraft with at least " + minMissions +
                    " missions in " + year + ": " + count);

            // Show details of qualifying aircraft
            Aircraft[] allAircrafts = aircraftManagement.getAircrafts();
            if (allAircrafts.length > 0) {
                System.out.println("\nAircraft meeting the criteria:");
                for (Aircraft a : allAircrafts) {
                    if (a != null) {
                        int missionsCount = missionManagement.getAircraftMissionCountByYear(a, year);
                        if (missionsCount >= minMissions) {
                            System.out.println("- " + a.getIdAircraft() + " (" + a.getClass().getSimpleName() +
                                    "): " + missionsCount + " missions");
                        }
                    }
                }
            }

            // Option to save report
            System.out.print("\nWould you like to save this report? (Y/N): ");
            String saveOption = scanner.nextLine();
            if (saveOption.equalsIgnoreCase("Y")) {
                saveAircraftMissionsReport(year, minMissions, count);
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a number.");
            scanner.nextLine(); // Clear buffer on error
        }
    }

    private static void saveAircraftMissionsReport(int year, int minMissions, int count) {
        String filename = "missions_report_" + year + ".txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("=== Aircraft Missions Report ===");
            writer.println("Year: " + year);
            writer.println("Minimum missions: " + minMissions);
            writer.println("Total aircraft meeting criteria: " + count);
            writer.println("\n=== Details ===");

            Aircraft[] allAircrafts = aircraftManagement.getAircrafts();
            for (Aircraft a : allAircrafts) {
                if (a != null) {
                    int missionsCount = missionManagement.getAircraftMissionCountByYear(a, year);
                    if (missionsCount >= minMissions) {
                        writer.println("- " + a.getIdAircraft() + " (" + a.getClass().getSimpleName() +
                                "): " + missionsCount + " missions");
                    }
                }
            }

            System.out.println("Report saved at: " + Paths.get(filename).toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving report: " + e.getMessage());
        }
    }

    // Helper methods for menu selections
    private static MissionType selectMissionType() {
        System.out.println("\n=== Select Mission Type ===");
        MissionType[] types = MissionType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i]);
        }
        System.out.print("Choose (1-" + types.length + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return types[choice - 1];
    }

    private static MissionContext selectMissionContext() {
        System.out.println("\n=== Select Mission Context ===");
        MissionContext[] contexts = MissionContext.values();
        for (int i = 0; i < contexts.length; i++) {
            System.out.println((i + 1) + ". " + contexts[i]);
        }
        System.out.print("Choose (1-" + contexts.length + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return contexts[choice - 1];
    }

    private static Rank selectRank(String prompt) {
        System.out.println("\n=== " + prompt + " ===");
        Rank[] ranks = Rank.values();
        for (int i = 0; i < ranks.length; i++) {
            System.out.println((i + 1) + ". " + ranks[i]);
        }
        System.out.print("Choose (1-" + ranks.length + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return ranks[choice - 1];
    }

    private static void listCrewMembers(Mission mission) {
        System.out.println("\n=== Current Crew ===");
        CrewMember[] crew = mission.getCrew();
        if (crew.length == 0) {
            System.out.println("No crew members.");
            return;
        }

        for (CrewMember member : crew) {
            System.out.print("- ID: " + member.getNumber() + ", " + member.getName() + " (" + member.getRank() + ")");
            if (member instanceof Pilot) {
                System.out.print(" [Pilot - License: " + ((Pilot) member).getIdPilot() + "]");
            }
            System.out.println();
        }
    }

    // Lists all registered missions
    private static void listMissions() {
        Mission[] missions = missionManagement.getAllMissions();
        if (missions.length == 0) {
            System.out.println("No missions registered.");
        } else {
            for (Mission m : missions) {
                System.out.println("ID: " + m.getIdMission() +
                        ", Start Date: " + sdf.format(m.getStartDate()) +
                        ", End Date: " + sdf.format(m.getEndDate()) +
                        ", Aircraft: " + m.getAircraft().getIdAircraft() +
                        ", Type: " + m.getType() +
                        ", Context: " + m.getContext());
            }
        }
    }

    // Edit mission
    private static void editMission() {
        // Show available missions
        System.out.println("\n=== Available Missions ===");
        Mission[] allMissions = missionManagement.getAllMissions();
        if (allMissions.length == 0) {
            System.out.println("No missions registered.");
            return;
        }

        for (Mission m : allMissions) {
            System.out.println("ID: " + m.getIdMission() + " - " + m.getPurpose());
        }

        // Request mission ID
        System.out.print("\nMission ID to edit: ");
        String id = scanner.nextLine();

        // Verify ID has at least 4 digits
        if (id.length() < 4) {
            System.out.println("ID must have at least 4 digits.");
            return;
        }

        Mission mission = missionManagement.getMissionById(id);
        if (mission == null) {
            System.out.println("Mission not found.");
            return;
        }

        // Edit menu
        boolean editing = true;
        while (editing) {
            System.out.println("\n=== Editing Mission " + mission.getIdMission() + " ===");
            System.out.println("1. Edit commander/authorized");
            System.out.println("2. Edit description/purpose");
            System.out.println("3. Edit mission type");
            System.out.println("4. Manage crew");
            System.out.println("5. Edit aircraft");
            System.out.println("0. Finish editing");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    editCommander(mission);
                    break;
                case 2:
                    editDescription(mission);
                    break;
                case 3:
                    editMissionType(mission);
                    break;
                case 4:
                    manageCrewMembers(mission);
                    break;
                case 5:
                    editMissionAircraft(mission);
                    break;
                case 0:
                    editing = false;
                    System.out.println("Editing completed.");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    // Auxiliary methods for each edit functionality
    private static void editCommander(Mission mission) {
        System.out.print("New commander/authorized (current: " + mission.getAuthorizedBy() + "): ");
        String commander = scanner.nextLine();
        if (!commander.isEmpty()) {
            mission.setAuthorizedBy(commander);
        }
    }

    private static void editDescription(Mission mission) {
        System.out.print("New description/purpose (current: " + mission.getPurpose() + "): ");
        String description = scanner.nextLine();
        if (!description.isEmpty()) {
            mission.setPurpose(description);
        }
    }

    private static void editMissionType(Mission mission) {
        System.out.print("New mission type (current: " + mission.getType() + "): ");
        System.out.println("\nAvailable types: RECONNAISSANCE, RESCUE, COMBAT, PATROL");
        String typeInput = scanner.nextLine();
        if (!typeInput.isEmpty()) {
            try {
                MissionType newType = MissionType.valueOf(typeInput.toUpperCase());
                mission.setType(newType);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid mission type. Keeping current.");
            }
        }
    }

    private static void editMissionAircraft(Mission mission) {
        System.out.println("\n=== Available Aircraft ===");
        Aircraft[] aircrafts = aircraftManagement.getAircrafts();
        for (Aircraft a : aircrafts) {
            System.out.println("ID: " + a.getIdAircraft() + " - " + a.getClass().getSimpleName());
        }

        System.out.print("\nNew aircraft ID (current: " +
                (mission.getAircraft() != null ? mission.getAircraft().getIdAircraft() : "None") + "): ");
        String aircraftId = scanner.nextLine();

        if (!aircraftId.isEmpty()) {
            Aircraft aircraft = aircraftManagement.getAircraftById(aircraftId);
            if (aircraft != null) {
                mission.setAircraft(aircraft);
            } else {
                System.out.println("Aircraft not found!");
            }
        }
    }

    private static void manageCrewMembers(Mission mission) {
        boolean managingCrew = true;
        while (managingCrew) {
            System.out.println("\n=== Mission Crew ===");
            CrewMember[] crew = mission.getCrew();
            if (crew.length == 0) {
                System.out.println("No crew members.");
            } else {
                for (CrewMember member : crew) {
                    System.out.print("- " + member.getName() + " (" + member.getRank() + ")");
                    if (member instanceof Pilot) {
                        System.out.print(" - Pilot - License: " + ((Pilot) member).getIdPilot());
                    }
                    System.out.println();
                }
            }

            System.out.println("\n1. Add crew member");
            System.out.println("2. Add pilot");
            System.out.println("3. Remove crew member");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    addCrewMember(mission);
                    break;
                case 2:
                    addPilot(mission);
                    break;
                case 3:
                    removeCrewMember(mission);
                    break;
                case 0:
                    managingCrew = false;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void addCrewMember(Mission mission) {
        System.out.println("\nAdd Crew Member");
        int id;
        while (true) {
            System.out.print("Crew member ID (minimum 4 digits): ");
            id = scanner.nextInt();
            scanner.nextLine();

            if (String.valueOf(id).length() < 4) {
                System.out.println("ID must have at least 4 digits. Please try again.");
                continue;
            }
            if (isCrewMemberIdExists(id)) {
                System.out.println("ID already exists, please enter another");
            } else {
                break;
            }
        }

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Rank (CADET, LIEUTENANT, CAPTAIN, MAJOR, COLONEL): ");
        String rankInput = scanner.nextLine().toUpperCase();

        try {
            Rank rank = Rank.valueOf(rankInput);
            CrewMember member = new CrewMember(name, rank);
            mission.addCrewMember(member);
            System.out.println("Crew member added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid rank! Crew member not added.");
        }
    }

    private static void addPilot(Mission mission) {
        System.out.println("\nAdd Pilot");
        int id;
        while (true) {
            System.out.print("Pilot ID (minimum 4 digits): ");
            id = scanner.nextInt();
            scanner.nextLine();

            if (String.valueOf(id).length() < 4) {
                System.out.println("ID must have at least 4 digits. Please try again.");
                continue;
            }
            if (isCrewMemberIdExists(id)) {
                System.out.println("ID already exists, please enter another");
            } else {
                break;
            }
        }

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Rank (CADET, LIEUTENANT, CAPTAIN, MAJOR, COLONEL): ");
        String rankInput = scanner.nextLine().toUpperCase();

        try {
            Rank rank = Rank.valueOf(rankInput);
            Pilot pilot = new Pilot(name, rank);
            mission.addCrewMember(pilot);
            System.out.println("Pilot added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid rank! Pilot not added.");
        }
    }

    private static void removeCrewMember(Mission mission) {
        System.out.print("\nName of crew member to remove: ");
        String name = scanner.nextLine();

        if (mission.removeCrewMemberByName(name)) {
            System.out.println("Crew member removed successfully!");
        } else {
            System.out.println("Crew member not found!");
        }
    }

    // Removes a mission based on the provided ID
    private static void removeMission() {
        System.out.print("Mission ID to remove: ");
        String id = scanner.nextLine();
        boolean removed = missionManagement.removeMission(id);
        if (removed) {
            System.out.println("Mission removed.");
        } else {
            System.out.println("Mission not found.");
        }
    }

    /**
     * Displays the mission report for a specific aircraft
     */
    private static void showAircraftMissionReport() {
        // List available aircraft
        System.out.println("\n=== Available Aircraft ===");
        Aircraft[] aircrafts = aircraftManagement.getAircrafts();
        if (aircrafts.length == 0) {
            System.out.println("No aircraft registered.");
            return;
        }
        for (Aircraft a : aircrafts) {
            System.out.println("ID: " + a.getIdAircraft() + " - " +
                    a.getClass().getSimpleName() + " (" +
                    a.getManufacturer() + ")");
        }

        // Request aircraft ID
        System.out.print("\nEnter the aircraft ID for the report: ");
        String aircraftId = scanner.nextLine();
        Aircraft aircraft = aircraftManagement.getAircraftById(aircraftId);
        if (aircraft == null) {
            System.out.println("Aircraft not found!");
            return;
        }

        // Generate and display report
        String report = missionManagement.generateAircraftMissionReport(aircraft);
        System.out.println(report);

        // Option to save to file
        System.out.print("Would you like to save this report to a file? (Y/N): ");
        String saveOption = scanner.nextLine();
        if (saveOption.equalsIgnoreCase("Y")) {
            System.out.print("Enter file name (without extension): ");
            String filename = scanner.nextLine();
            saveReportToFile(report, "report_" + filename + ".txt");
        }
    }

    /**
     * Saves the report to a text file
     */
    private static void saveReportToFile(String report, String filename) {
        // Show the absolute path where the file will be saved
        String fullPath = Paths.get(filename).toAbsolutePath().toString();
        System.out.println("File will be saved at: " + fullPath);

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.print(report);
            System.out.println("Report saved at: " + fullPath);
        } catch (IOException e) {
            System.err.println("Error saving report: " + e.getMessage());
        }
    }
}

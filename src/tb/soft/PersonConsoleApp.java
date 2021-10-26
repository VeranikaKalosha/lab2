package tb.soft;

import java.util.*;

/**
 * Program: Aplikacja działająca w oknie konsoli, która umożliwia testowanie
 * operacji wykonywanych na obiektach klasy Person.
 * Plik: PersonConsoleApp.java
 * <p>
 * Autor: Paweł Rogaliński
 * Data: październik 2018 r.
 */
public class PersonConsoleApp {

    private static final String GREETING_MESSAGE =
            "Program Person - wersja konsolowa\n" +
                    "Autor: Paweł Rogaliński\n" +
                    "Data:  październik 2018 r.\n";

    private static final String KOLEKCJE =
            "    W Y B Ó R   K O L E K C J I  \n" +
                    "1 - HashSet \n" +
                    "2 - TreeSet        \n" +
                    "3 - ArrayList  \n" +
                    "4 - LinkedList  \n" +
                    "5 - HashMap \n" +
                    "6 - TreeMap     \n";

    private static final String MENU =
            "    M E N U   G Ł Ó W N E  \n" +
                    "1 - Podaj dane nowej osoby \n" +
                    "2 - Usuń dane osoby        \n" +
                    "3 - Iteracja   \n" +
                    "0 - Zakończ program        \n";


    /**
     * ConsoleUserDialog to pomocnicza klasa zawierająca zestaw
     * prostych metod do realizacji dialogu z użytkownikiem
     * w oknie konsoli tekstowej.
     */
    private static final ConsoleUserDialog UI = new ConsoleUserDialog();


    public static void main(String[] args) {
        // Utworzenie obiektu aplikacji konsolowej
        // oraz uruchomienie głównej pętli aplikacji.
        PersonConsoleApp application = new PersonConsoleApp();
        application.runMainLoop();
    }


    /*
     *  Referencja do obiektu, który zawiera dane aktualnej osoby.
     */
    private Person currentPerson = null;


    /*
     *  Metoda runMainLoop wykonuje główną pętlę aplikacji.
     *  UWAGA: Ta metoda zawiera nieskończoną pętlę,
     *         w której program się zatrzymuje aż do zakończenia
     *         działania za pomocą metody System.exit(0);
     */
    public void runMainLoop() {
        UI.printMessage(GREETING_MESSAGE);
        HashSet<Person> hashSet = null;
        TreeSet<Person> treeSet = null;
        ArrayList<Person> arrayList = null;
        LinkedList<Person> linkedList = null;
        HashMap<Person, Integer> hashMap = null;
        TreeMap<Person, Integer> treeMap = null;
        // create Map
        // initialize with null
        switch (UI.enterInt(KOLEKCJE + "==>> ")) {
            case 1:
                hashSet = new HashSet<>();
                break;
            case 2:
                treeSet = new TreeSet<>();
                break;
            case 3:
                arrayList = new ArrayList<>();
                break;
            case 4:
                linkedList = new LinkedList<>();
                break;
            case 5:
                hashMap = new HashMap<>();
                break;
            case 6:
                treeMap = new TreeMap<>();
                break;
        }


        while (true) {
            UI.clearConsole();
            // showCurrentPerson();

            //try {
            switch (UI.enterInt(MENU + "==>> ")) {
                case 1:
                    // utworzenie nowej osoby
                    // add condition to work with not null collection
                    currentPerson = createNewPerson();
                    if (currentPerson == null) break;
                    if (hashSet != null)
                        hashSet.add(currentPerson);
                    if (treeSet != null)
                        treeSet.add(currentPerson);
                    if (arrayList != null)
                        arrayList.add(currentPerson);
                    if (linkedList != null)
                        linkedList.add(currentPerson);
                    if (hashMap !=null)
                        hashMap.put(currentPerson,null);
                    if (treeMap !=null)
                        treeMap.put(currentPerson,null);
                    break;
                case 2:
                    if (hashSet != null)
                        hashSet.remove(createNewPerson());
                    if (treeSet != null)
                        treeSet.remove(createNewPerson());
                    if (arrayList != null)
                        arrayList.remove(createNewPerson());
                    if (linkedList != null)
                        linkedList.remove(createNewPerson());
                    if (hashMap !=null)
                        hashMap.remove(createNewPerson());
                    if (treeMap !=null)
                        treeMap.remove(createNewPerson());
                    // usunięcie danych aktualnej osoby.

                    UI.printInfoMessage("Dane aktualnej osoby zostały usunięte");
                    break;

                case 3:
                    if (hashSet != null)
                        hashSet.forEach(System.out::println);
                    if (treeSet != null)
                        treeSet.forEach(System.out::println);
                    if (arrayList != null)
                        arrayList.forEach(System.out::println);
                    if (linkedList != null)
                        linkedList.forEach(System.out::println);
                    if (hashMap !=null)
                        hashMap.keySet().forEach(System.out::println);
                    if (treeMap !=null)
                        treeMap.keySet().forEach(System.out::println);

                    break;
                case 0:
                    // zakończenie działania programu
                    UI.printInfoMessage("\nProgram zakończył działanie!");
                    System.exit(0);
            } // koniec instrukcji switch
        }
    } // koniec pętli while


    /*
     *  Metoda wyświetla w oknie konsoli dane aktualnej osoby
     *  pamiętanej w zmiennej currentPerson.
     */
    void showCurrentPerson() {
        showPerson(currentPerson);
    }


    /*
     * Metoda wyświetla w oknie konsoli dane osoby reprezentowanej
     * przez obiekt klasy Person
     */
    static void showPerson(Person person) {
        StringBuilder sb = new StringBuilder();

        if (person != null) {
            sb.append("Aktualna osoba: \n")
                    .append("      Imię: ").append(person.getFirstName()).append("\n")
                    .append("  Nazwisko: ").append(person.getLastName()).append("\n")
                    .append("   Rok ur.: ").append(person.getBirthYear()).append("\n")
                    .append("Stanowisko: ").append(person.getJob()).append("\n");
        } else
            sb.append("Brak danych osoby\n");
        UI.printMessage(sb.toString());
    }


    /*
     * Metoda wczytuje w konsoli dane nowej osoby, tworzy nowy obiekt
     * klasy Person i wypełnia atrybuty wczytanymi danymi.
     * Walidacja poprawności danych odbywa się w konstruktorze i setterach
     * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
     * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
     */
    static Person createNewPerson() {
        String first_name = UI.enterString("Podaj imię: ");
        String last_name = UI.enterString("Podaj nazwisko: ");
        String birth_year = UI.enterString("Podaj rok ur.: ");
        UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
        String job_name = UI.enterString("Podaj stanowisko: ");
        Person person;
        try {
            // Utworzenie nowego obiektu klasy Person oraz
            // ustawienie wartości wszystkich atrybutów.
            person = new Person(first_name, last_name);
            person.setBirthYear(birth_year);
            person.setJob(job_name);
        } catch (PersonException e) {
            // Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
            // gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
            // poszczególnych atrybutów.
            // Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
            UI.printErrorMessage(e.getMessage());
            return null;
        }
        return person;
    }


}  // koniec klasy PersonConsoleApp

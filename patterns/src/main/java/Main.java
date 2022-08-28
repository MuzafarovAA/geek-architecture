import adapter.*;
import bridge.*;
import composite.*;
import decorator.*;
import facade.Computer;
import facade.ComputerFacade;
import proxy.LabDoor;
import proxy.SecuredDoor;

public class Main {

    public static void main(String[] args) {

        System.out.println("Adapter pattern:");
        adapterExamples();
        System.out.println("-----");

        System.out.println("Bridge pattern:");
        bridgeExamples();
        System.out.println("-----");

        System.out.println("Composite pattern:");
        compositeExamples();
        System.out.println("-----");

        System.out.println("Decorator pattern:");
        decoratorExamples();
        System.out.println("-----");

        System.out.println("Facade pattern:");
        facadeExamples();
        System.out.println("-----");

        System.out.println("Facade pattern:");
        proxyExamples();

    }

    private static void adapterExamples() {

        Hunter hunter = new Hunter();
        Lion africanLion = new AfricanLion();
        Lion asianLion = new AsianLion();
        WildDog wildDog = new WildDog();
        WildDogAdapter wildDogAdapter = new WildDogAdapter(wildDog);

        hunter.hunt(africanLion);
        hunter.hunt(asianLion);
        hunter.hunt(wildDogAdapter);

    }

    private static void bridgeExamples() {

        Theme aquaTheme = new AquaTheme();
        Theme darkTheme = new DarkTheme();
        Theme lightTheme = new LightTheme();
        WebPage aboutAqua = new About(aquaTheme);
        WebPage aboutDark = new About(darkTheme);
        WebPage aboutLight = new About(lightTheme);
        WebPage careersAqua = new Careers(aquaTheme);
        WebPage careersDark = new Careers(darkTheme);
        WebPage careersLight = new Careers(lightTheme);

        System.out.println(aboutAqua.getContent());
        System.out.println(aboutDark.getContent());
        System.out.println(aboutLight.getContent());

        System.out.println(careersAqua.getContent());
        System.out.println(careersDark.getContent());
        System.out.println(careersLight.getContent());

    }

    public static void compositeExamples() {

        Employee john = new Developer("John Doe", 12000);
        Employee jane = new Designer("Jane Doe", 15000);

        Organization organization = new Organization();
        organization.addEmployee(john);
        organization.addEmployee(jane);

        System.out.println("Net salaries: " + organization.getNetSalaries());

    }

    private static void decoratorExamples() {

        Coffee someCoffee = new SimpleCoffee();
        System.out.println(someCoffee.getCost());
        System.out.println(someCoffee.getDescription());

        someCoffee = new MilkCoffee(someCoffee);
        System.out.println(someCoffee.getCost());
        System.out.println(someCoffee.getDescription());

        someCoffee = new WhipCoffee(someCoffee);
        System.out.println(someCoffee.getCost());
        System.out.println(someCoffee.getDescription());

        someCoffee = new VanillaCoffee(someCoffee);
        System.out.println(someCoffee.getCost());
        System.out.println(someCoffee.getDescription());

    }

    private static void facadeExamples() {

        ComputerFacade computer = new ComputerFacade(new Computer());
        computer.turnOn();
        computer.turnOff();

    }

    private static void proxyExamples() {

        SecuredDoor door = new SecuredDoor(new LabDoor());
        door.open("invalid");
        door.open("$ecr@t");
        door.close();

    }

}

package machine;

import java.util.Scanner;

enum CMState { MAIN, BUY, FILL_WATER, FILL_MILK, FILL_BEANS, FILL_CUPS, EXIT }

public class CoffeeMachine {

    public static final int ESP_WATER = 250;
    public static final int ESP_BEANS = 16;
    public static final int ESP_COST = 4;
    public static final int LAT_WATER = 350;
    public static final int LAT_MILK = 75;
    public static final int LAT_BEANS = 20;
    public static final int LAT_COST = 7;
    public static final int CAP_WATER = 200;
    public static final int CAP_MILK = 100;
    public static final int CAP_BEANS = 12;
    public static final int CAP_COST = 6;

    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int cash;

    public CoffeeMachine(int water, int milk, int beans, int cups, int cash) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.cash = cash;
    }

    public CMState getCurrentState() {
        return currentState;
    }

    private CMState currentState = CMState.MAIN;

    public void showMachineState() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d of water\n", this.water);
        System.out.printf("%d of milk\n", this.milk);
        System.out.printf("%d of coffee beans\n", this.beans);
        System.out.printf("%d of disposable cups\n", this.cups);
        System.out.printf("%d of money\n", this.cash);
    }

    public void getOrder(String input) {
        switch (currentState) {
            case MAIN:
                switch (input) {
                    case "buy":
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, " +
                                "3 - cappuccino, back - to main menu");
                        this.currentState = CMState.BUY;
                        break;
                    case "fill":
                        System.out.println("Write how many ml of water do you want to add:");
                        this.currentState = CMState.FILL_WATER;
                        break;
                    case "take":
                        System.out.printf("I gave you $%d\n", this.cash);
                        this.cash = 0;
                        break;
                    case "remaining":
                        this.showMachineState();
                        System.out.println("Write action (buy, fill, take, remaining, exit)");
                        break;
                    case "exit":
                        this.currentState = CMState.EXIT;
                }
                break;
            case BUY:
                switch (input) {
                    case "1":
                        if (this.water - ESP_WATER < 0 || this.beans - ESP_BEANS < 0 || this.cups - 1 < 0) {
                            System.out.println("Not enough resources to make a cup of coffee!");
                            break;
                        } else {
                            System.out.println("I have enough resources, making you a coffee!");
                        }
                        this.water -= ESP_WATER;
                        this.beans -= ESP_BEANS;
                        this.cups--;
                        this.cash += ESP_COST;
                        break;
                    case "2":
                        if (this.water - LAT_WATER < 0 || this.milk - LAT_MILK < 0
                                || this.beans - LAT_BEANS < 0 || this.cups - 1 < 0) {
                            System.out.println("Not enough resources to make a cup of coffee!");
                            break;
                        } else {
                            System.out.println("I have enough resources, making you a coffee!");
                        }
                        this.water -= LAT_WATER;
                        this.milk -= LAT_MILK;
                        this.beans -= LAT_BEANS;
                        this.cups--;
                        this.cash += LAT_COST;
                        break;
                    case "3":
                        if (this.water - CAP_WATER < 0 || this.milk - CAP_MILK < 0
                                || this.beans - CAP_BEANS < 0 || this.cups - 1 < 0) {
                            System.out.println("Not enough resources to make a cup of coffee!");
                        } else {
                            System.out.println("I have enough resources, making you a coffee!");
                        }
                        this.water -= CAP_WATER;
                        this.milk -= CAP_MILK;
                        this.beans -= CAP_BEANS;
                        this.cups--;
                        this.cash += CAP_COST;
                        break;
                }
                this.currentState = CMState.MAIN;
                System.out.println("Write action (buy, fill, take, remaining, exit)");
                break;
            case FILL_WATER:
                this.water += Integer.parseInt(input);
                System.out.println("Write how many ml of milk do you want to add:");
                this.currentState = CMState.FILL_MILK;
                break;
            case FILL_MILK:
                this.milk += Integer.parseInt(input);
                System.out.println("Write how many grams of coffee beans do you want to add:");
                this.currentState = CMState.FILL_BEANS;
                break;
            case FILL_BEANS:
                this.beans += Integer.parseInt(input);
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                this.currentState = CMState.FILL_CUPS;
                break;
            case FILL_CUPS:
                this.cups += Integer.parseInt(input);
                this.currentState = CMState.MAIN;
                System.out.println("Write action (buy, fill, take, remaining, exit)");
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);
        System.out.println("Write action (buy, fill, take, remaining, exit)");
        while (coffeeMachine.getCurrentState() != CMState.EXIT) {
            String action = scanner.nextLine();
            coffeeMachine.getOrder(action);
        }
    }
}

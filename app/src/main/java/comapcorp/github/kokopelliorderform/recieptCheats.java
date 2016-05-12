package comapcorp.github.kokopelliorderform;

/**
 * Created by Savannah on 5/10/2016.
 */
public class recieptCheats {

    //HEADER
    public void printHeader (){
        System.out.println("Your Kokopelli Reciept");
                /** Possible ideas for the header include: Cashier name, Time, Day, Title, etc. **/
        System.out.println();
        System.out.println();
        System.out.println();
    }

    //ADDS TWO NUMBERS AND RETURNS THE TOTAL, PRINTS THE WORK DONE (used for printing the final reciept?)
    public int addUp(int num1, int num2){
        int total = num1 + num2;
        System.out.println("Total:" + formatDollars(num1) + " + {new item)@strings/itemInQuestion " + formatDollars(num2) + " = " + formatDollars(total));
        return total;
    }

    //SUBTRACTS TWO NUMBERS AND RETURNS THE TOTAL, PRINTS THE WORK DONE, (used for sales, rebates, errors, refunds, etc.)
    public int subtractEm (int num1, int num2) {
        int total = num1 - num2;
        System.out.println("Total:" + formatDollars(num1) + " - change (@strings/) " + formatDollars(num2) + " = " + formatDollars(total));
        return total;
    }

    //RETURNS STRING IN A DOLLAR FORMAT (10.44 --> $10.44), All numbers should be formatted this way
    public String formatDollars(int total){
        String finalFormat = "";
        System.out.printf("$%,d", total);
        return finalFormat;
    }

    //FOOTER
    public void printFooter(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Have a nice day!");
        /** Possible ideas for the header include: Cashier name, Time, Day, Title, etc. (ANYTHING NOT IN HEADER) **/

    }

}

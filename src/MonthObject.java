public class MonthObject {

    private String itemName;
    private Boolean isExpense;
    private int quantity;
    private int sumOfOne;

    MonthObject(String itemName, Boolean isExpense, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }


    public int getQuantity() {
        return quantity;
    }

    public int getSumOfOne() {
        return sumOfOne;
    }

    public String getItemName() {
        return itemName;
    }

    public Boolean getIsExpense() {
        return isExpense;
    }
}

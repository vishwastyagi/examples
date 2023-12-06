import java.util.ArrayList;
import java.util.List;

/*
Tesco gets millions of orders every day with an average basket size of 100 items. Tesco Business has got some regulations around selling products online and in stores. These regulations are mandatory from legal and business perspective to enforce for all order transactions.
You are given an order with a list of products in the shopping cart/basket with productid, product Category and quantity. And also, Restriction Rules on Qty and Qty/Category.
Example:
Ordered items in the shopping cart/basket
Item-1 -> productid=1, category=Paracetamol, quantity=3
Item-2 -> productid=2, category=analgesic, quantity=3
Item-3 -> productid=3, category=chocolate, quantity=8
Item-4 -> productid=4, category= Paracetamol, quantity=2
Business Restriction rules:
Cannot buy more than 10 Quantity of any products - BulkBuyLimit
Cannot buy more than 5 Quantity of paracetamol products – BulkBuyLimitCategory

Write a restriction rule engine to run the restriction check against the shopping cart/basket and return the status as to MET/BREACHED indicating restriction status for the given restriction rules.
For the above given example, the restriction status returned would be MET.

Here I coded for
 */
class ShoppingCartItem {
    private int productId;
    private String category;
    private int quantity;

    public ShoppingCartItem(int productId, String category, int quantity) {
        this.productId = productId;
        this.category = category;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class ShoppingCart {
    private List<ShoppingCartItem> shoppingCartItemList = new ArrayList<>();

    public List<ShoppingCartItem> getShoppingCartItemList() {
        return shoppingCartItemList;
    }

    public void setShoppingCartItemList(List<ShoppingCartItem> shoppingCartItemList) {
        this.shoppingCartItemList = shoppingCartItemList;
    }
}

interface RulesStrategy {
    boolean validateRestrictions(ShoppingCartItem shoppingCartItem);
}

class BulkBuyLimitStrategy implements RulesStrategy {
    private int quantity;

    public BulkBuyLimitStrategy(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean validateRestrictions(ShoppingCartItem shoppingCartItem) {
        if (shoppingCartItem.getQuantity() <= quantity)
            return true;
        return false;
    }
}

class BulkBuyLimitCategoryStartegy implements RulesStrategy{
    private int quantity;
    private List<String> categories;

    public BulkBuyLimitCategoryStartegy(int quantity, List<String> categories) {
        this.quantity = quantity;
        this.categories = categories;
    }

    @Override
    public boolean validateRestrictions(ShoppingCartItem shoppingCartItem) {
        if(categories.contains(shoppingCartItem.getCategory())){
            if(shoppingCartItem.getQuantity()<=quantity){
                return true;
            }else {
                return false;
            }
        }
        return true;
    }
}
class RulesChecker{
    List<RulesStrategy> rulesStrategyList;

    public RulesChecker(List<RulesStrategy> rulesStrategyList) {
        this.rulesStrategyList = rulesStrategyList;
    }
    public List<RulesStrategy> getRulesStrategyList() {
        return rulesStrategyList;
    }

    public void addRulesStartegy(RulesStrategy rulesStrategy) {
        this.rulesStrategyList.add(rulesStrategy);
    }

    /*public boolean validateRulesForShoppingItem(ShoppingCartItem shoppingCartItem){
        for(RulesStrategy rulesStrategy:rulesStrategyList){
            if(!rulesStrategy.validateRestrictions(shoppingCartItem)){
                return false;
            }
        }
        return true;
    }*/
    public boolean validateRulesForShoppingItem(ShoppingCartItem shoppingCartItem) {
        return rulesStrategyList.stream()
                .allMatch(strategy -> strategy.validateRestrictions(shoppingCartItem));
    }
}
public class ShoppingCartRulesExecutor {
    public static void main(String[] args) {
        List<String> categoryList=new ArrayList<>();
        categoryList.add("Paracetamol");
        RulesStrategy bulkBuyLimitCategoryStartegy = new BulkBuyLimitCategoryStartegy(5,categoryList);
        RulesStrategy bulkBuyLimitStrategy = new BulkBuyLimitStrategy(10);
        List<RulesStrategy> rulesStrategyList = new ArrayList<>();
        rulesStrategyList.add(bulkBuyLimitCategoryStartegy);
        rulesStrategyList.add(bulkBuyLimitStrategy);
        RulesChecker rulesChecker = new RulesChecker(rulesStrategyList);

        List<ShoppingCartItem> shoppingCartItemList = new ArrayList<>();
        shoppingCartItemList.add(new ShoppingCartItem(1,"Paracetamol",3));
        shoppingCartItemList.add(new ShoppingCartItem(2,"analgesic",3));
        shoppingCartItemList.add(new ShoppingCartItem(3,"chocolate",8));
        shoppingCartItemList.add(new ShoppingCartItem(4,"Paracetamol",2));
        shoppingCartItemList.add(new ShoppingCartItem(5,"Paracetamol",20));

        /*for(ShoppingCartItem shoppingCartItem:shoppingCartItemList){
            boolean isRestrictionMet = rulesChecker.validateRulesForShoppingItem(shoppingCartItem);
            if(isRestrictionMet){
                System.out.println("MET");
            }else{
                System.out.println("BREACHED");
            }
        }*/
        shoppingCartItemList.forEach(shoppingCartItem -> {
            boolean isRestrictionMet = rulesChecker.validateRulesForShoppingItem(shoppingCartItem);
            System.out.println(isRestrictionMet ? "MET" : "BREACHED");
        });
    }
}
/*
Test Cases:-
1. Met condition
2. Breached Condition with category and quantity
3. test case for handling edge cases like no shopping cart, no rules
 */
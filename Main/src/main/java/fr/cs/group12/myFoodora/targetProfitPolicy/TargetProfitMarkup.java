package fr.cs.group12.myFoodora.targetProfitPolicy;

import fr.cs.group12.myFoodora.myFoodora.MyFoodoraSystem;
/**
 * The TargetProfitMarkup class implements the TargetProfitPolicy interface to adjust the markup percentage based on a target profit.
 */
public class TargetProfitMarkup implements TargetProfitPolicy {
    /**
     * Applies the target profit policy to adjust the markup percentage.
     * @param myfoodorasystem The MyFoodoraSystem instance used to access system data and settings.
     * @param targetProfit The target profit to achieve.
     */
    @Override
    public void apply(MyFoodoraSystem myfoodorasystem, double targetProfit) {
        double totalIncome = MyFoodoraSystem.computeIncomeLastMonth();
        double serviceFee = MyFoodoraSystem.getServiceFee();
        double deliveryCost = MyFoodoraSystem.getDeliveryCost();
        int totalOrders = MyFoodoraSystem.getNumberOfOrdersLastMonth();

        double markupPercentage = (targetProfit + totalOrders * deliveryCost - totalOrders * serviceFee) / totalIncome;
        MyFoodoraSystem.setMarkupPercentage(markupPercentage);
    }
}


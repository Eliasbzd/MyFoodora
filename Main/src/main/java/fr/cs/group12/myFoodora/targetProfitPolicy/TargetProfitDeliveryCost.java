package fr.cs.group12.myFoodora.targetProfitPolicy;

import fr.cs.group12.myFoodora.myFoodora.MyFoodoraSystem;
/**
 * The TargetProfitDeliveryCost class implements the TargetProfitPolicy interface to adjust the delivery cost based on a target profit.
 */
public class TargetProfitDeliveryCost implements TargetProfitPolicy {
    /**
     * Applies the target profit policy to adjust the delivery cost.
     * @param myfoodorasystem The MyFoodoraSystem instance used to access system data and settings.
     * @param targetProfit The target profit to achieve.
     */
    @Override
    public void apply(MyFoodoraSystem myfoodorasystem, double targetProfit) {
        double totalIncome = MyFoodoraSystem.computeIncomeLastMonth();
        double serviceFee = MyFoodoraSystem.getServiceFee();
        double markupPercentage = MyFoodoraSystem.getMarkupPercentage();
        int totalOrders = MyFoodoraSystem.getNumberOfOrdersLastMonth();

        double deliveryCost = (totalIncome * markupPercentage + serviceFee * totalOrders - targetProfit) / totalOrders;
        MyFoodoraSystem.setDeliveryCost(deliveryCost);
    }
}


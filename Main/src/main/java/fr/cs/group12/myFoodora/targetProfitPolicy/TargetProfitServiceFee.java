package fr.cs.group12.myFoodora.targetProfitPolicy;

import fr.cs.group12.myFoodora.myFoodora.MyFoodoraSystem;
/**
 * The TargetProfitServiceFee class implements the TargetProfitPolicy interface to adjust the service fee
 * according to a target profit policy.
 */
public class TargetProfitServiceFee implements TargetProfitPolicy {
    /**
     * Applies the target profit policy to adjust the service fee.
     *
     * @param myfoodorasystem The MyFoodoraSystem instance used to access system data and settings.
     * @param targetProfit    The target profit to achieve.
     */
    @Override
    public void apply(MyFoodoraSystem myfoodorasystem, double targetProfit) {
        double totalIncome = MyFoodoraSystem.computeIncomeLastMonth();
        double markupPercentage = MyFoodoraSystem.getMarkupPercentage();
        double deliveryCost = MyFoodoraSystem.getDeliveryCost();
        int totalOrders = MyFoodoraSystem.getNumberOfOrdersLastMonth();

        double serviceFee = (targetProfit + totalOrders * deliveryCost - totalIncome * markupPercentage) / totalOrders;
        MyFoodoraSystem.setServiceFee(serviceFee);
    }
}


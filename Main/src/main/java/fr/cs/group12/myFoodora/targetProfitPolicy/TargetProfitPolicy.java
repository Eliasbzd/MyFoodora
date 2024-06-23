package fr.cs.group12.myFoodora.targetProfitPolicy;

import fr.cs.group12.myFoodora.myFoodora.MyFoodoraSystem;
/**
 * The TargetProfitPolicy interface defines the method signature for applying a target profit policy.
 */
public interface TargetProfitPolicy {
    /**
     * Applies the target profit policy to adjust system settings.
     * @param myFoodoraSystem The MyFoodoraSystem instance used to access system data and settings.
     * @param targetProfit The target profit to achieve.
     */
    void apply(MyFoodoraSystem myFoodoraSystem, double targetProfit);
}


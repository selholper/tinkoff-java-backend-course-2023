package edu.hw11.Task2;

import net.bytebuddy.agent.ByteBuddyAgent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestChangeBehaviorArithmeticUtilsClassInFlight {
    @Test
    public void testReloadM_shouldChangeSumMethodInFlight() {
        ByteBuddyAgent.install();
        ClassBehaviorReloader.reload();
        Assertions.assertThat(ArithmeticUtils.sum(11, 4999)).isEqualTo(54989);
    }
}

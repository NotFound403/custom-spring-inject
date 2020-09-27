package cn.felord.custom;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

import static com.alibaba.csp.sentinel.slots.block.RuleConstant.CONTROL_BEHAVIOR_DEFAULT;


@SpringBootApplication
public class CustomSpringInjectApplication {


    public static void main(String[] args) {
        SpringApplication.run(CustomSpringInjectApplication.class, args);
    }


    @Bean
    public SentinelResourceAspect sentinelResourceAspect(){

        // 规则对应的类为FlowRule，用List保存，可以有多个规则
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule1 = new FlowRule();
        rule1.setResource("bar");
        // QPS为20
        rule1.setCount(2);
//        rule1.setLimitApp(appName);
        //限流的类型
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setControlBehavior(CONTROL_BEHAVIOR_DEFAULT);
        rules.add(rule1);
        FlowRuleManager.loadRules(rules);
        return new SentinelResourceAspect();
    }

}

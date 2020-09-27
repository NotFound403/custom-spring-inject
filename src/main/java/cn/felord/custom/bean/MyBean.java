package cn.felord.custom.bean;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foo")
public class MyBean {

    @SentinelResource(value = "bar", entryType = EntryType.IN,resourceType = 2)
    @GetMapping("/bar")
    public String bar() {
        return "bar";
    }

}

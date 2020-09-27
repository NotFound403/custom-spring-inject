package cn.felord.custom.bean;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@ControllerAdvice
@Order(0)
public class SentinelSpringMvcBlockHandlerConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(BlockException.class)
    @ResponseBody
    public Object sentinelBlockHandler(HttpServletRequest request, BlockException e) {
        logger.warn("Blocked by Sentinel: {}", e.getRule());
        // Return the customized result.
        HashMap<String, Object> map = new HashMap<>();
        map.put("path",request.getServletPath());
        map.put("msg","limited");
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(map);
    }
}

package com.shy.springbootskeleton.aop;


import com.shy.springbootskeleton.config.db.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Order(1)
@Configuration
@Slf4j
public class DataSourceAspect {
    @Before("@annotation(transactional)")
    public void switchDataSourceType(Transactional transactional){
        if(transactional.readOnly()){
            DataSourceContextHolder.read();
            log.info("dataSource切换到: Read");
        }else{
            DataSourceContextHolder.write();
            log.info("dataSource切换到: Write");
        }

    }
}

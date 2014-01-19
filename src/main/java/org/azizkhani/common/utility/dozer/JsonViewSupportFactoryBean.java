package org.azizkhani.common.utility.dozer;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.google.common.collect.Lists;

/**
 * Modified Spring 3.1's internal Return value handlers, and wires up a decorator
 * to add support for @JsonView
 * 
 * @author martypitt
 *
 */
public class JsonViewSupportFactoryBean implements InitializingBean {

    @Autowired
    private RequestMappingHandlerAdapter adapter;
    
    @Autowired(required=true)
    private Mapper dozerBeanMapper;
    
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> handlers = Lists.newArrayList(adapter.getReturnValueHandlers());
        decorateHandlers(handlers);
        adapter.setReturnValueHandlers(handlers);
    }
    
    private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        for (HandlerMethodReturnValueHandler handler : handlers) {
            if (handler instanceof RequestResponseBodyMethodProcessor)
            {
                ViewInjectingReturnValueHandler decorator = new ViewInjectingReturnValueHandler(handler,dozerBeanMapper);
                int index = handlers.indexOf(handler);
                handlers.set(index, decorator);
                //log.info("JsonView decorator support wired up");
                break;
            }
        }        
    }

}
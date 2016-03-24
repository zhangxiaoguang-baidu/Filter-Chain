package filterchain;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Author  : zhangxiaoguang
 * email   : zhangxiaoguang03@meituan.com
 * Date    : 15/8/10
 * Time    : 下午9:06
 * ---------------------------------------
 * Desc    : 用来处理标注了指定annotation 的 class
 */
@Component
public class HandlerAnnotationProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        HandlerInfo handlerInfoAnnotation = AnnotationUtils.findAnnotation(bean.getClass(), HandlerInfo.class);

        if (handlerInfoAnnotation != null) {
            String[] handlerChainType = handlerInfoAnnotation.handlerChainType();
            if (handlerChainType == null || handlerChainType.length == 0 || !(bean instanceof Handler)) {
                return bean;
            }
            int[] orders = handlerInfoAnnotation.order();
            if (orders == null || orders.length == 0) {
                orders = new int[handlerChainType.length];
            } else if (orders.length < handlerChainType.length) {
                int[] tmpOrders = Arrays.copyOf(orders, orders.length);
                orders = new int[handlerChainType.length];
                System.arraycopy(tmpOrders, 0, orders, 0, tmpOrders.length);
            }
            final String flowType = handlerInfoAnnotation.flowType();

            for (int i = 0; i < handlerChainType.length; i++) {
                Map<String, List<HandlerConfig>> flowStarterMap = StorageTemp.handlerChainListMap.get(flowType);
                if (flowStarterMap == null) {
                    flowStarterMap = Maps.newHashMap();
                    StorageTemp.handlerChainListMap.put(flowType, flowStarterMap);
                }

                List<HandlerConfig> chainHandlerList = flowStarterMap.get(handlerChainType[i]);
                if (chainHandlerList == null) {
                    chainHandlerList = Lists.newArrayList();
                    flowStarterMap.put(handlerChainType[i], chainHandlerList);
                }

                chainHandlerList.add(new HandlerConfig((Handler<HandlerContext<?>, OutputInitial>) bean, orders[i]));
            }

            return bean;
        }
        List<Class<?>> interfaceList = Arrays.asList(bean.getClass().getInterfaces());
        boolean isImplement = interfaceList.contains(HandlerListChainAware.class);
        if (isImplement) {
            HandlerListChainAware handlerChainListAware = (HandlerListChainAware) bean;
            StorageTemp.flowStarterMap.put(handlerChainListAware.flowType(), handlerChainListAware);
        }

        return bean;
    }
}

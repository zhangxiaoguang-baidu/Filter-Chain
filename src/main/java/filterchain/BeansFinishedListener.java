package filterchain;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Author  : zhangxiaoguang
 * email   : zhangxiaoguang03@meituan.com
 * Date    : 16/3/23
 * Time    : 上午2:46
 * ---------------------------------------
 * Desc    :
 */
@Component
public class BeansFinishedListener implements ApplicationListener<ContextRefreshedEvent> {
    protected static final Logger log = LoggerFactory.getLogger(BeansFinishedListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event == null || event.getApplicationContext().getParent() != null) {
            return;
        }

        Map<String, Map<String, List<Handler<HandlerContext<?>, OutputInitial>>>>
                handlerChainList = reOrder();

        for (String flowType : handlerChainList.keySet()) {
            StorageTemp.flowStarterMap.get(flowType).setHandlerListChainMap(handlerChainList.get(flowType));
        }

        destroy();
    }

    private Map<String, Map<String, List<Handler<HandlerContext<?>, OutputInitial>>>> reOrder() {
        Map<String, Map<String, List<Handler<HandlerContext<?>, OutputInitial>>>> handlerChainListMapCopy = Maps.newHashMap();
        Map<String, Map<String, List<HandlerConfig>>> handlerChainListMap = StorageTemp.handlerChainListMap;

        for (String flowType : handlerChainListMap.keySet()) {
            Map<String, List<Handler<HandlerContext<?>, OutputInitial>>> handlerChainListCopy = Maps.newHashMap();
            handlerChainListMapCopy.put(flowType, handlerChainListCopy);

            Map<String, List<HandlerConfig>> handlerChainList = handlerChainListMap.get(flowType);

            for (String handlerListChainType : handlerChainList.keySet()) {
                List<HandlerConfig> handlerConfigList = handlerChainList.get(handlerListChainType);

                List<Handler<HandlerContext<?>, OutputInitial>> handlerConfigListCopy = Lists.newArrayList();
                handlerChainListCopy.put(handlerListChainType, handlerConfigListCopy);
                Collections.sort(handlerConfigList, new Comparator<HandlerConfig>() {
                    @Override
                    public int compare(HandlerConfig handlerConfig1, HandlerConfig handlerConfig2) {
                        return handlerConfig2.getOrder() - handlerConfig1.getOrder();
                    }
                });

                for (HandlerConfig handlerConfig : handlerConfigList) {
                    handlerConfigListCopy.add(handlerConfig.getHandler());
                }
            }
        }

        return handlerChainListMapCopy;
    }

    private void destroy() {
        StorageTemp.destroyAllElements();
    }
}

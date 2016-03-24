package filterchain;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Author  : zhangxiaoguang
 * email   : zhangxiaoguang03@meituan.com
 * Date    : 16/3/23
 * Time    : 上午2:14
 * ---------------------------------------
 * Desc    : 临时性存储
 */
public class StorageTemp {
    public static Map<String, Map<String, List<HandlerConfig>>> handlerChainListMap = Maps.newConcurrentMap();
    public static Map<String, HandlerListChainAware<HandlerContext<?>, OutputInitial>> flowStarterMap = Maps.newConcurrentMap();

    public static void destroyAllElements() {
        handlerChainListMap.clear();
        flowStarterMap.clear();
    }
}

package filterchain;

import java.util.List;
import java.util.Map;

/**
 * Author  : zhangxiaoguang
 * email   : zhangxiaoguang03@meituan.com
 * Date    : 16/3/24
 * Time    : 上午12:17
 * ---------------------------------------
 * Desc    : 该接口主要用来注入
 */
public interface HandlerListChainAware<I, O> {
    /**
     * 注入处理器链条集合list
     *
     * @param handlerChainListMap 处理器链条集合
     */
    void setHandlerListChainMap(Map<String, List<Handler<I, O>>> handlerChainListMap);

    /**
     * 业务流类型
     *
     * @return
     */
    String flowType();
}

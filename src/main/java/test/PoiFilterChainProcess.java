package test;

import filterchain.Handler;
import filterchain.HandlerContext;
import filterchain.HandlerListChainAware;
import filterchain.TerminalException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Author  : zhangxiaoguang
 * email   : zhangxiaoguang03@meituan.com
 * Date    : 16/3/24
 * Time    : 上午2:08
 * ---------------------------------------
 * Desc    :
 */
@Component("poiFilterChainProcess")
public class PoiFilterChainProcess implements HandlerListChainAware<HandlerContext<String>, ContentWrapper> {

    Map<String, List<Handler<HandlerContext<String>, ContentWrapper>>> handlerChainListMap;

    @Override
    public void setHandlerListChainMap(Map<String, List<Handler<HandlerContext<String>, ContentWrapper>>> handlerChainListMap) {
        this.handlerChainListMap = handlerChainListMap;
    }

//    public void process(String filterChainType) {
//        HandlerContext<String> handlerContext = new HandlerContext<>();
//        ContentWrapper out = new ContentWrapper();
//
//        List<Handler<HandlerContext<String>, ContentWrapper>> list = handlerChainListMap.get(filterChainType);
//        try {
//            for (Handler<HandlerContext<String>, ContentWrapper> hbe : list) {
//                hbe.handle(handlerContext, out);
//            }
//        } catch (TerminalException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("result:" + out);
//    }

    @Override
    public String flowType() {
        return "poi";
    }
}

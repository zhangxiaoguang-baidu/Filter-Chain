package test;


import filterchain.AbstractHandler;
import filterchain.HandlerContext;
import filterchain.HandlerException;
import filterchain.HandlerInfo;
import org.springframework.stereotype.Component;

/**
 * Author  : zhangxiaoguang
 * email   : zhangxiaoguang03@meituan.com
 * Date    : 16/3/24
 * Time    : 上午2:16
 * ---------------------------------------
 * Desc    :
 */
@Component("cityHandler")
@HandlerInfo(
        handlerChainType = {
                "周边游推荐POI业务链",
                "点评筛选业务链"
        },
        order = {
                100, 10
        },
        flowType = "poi")
public class CityHandler extends AbstractHandler<HandlerContext<String>, ContentWrapper> {
    @Override
    protected ContentWrapper doHandle(HandlerContext<String> handleContext, ContentWrapper initOutput) throws HandlerException {
        initOutput.map.put(this.getClass().getSimpleName(), this.getClass().getName());

        System.out.println(this.getClass().getSimpleName() + " is handling...");

        return initOutput;
    }
}

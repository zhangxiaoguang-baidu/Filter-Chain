package test;



import filterchain.*;
import org.springframework.stereotype.Component;

/**
 * Author  : zhangxiaoguang
 * email   : zhangxiaoguang03@meituan.com
 * Date    : 16/3/24
 * Time    : 上午2:16
 * ---------------------------------------
 * Desc    :
 */
@Component("locationHandler")
@HandlerInfo(
        handlerChainType = {
                "周边游推荐POI业务链",
                "主站处理业务链"
        },
        order = {
                11, 8
        },
        flowType = "poi")
public class LocationHandler extends AbstractHandler<HandlerContext<String>, ContentWrapper> {
    @Override
    protected ContentWrapper doHandle(HandlerContext<String> handleContext, ContentWrapper initOutput) throws HandlerException {
        initOutput.map.put(this.getClass().getSimpleName(), this.getClass().getName());
        System.out.println(this.getClass().getSimpleName() + " is handling...");
        return initOutput;
    }

//    @Override
//    protected ErrorHandler errorHandler() {
//        return new ErrorHandler() {
//            @Override
//            public void handleError(Throwable t) {
//                t.printStackTrace();
//                System.out.println("哈哈哈哈哈哈哈哈");
//            }
//        };
//    }
//
//    @Override
//    protected boolean ifTerminalThisFlow() {
//        return true;
//    }
}

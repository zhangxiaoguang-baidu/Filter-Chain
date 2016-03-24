package filterchain;


/**
 * Author  : zhangxiaoguang
 * email   : zhangxiaoguang03@meituan.com
 * Date    : 16/3/23
 * Time    : 下午3:37
 * ---------------------------------------
 * Desc    :
 */
public class HandlerConfig {
    private Handler<HandlerContext<?>, OutputInitial> handler;
    private int order;

    public HandlerConfig(Handler<HandlerContext<?>, OutputInitial> handler) {
        this.handler = handler;
    }

    public HandlerConfig(Handler<HandlerContext<?>, OutputInitial> handler, int order) {
        this.handler = handler;
        this.order = order;
    }

    public Handler<HandlerContext<?>, OutputInitial> getHandler() {
        return handler;
    }

    public void setHandler(Handler<HandlerContext<?>, OutputInitial> handler) {
        this.handler = handler;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}

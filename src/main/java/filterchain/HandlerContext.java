package filterchain;

/**
 * Author  : zhangxiaoguang
 * email   : zhangxiaoguang03@meituan.com
 * Date    : 16/3/23
 * Time    : 下午8:28
 * ---------------------------------------
 * Desc    : 该context主要是用来进行各个processor之间的值传递
 */
public class HandlerContext<T> {
    protected T context;

    public T getContext() {
        return context;
    }

    public void setContext(T context) {
        this.context = context;
    }
}

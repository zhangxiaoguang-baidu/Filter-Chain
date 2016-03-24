package filterchain;

/**
 * Author  : zhangxiaoguang
 * email   : zhangxiaoguang03@meituan.com
 * Date    : 16/3/23
 * Time    : 下午23:31
 * ---------------------------------------
 * Desc    : 处理器核心处理类
 */
public interface Handler<C, O> {

    /**
     * 按需要处理每一个节点
     *
     * @param context    处理器上下文
     * @param initOutput 需要进行封装的初始化对象
     * @return
     * @throws TerminalException 终止该线程继续运行异常
     */
    O handle(final C context, O initOutput) throws TerminalException;
}

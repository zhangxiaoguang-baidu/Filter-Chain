package filterchain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author  : zhangxiaoguang
 * email   : zhangxiaoguang03@meituan.com
 * Date    : 16/3/23
 * Time    : 上午2:23
 * ---------------------------------------
 * Desc    : 抽象出顶层接口
 */
public abstract class AbstractHandler<I extends HandlerContext, O extends OutputInitial>
        implements Handler<I, O> {

    protected static final Logger log = LoggerFactory.getLogger(AbstractHandler.class);

    /**
     * 由子类处理业务逻辑
     *
     * @param handleContext 处理器上下文
     * @param initOutput    初始化输出结果
     * @return
     */
    protected abstract O doHandle(final I handleContext, O initOutput) throws HandlerException;

    @Override
    public O handle(final I handleContext, O initOutput) throws TerminalException {
        try {
            if (!ifHandle(handleContext)) {
                return initOutput;
            }
            beforeHandle(handleContext, initOutput);
            O outputWrapper = doHandle(handleContext, initOutput);
            if (outputWrapper == null) {
                throw new TerminalException("Error : output was changed to null!!!");
            }
            afterHandle(handleContext, outputWrapper);
            return outputWrapper;
        } catch (HandlerException | TerminalException e) {
            handleError(e);
            if (ifTerminalThisFlow()) {
                throw new TerminalException(e);
            }
        }
        return initOutput;
    }

    private void handleError(Exception e) {
        if (errorHandler() == null) {
            log.error("", e);
        } else {
            errorHandler().handleError(e);
        }
    }

    /**
     * 个性化定制，发生在处理节点任务之前，可以由子类个性化定制
     *
     * @param handleContext 处理器上下文
     * @param initOutput    初始化输出
     */
    protected void beforeHandle(final I handleContext, O initOutput) {
        //do nothing
    }

    /**
     * 个性化定制，发生在处理节点任务之后，可以由子类个性化定制
     *
     * @param handleContext 处理器上下文
     * @param initOutput    初始化输出
     */
    protected void afterHandle(final I handleContext, O initOutput) {
        //do nothing
    }

    /**
     * 是否回调处理异常，默认没有
     *
     * @return
     */
    protected ErrorHandler errorHandler() {
        return null;
    }

    /**
     * 某个handler发生异常，是否中断整个流程，默认不中断
     *
     * @return
     */
    protected boolean ifTerminalThisFlow() {
        return false;
    }

    /**
     * 该节点是否进行处理，默认处理
     *
     * @param handleContext 处理上下文
     * @return
     */
    protected boolean ifHandle(final I handleContext) {
        return true;
    }
}

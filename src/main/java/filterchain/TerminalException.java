package filterchain;

/**
 * Author  : zhangxiaoguang
 * email   : zhangxiaoguang03@meituan.com
 * Date    : 16/3/24
 * Time    : 上午3:14
 * ---------------------------------------
 * Desc    :
 */
public class TerminalException extends Exception {
    public TerminalException(String message) {
        super(message);
    }

    public TerminalException() {
    }

    public TerminalException(Throwable cause) {
        super(cause);
    }

    public TerminalException(String message, Throwable cause) {
        super(message, cause);
    }
}

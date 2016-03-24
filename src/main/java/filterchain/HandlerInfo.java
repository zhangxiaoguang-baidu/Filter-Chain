package filterchain;

import java.lang.annotation.*;

/**
 * Author  : zhangxiaoguang
 * email   : zhangxiaoguang03@meituan.com
 * Date    : 16/3/23
 * Time    : 上午2:02
 * ---------------------------------------
 * Desc    : 每一个handler node相关属性，一个handler可以被多个flowType业务流使用
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface HandlerInfo {

    String[] handlerChainType() default {"default"};//每一个handler node可以被多少种业务流使用

    String flowType() default "mainLine";//业务主线类型

    int[] order() default 0;//0代表最后处理
}

package com.redmount.template.core;

import com.redmount.template.util.Logger;
import lombok.Data;

@Data
public abstract class AbstractJob implements Job {
    /**
     * Job中需要用到的数据
     */
    public Object data;

    /**
     * 对Job管理器公开的唯一方法.
     * 内部会按照before/do/after的顺序进行执行.
     * 暂时不支持异步调用方法.
     *
     * @param startArgs 准备运行方法参数
     * @param doArgs    运行方法参数
     * @param afterArgs 运行后方法参数
     */
    public void runJob(String[] startArgs, String[] doArgs, String[] afterArgs) {
        Logger.info("BeforeJob");
        this.beforeJob(startArgs);
        Logger.info("DoingJob");
        this.doJob(doArgs);
        Logger.info("Done");
        this.afterJob(afterArgs);
        Logger.info("JobOver");
    }
}

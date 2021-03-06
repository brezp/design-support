package com.github.brezp.design.executorchain;

import com.github.brezp.design.executorchain.exector.AbstractExecutor;
import com.github.brezp.design.executorchain.chain.ApplicationChainContext;
import com.github.brezp.design.executorchain.exector.ExecutorContext;
import com.github.brezp.design.executorchain.exector.Executor;
import com.github.brezp.design.executorchain.chain.ExecutorChain;

import java.util.LinkedList;
import java.util.Map;

/**
 * ExecutorChainBootstrap
 *
 *
 * @author brezp
 */
public class ExecutorChainBootstrap<T extends ExecutorContext> implements ExecutorChain {

    private LinkedList<AbstractExecutor<T>> executors;

    private int pos = 0;

    @Override
    public void execute() throws Exception {
        if (pos < executors.size()) {
            AbstractExecutor<T> executorConfig = executors.get(pos++);
            Executor<T> executor = executorConfig.getExecutor();

            T executorContext;
            Map<String, Object> clazz2ExecutorContextMap = this.getChainContextRef().getConfigClazz2ExecutorContextMap();
            String key = executorConfig.getClass().getName();
            if (clazz2ExecutorContextMap.containsKey(key)) {
                //noinspection unchecked
                executorContext = (T) clazz2ExecutorContextMap.get(key);
            } else {
                executorContext = executorConfig.getExecutorContext();
            }
            // 执行具体业务逻辑代码
            executor.execute(executorContext, this);

            // 执行完成之后缓存ExecutorContext实例
            clazz2ExecutorContextMap.put(key, executorContext);

            // 执行后续操作，做一些资源关闭、任务状态更新等操作，并调用下一个Executor
            executor.postExecute(executorContext, this);
        }
    }

    public void addExecutor(AbstractExecutor<T> executor) {
        if (executors == null) {
            executors = new LinkedList<>();
        }
        executors.add(executor);
    }

    private ApplicationChainContext chainContextRef = new ApplicationChainContext();

    @Override
    public ApplicationChainContext getChainContextRef() {
        return this.chainContextRef;
    }
}

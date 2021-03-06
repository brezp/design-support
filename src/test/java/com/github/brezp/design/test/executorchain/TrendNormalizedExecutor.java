package com.github.brezp.design.test.executorchain;


import com.github.brezp.design.executorchain.exector.AbstractExecutor;
import com.github.brezp.design.executorchain.exector.Executor;

/**
 * TrendNormalizedExecutor
 *
 */
public class TrendNormalizedExecutor extends AbstractExecutor<TrendNormalizedExecutorContext> {

    private AppCategory appCategory;
    private String socialIndexAndType;
    private String ecomIndexAndType;
    private String normalizedTaskName;
    private String normalizedTaskDescription;
    private String time;
    private String lastTobeNormalizedFilePath;
    private String normalizedHdfsDir;

    public TrendNormalizedExecutor(
            Executor<TrendNormalizedExecutorContext> executor, AppCategory appCategory,
            String socialIndexAndType, String ecomIndexAndType,
            String normalizedTaskName, String normalizedTaskDescription, String time,
            String lastTobeNormalizedFilePath, String normalizedHdfsDir) {
        super(executor);
        this.appCategory = appCategory;
        this.socialIndexAndType = socialIndexAndType;
        this.ecomIndexAndType = ecomIndexAndType;
        this.normalizedTaskName = normalizedTaskName;
        this.normalizedTaskDescription = normalizedTaskDescription;
        this.time = time;
        this.lastTobeNormalizedFilePath = lastTobeNormalizedFilePath;
        this.normalizedHdfsDir = normalizedHdfsDir;
    }

    @Override
    public TrendNormalizedExecutorContext getExecutorContext() throws Exception {
        TrendNormalizedExecutorContext context = new TrendNormalizedExecutorContext();
        context.setAppCategory(appCategory)
                .setSocialIndexAndType(socialIndexAndType)
                .setEcomIndexAndType(ecomIndexAndType)
                .setNormalizedTaskName(normalizedTaskName)
                .setNormalizedTaskDescription(normalizedTaskDescription)
                .setTime(time)
                .setLastTobeNormalizedFilePath(lastTobeNormalizedFilePath)
                .setNormalizedHdfsDir(normalizedHdfsDir);
        return context;
    }
}

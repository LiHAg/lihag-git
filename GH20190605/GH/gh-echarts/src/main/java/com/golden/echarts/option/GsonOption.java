package com.golden.echarts.option;

import com.golden.echarts.utils.GsonUtil;
import com.golden.echarts.utils.OptionUtil;

/**
 * 增强的Option
 *
 * @author jinma.zheng
 */
public class GsonOption extends Option {

    /**
     * 在浏览器中查看
     */
    public void view() {
        OptionUtil.browse(this);
    }

    /**
     * 获取toString值
     */
    public String toString() {
        return GsonUtil.format(this);
    }

    /**
     * 获取toPrettyString值
     */
    public String toPrettyString() {
        return GsonUtil.prettyFormat(this);
    }

}

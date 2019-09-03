
package com.golden.echarts;

import com.golden.echarts.code.X;
import com.golden.echarts.model.line.Basic;
import com.golden.echarts.style.TextStyle;

import lombok.Getter;
import lombok.Setter;

/**
 * @author liuzh
 */
@Getter
@Setter
public class Title extends Basic<Title> implements Component {
    /**
     * 主标题文本，'\n'指定换行
     */
    private String text;
    //private String link;
    //private String target;
    //private String subtext;
    //private String sublink;

    /**
     * 获取text值
     */
    public String text() {
        return this.text;
    }
	  /**
     * 设置text值
     *
     * @param text
     */
    public Title text(String text) {
        this.text = text;
        return this;
    }
}

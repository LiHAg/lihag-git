package com.golden.echarts.model.line;

import com.golden.echarts.Component;
import com.golden.echarts.code.Align;
import com.golden.echarts.code.Orient;
import com.golden.echarts.model.scatter.Data;
import com.golden.echarts.style.TextStyle;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * @author jinma.zheng
 */
@Getter
@Setter
public class Legend extends Basic<Legend> implements Data<Legend>, Component {
    private Orient orient;//布局方式，默认为水平布局，可选为：'horizontal' | 'vertical'
    /**
     * 图例图形宽度
     */
    private Integer itemWidth;
    /**
     * 图例图形高度
     */
    private Integer itemHeight;
    /**
     * 文字样式
     *
     * @see com.golden.echarts.style.TextStyle
     */
    private TextStyle textStyle;
    /**
     * 选择模式，默认开启图例开关
     *
     * @see com.golden.echarts.code.SelectedMode
     */
    private Object selectedMode;
    /**
     * 配置默认选中状态，可配合LEGEND.SELECTED事件做动态数据载入
     */
    private Map<String, Boolean> selected;
    private List data;  //图例内容数组，数组项通常为{string}，每一项代表一个系列的name。

    private Align align;
    private String formatter;

    /**
     * 构造函数
     */
    public Legend() {
    }

    /**
     * 构造函数,参数:values
     *
     * @param values
     */
    public Legend(Object... values) {
        this.data(values);
    }

    public Align align() {
        return this.align;
    }

    public Legend align(Align align) {
        this.align = align;
        return this;
    }

    public String formatter() {
        return this.formatter;
    }

    public Legend formatter(String formatter) {
        this.formatter = formatter;
        return this;
    }
    /**
     * 设置textStyle值
     *
     * @param textStyle
     */
    public Legend textStyle(TextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    /**
     * 设置data值
     *
     * @param data
     */
    public Legend data(List data) {
        this.data = data;
        return this;
    }

    /**
     * 获取orient值
     */
    public Orient orient() {
        return this.orient;
    }

    /**
     * 设置orient值
     *
     * @param orient
     */
    public Legend orient(Orient orient) {
        this.orient = orient;
        return this;
    }

    /**
     * 获取itemWidth值
     */
    public Integer itemWidth() {
        return this.itemWidth;
    }

    /**
     * 设置itemWidth值
     *
     * @param itemWidth
     */
    public Legend itemWidth(Integer itemWidth) {
        this.itemWidth = itemWidth;
        return this;
    }

    /**
     * 获取itemHeight值
     */
    public Integer itemHeight() {
        return this.itemHeight;
    }

    /**
     * 设置itemHeight值
     *
     * @param itemHeight
     */
    public Legend itemHeight(Integer itemHeight) {
        this.itemHeight = itemHeight;
        return this;
    }

    /**
     * 文字样式
     *
     * @see com.golden.echarts.style.TextStyle
     */
    public TextStyle textStyle() {
        if (this.textStyle == null) {
            this.textStyle = new TextStyle();
        }
        return this.textStyle;
    }

    /**
     * 获取selectedMode值
     */
    public Object selectedMode() {
        return this.selectedMode;
    }

    /**
     * 设置selectedMode值
     *
     * @param selectedMode
     */
    public Legend selectedMode(Object selectedMode) {
        this.selectedMode = selectedMode;
        return this;
    }


    /**
     * 获取selected值
     *
     * @param name
     */
    public Boolean selected(String name) {
        if (this.selected == null) {
            return null;
        } else {
            return selected.get(name);
        }
    }

    /**
     * 设置默认选中状态
     *
     * @param name
     * @param selected
     * @return
     */
    public Legend selected(String name, Boolean selected) {
        if (!this.data.contains(name)) {
            throw new RuntimeException("Legend中不包含name为" + name + "的图例");
        }
        if (this.selected == null) {
            this.selected = new LinkedHashMap<String, Boolean>();
        }
        this.selected.put(name, selected);
        return this;
    }

    /**
     * 获取data值
     */
    public List data() {
        if (this.data == null) {
            this.data = new ArrayList();
        }
        return this.data;
    }

    /**
     * 添加图例属性
     *
     * @param values
     * @return
     */
    public Legend data(Object... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        this.data().addAll(Arrays.asList(values));
        return this;
    }
}

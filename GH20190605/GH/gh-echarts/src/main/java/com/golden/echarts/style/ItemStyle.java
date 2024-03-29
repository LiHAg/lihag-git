
package com.golden.echarts.style;

import java.io.Serializable;

import com.golden.echarts.style.itemstyle.Emphasis;
import com.golden.echarts.style.itemstyle.Normal;

/**
 * Description: ItemStyle
 *
 */
public class ItemStyle implements Serializable {

    private static final long serialVersionUID = 418674375057055357L;

    /**
     * 默认样式
     */
    private Normal normal;
    /**
     * 强调样式（悬浮时样式）
     */
    private Emphasis emphasis;
    /**
     * 面包屑
     */
    private Breadcrumb breadcrumb;
    /**
     * 二级边框宽度
     */
    private Integer childBorderWidth;
    /**
     * 二级边框颜色
     */
    private Object childBorderColor;

    /**
     * 获取normal值
     */
    public Normal normal() {
        if (this.normal == null) {
            this.normal = new Normal();
        }
        return this.normal;
    }

    /**
     * 设置normal值
     *
     * @param normal
     */
    public ItemStyle normal(Normal normal) {
        this.normal = normal;
        return this;
    }

    /**
     * 获取emphasis值
     */
    public Emphasis emphasis() {
        if (this.emphasis == null) {
            this.emphasis = new Emphasis();
        }
        return this.emphasis;
    }

    /**
     * 设置emphasis值
     *
     * @param emphasis
     */
    public ItemStyle emphasis(Emphasis emphasis) {
        this.emphasis = emphasis;
        return this;
    }

    /**
     * 获取normal值
     */
    public Normal getNormal() {
        return normal;
    }

    //以下属性是TreeMap特有

    /**
     * 设置normal值
     *
     * @param normal
     */
    public void setNormal(Normal normal) {
        this.normal = normal;
    }

    /**
     * 获取emphasis值
     */
    public Emphasis getEmphasis() {
        return emphasis;
    }

    /**
     * 设置emphasis值
     *
     * @param emphasis
     */
    public void setEmphasis(Emphasis emphasis) {
        this.emphasis = emphasis;
    }

    /**
     * 设置breadcrumb值
     *
     * @param breadcrumb
     */
    public ItemStyle breadcrumb(Breadcrumb breadcrumb) {
        this.breadcrumb = breadcrumb;
        return this;
    }

    /**
     * 获取breadcrumb值
     */
    public Breadcrumb breadcrumb() {
        if (this.breadcrumb == null) {
            this.breadcrumb = new Breadcrumb();
        }
        return this.breadcrumb;
    }

    /**
     * 设置childBorderWidth值
     *
     * @param childBorderWidth
     */
    public ItemStyle childBorderWidth(Integer childBorderWidth) {
        this.childBorderWidth = childBorderWidth;
        return this;
    }

    /**
     * 获取childBorderWidth值
     */
    public Integer childBorderWidth() {
        return this.childBorderWidth;
    }

    /**
     * 设置childBorderColor值
     *
     * @param childBorderColor
     */
    public ItemStyle childBorderColor(Object childBorderColor) {
        this.childBorderColor = childBorderColor;
        return this;
    }

    /**
     * 获取childBorderColor值
     */
    public Object childBorderColor() {
        return this.childBorderColor;
    }

    /**
     * 获取breadcrumb值
     */
    public Breadcrumb getBreadcrumb() {
        return breadcrumb;
    }

    /**
     * 设置breadcrumb值
     *
     * @param breadcrumb
     */
    public void setBreadcrumb(Breadcrumb breadcrumb) {
        this.breadcrumb = breadcrumb;
    }

    /**
     * 获取childBorderWidth值
     */
	public Integer getChildBorderWidth() {
        return childBorderWidth;
    }

    /**
     * 设置childBorderWidth值
     *
     * @param childBorderWidth
     */
    public void setChildBorderWidth(Integer childBorderWidth) {
        this.childBorderWidth = childBorderWidth;
    }

    /**
     * 获取childBorderColor值
     */
	public Object getChildBorderColor() {
        return childBorderColor;
    }

    /**
     * 设置childBorderColor值
     *
     * @param childBorderColor
     */
    public void setChildBorderColor(Object childBorderColor) {
        this.childBorderColor = childBorderColor;
    }
}

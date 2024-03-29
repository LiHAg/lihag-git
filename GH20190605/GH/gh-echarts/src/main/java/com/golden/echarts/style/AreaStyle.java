package com.golden.echarts.style;

import java.io.Serializable;

import com.golden.echarts.style.itemstyle.Normal;

/**
 * 区域填充样式
 *
 */
public class AreaStyle implements Serializable {

    private static final long serialVersionUID = -6547716731700677234L;

    /**
     * 颜色
     */
    private Object color;
    /**
     * 填充样式，目前仅支持'default'(实填充)
     */
    private Object type;
    /**
     * 面积图：杜洪明
     */
    private Normal normal;

    /**
     * 获取color值
     */
    public Object color() {
        return this.color;
    }

    /**
     * 设置color值
     *
     * @param color
     */
    public AreaStyle color(Object color) {
        this.color = color;
        return this;
    }

    /**
     * 获取type值
     */
    public Object type() {
        return this.type;
    }

    /**
     * 设置type值
     *
     * @param type
     */
    public AreaStyle type(Object type) {
        this.type = type;
        return this;
    }
    /**
     * 获取面积图：杜洪明
     */
    public Normal normal() {
    	if (this.normal == null) {
            this.normal = new Normal();
        }
    	return this.normal;
    }
    /**
     * 设置面积图：杜洪明
     * @param normal
     */
    public AreaStyle normal(Normal normal) {
    	this.normal = normal;
    	return this;
    }
    /**
     * 获取typeDefault值
     */
    public AreaStyle typeDefault() {
        this.type = "default";
        return this;
    }

    /**
     * 获取color值
     */
    public Object getColor() {
        return color;
    }

    /**
     * 设置color值
     *
     * @param color
     */
    public void setColor(Object color) {
        this.color = color;
    }

    /**
     * 获取type值
     */
    public Object getType() {
        return type;
    }

    /**
     * 设置type值
     *
     * @param type
     */
    public void setType(Object type) {
        this.type = type;
    }
}

package com.golden.echarts.option;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.golden.echarts.DataRange;
import com.golden.echarts.DataZoom;
import com.golden.echarts.Grid;
import com.golden.echarts.Polar;
import com.golden.echarts.Timeline;
import com.golden.echarts.Title;
import com.golden.echarts.Tooltip;
import com.golden.echarts.axis.Axis;
import com.golden.echarts.code.Trigger;
import com.golden.echarts.model.line.Legend;
import com.golden.echarts.model.scatter.Series;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: Option
 *
 * @author jinma.zheng
 */
@Getter
@Setter
public class Option implements Serializable {

    private static final long serialVersionUID = 4664955083296866542L;
    private Object backgroundColor;//全图默认背景，（详见backgroundColor），默认为无，透明
    private Timeline timeline;//时间轴，每个图表最多仅有一个时间轴控件
    private Title title;//标题（详见title），每个图表最多仅有一个标题控件
    //private Toolbox toolbox; 工具箱，每个图表最多仅有一个工具箱
    private Tooltip tooltip; //提示框（详见tooltip），鼠标悬浮交互时的信息提示
    private Legend legend; //图例，每个图表最多仅有一个图例，混搭图表共享
    /**
     * 值域选择（详见dataRange）,值域范围
     */
    private DataRange dataRange;
    /**
     * 数据区域缩放（详见dataZoom）,数据展现范围选择
     */
    private List<DataZoom> dataZoom;
    private Grid grid; //直角坐标系内绘图网格
    private List<Axis> xAxis;//直角坐标系中横轴数组（详见xAxis），数组中每一项代表一条横轴坐标轴，标准中规定最多同时存在2条横轴
    private List<Axis> yAxis;//直角坐标系中纵轴数组（详见yAxis），数组中每一项代表一条纵轴坐标轴，标准（1.0）中规定最多同时存在2条纵轴
    private List<Series> series;//驱动图表生成的数据内容（详见series），数组中每一项代表一个系列的特殊选项及数据

    /**
     * 极坐标
     */
    private List<Polar> polar;
    
    /**
     * 设置timeline值
     *
     * @param timeline
     */
    public Option timeline(Timeline timeline) {
        this.timeline = timeline;
        return this;
    }

    /**
     * 设置title值
     *
     * @param title
     */
    public Option title(Title title) {
        this.title = title;
        return this;
    }

    /**
     * 标题
     *
     * @param text
     * @return
     */
    public Option title(String text) {
        this.title().text(text);
        return this;
    }

    /**
     * 标题和副标题
     *
     * @param text
     * @param subtext
     * @return
     */
    public Option title(String text, String subtext) {
        this.title().text(text);
        return this;
    }


    /**
     * 设置tooltip值
     *
     * @param tooltip
     */
    public Option tooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    /**
     * 设置trigger值
     *
     * @param trigger
     */
    public Option tooltip(Trigger trigger) {
        this.tooltip().trigger(trigger);
        return this;
    }

    /**
     * 设置legend值
     *
     * @param legend
     */
    public Option legend(Legend legend) {
        this.legend = legend;
        return this;
    }

    /**
     * 设置dataRange值
     *
     * @param dataRange
     */
    public Option dataRange(DataRange dataRange) {
        this.dataRange = dataRange;
        return this;
    }

    /**
     * 设置dataZoom值
     *
     * @param dataZoom
     */
    public Option dataZoom(List<DataZoom> dataZoom) {
        this.dataZoom = dataZoom;
        return this;
    }

    /**
     * 设置dataZoom值
     *
     * @param dataZoom
     */
    public Option dataZoom(DataZoom... dataZoom) {
        if (dataZoom == null || dataZoom.length == 0) {
            return this;
        }
        this.dataZoom().addAll(Arrays.asList(dataZoom));
        return this;
    }

    /**
     * 设置grid值
     *
     * @param grid
     */
    public Option grid(Grid grid) {
        this.grid = grid;
        return this;
    }

    /**
     * 设置xAxis值
     *
     * @param xAxis
     */
    public Option xAxis(List<Axis> xAxis) {
        this.xAxis = xAxis;
        return this;
    }

    /**
     * 设置yAxis值
     *
     * @param yAxis
     */
    public Option yAxis(List<Axis> yAxis) {
        this.yAxis = yAxis;
        return this;
    }

    /**
     * 设置series值
     *
     * @param series
     */
    public Option series(List<Series> series) {
        this.series = series;
        return this;
    }


    /**
     * 时间轴（详见timeline），每个图表最多仅有一个时间轴控件
     */
    public Timeline timeline() {
        if (this.timeline == null) {
            this.timeline = new Timeline();
        }
        return this.timeline;
    }

    /**
     * 标题（详见title），每个图表最多仅有一个标题控件
     */
    public Title title() {
        if (this.title == null) {
            this.title = new Title();
        }
        return this.title;
    }

    /**
     * 提示框（详见tooltip），鼠标悬浮交互时的信息提示
     */
    public Tooltip tooltip() {
        if (this.tooltip == null) {
            this.tooltip = new Tooltip();
        }
        return this.tooltip;
    }

    /**
     * 图例（详见legend），每个图表最多仅有一个图例，混搭图表共享
     */
    public Legend legend() {
        if (this.legend == null) {
            this.legend = new Legend();
        }
        return this.legend;
    }

    /**
     * 添加图例（详见legend），每个图表最多仅有一个图例，混搭图表共享
     *
     * @param values
     * @return
     */
    public Option legend(Object... values) {
        this.legend().data(values);
        return this;
    }

    /**
     * 值域选择（详见dataRange）,值域范围
     */
    public DataRange dataRange() {
        if (this.dataRange == null) {
            this.dataRange = new DataRange();
        }
        return this.dataRange;
    }

    /**
     * 数据区域缩放（详见dataZoom）,数据展现范围选择
     */
    public DataZoom dataZoomNew() {
        DataZoom dataZoom = new DataZoom();
        this.dataZoom().add(dataZoom);
        return dataZoom;
    }

    /**
     * 数据区域缩放（详见dataZoom）,数据展现范围选择
     */
    public List<DataZoom> dataZoom() {
        if (this.dataZoom == null) {
            this.dataZoom = new ArrayList<DataZoom>();
        }
        return this.dataZoom;
    }

    /**
     * 直角坐标系内绘图网格（详见grid）
     */
    public Grid grid() {
        if (this.grid == null) {
            this.grid = new Grid();
        }
        return this.grid;
    }

    /**
     * 直角坐标系中横轴数组（详见xAxis），数组中每一项代表一条横轴坐标轴，标准（1.0）中规定最多同时存在2条横轴
     */
    public List<Axis> xAxis() {
        if (this.xAxis == null) {
            this.xAxis = new ArrayList<Axis>();
        }
        return this.xAxis;
    }

    /**
     * 添加x轴
     *
     * @param values
     * @return
     */
    public Option xAxis(Axis... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        if (this.xAxis().size() == 2) {
            throw new RuntimeException("xAxis已经存在2个，无法继续添加!");
        }
        if (this.xAxis().size() + values.length > 2) {
            throw new RuntimeException("添加的xAxis超出了最大允许的范围:2!");
        }
        this.xAxis().addAll(Arrays.asList(values));
        return this;
    }

    /**
     * 直角坐标系中横轴数组（详见xAxis），数组中每一项代表一条横轴坐标轴，标准（1.0）中规定最多同时存在2条横轴
     */
    public List<Axis> yAxis() {
        if (this.yAxis == null) {
            this.yAxis = new ArrayList<Axis>();
        }
        return this.yAxis;
    }

    /**
     * 添加y轴
     *
     * @param values
     * @return
     */
    public Option yAxis(Axis... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        if (this.yAxis().size() == 2) {
            throw new RuntimeException("yAxis已经存在2个，无法继续添加!");
        }
        if (this.yAxis().size() + values.length > 2) {
            throw new RuntimeException("添加的yAxis超出了最大允许的范围:2!");
        }
        this.yAxis().addAll(Arrays.asList(values));
        return this;
    }

    /**
     * 驱动图表生成的数据内容（详见series），数组中每一项代表一个系列的特殊选项及数据
     */
    public List<Series> series() {
        if (this.series == null) {
            this.series = new ArrayList<Series>();
        }
        return this.series;
    }

    /**
     * 添加数据
     *
     * @param values
     * @return
     */
    public Option series(Series... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        this.series().addAll(Arrays.asList(values));
        return this;
    }
    
    /**
     * 获取polar值
     */
    public List<Polar> polar() {
        if (this.polar == null) {
            this.polar = new ArrayList<Polar>();
        }
        return this.polar;
    }

    /**
     * 设置polar值
     *
     * @param polar
     */
    public Option polar(List<Polar> polar) {
        this.polar = polar;
        return this;
    }

    /**
     * 设置values值
     *
     * @param values
     */
    public Option polar(Polar... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        this.polar().addAll(Arrays.asList(values));
        return this;
    }
}

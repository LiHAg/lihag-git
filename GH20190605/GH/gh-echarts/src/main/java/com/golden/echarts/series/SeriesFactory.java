package com.golden.echarts.series;

import com.golden.echarts.model.scatter.Scatter;

/**
 * Series工厂类
 *
 */
public class SeriesFactory {

    public static Line newLine() {
        return new Line();
    }

    public static Line newLine(String name) {
        return new Line(name);
    }

    public static Lines newLines() {
        return new Lines();
    }

    public static Lines newLines(String name) {
        return new Lines(name);
    }

    public static Bar newBar() {
        return new Bar();
    }

    public static Bar newBar(String name) {
        return new Bar(name);
    }

    public static Scatter newScatter() {
        return new Scatter();
    }

    public static Scatter newScatter(String name) {
        return new Scatter(name);
    }



}

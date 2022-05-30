package com.juka.graphmap.view.swing.components;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.IntStream;

public class ScrollPaneBuilder {

    private final JList<String> list;
    private final JScrollPane scrollPane;

    private ScrollPaneBuilder() {
        list = new JList<>();
        scrollPane = new JScrollPane(list);
    }

    public static ScrollPaneBuilder anHorizontalList() {
        ScrollPaneBuilder builder = new ScrollPaneBuilder();
        builder.list.setLayoutOrientation(JList.VERTICAL);
        builder.list.setVisibleRowCount(-1);
        builder.list.setFixedCellWidth(200);
        builder.list.setFixedCellHeight(20);
        builder.list.setFont(new Font("Arial", Font.PLAIN, 16));
        return builder;
    }

    public ScrollPaneBuilder withData(List<String> data) {
        list.setListData(data.toArray(new String[0]));
        return this;
    }

    public ScrollPaneBuilder withSingleSelection() {
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return this;
    }

    public ScrollPaneBuilder withMultipleSelection() {
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        return this;
    }

    public ScrollPaneBuilder withSize(int width, int height) {
        scrollPane.setPreferredSize(new Dimension(width, height));
        scrollPane.setMinimumSize(new Dimension(width, height));
        scrollPane.setMaximumSize(new Dimension(width, height));
        scrollPane.setSize(new Dimension(width, height));
        return this;
    }

    public ScrollPaneBuilder alwaysScrollVertical() {
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return this;
    }

    public ScrollPaneBuilder neverScrollHorizontal() {
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return this;
    }

    public ScrollPaneBuilder alwaysScrollHorizontal() {
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        return this;
    }

    public ScrollPaneBuilder withSelectedValue(String value) {
        list.setSelectedValue(value, true);
        return this;
    }

    public ScrollPaneBuilder withSelectedValues(List<String> steps) {
        list.setSelectedIndices(IntStream.range(0, list.getModel().getSize()).filter(i -> steps.stream().anyMatch(step -> list.getModel().getElementAt(i).equals(step))).toArray());
        return this;
    }

    public String getSelectedValue() {
        return list.getSelectedValue();
    }

    public List<String> getSelectedValues() {
        return list.getSelectedValuesList();
    }

    public ScrollPaneBuilder isYCentered() {
        scrollPane.setAlignmentY(Component.CENTER_ALIGNMENT);
        return this;
    }

    public JScrollPane build() {
        return scrollPane;
    }

}

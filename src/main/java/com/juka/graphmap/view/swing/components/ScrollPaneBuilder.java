package com.juka.graphmap.view.swing.components;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Swing JScrollPane of JList builder.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class ScrollPaneBuilder {

    private final JList<String> list;
    private final JScrollPane scrollPane;

    private ScrollPaneBuilder() {
        list = new JList<>();
        scrollPane = new JScrollPane(list);
    }

    /**
     * Create a new ScrollPaneBuilder.
     *
     * @return a ScrollPaneBuilder
     */
    public static ScrollPaneBuilder anHorizontalList() {
        ScrollPaneBuilder builder = new ScrollPaneBuilder();
        builder.list.setLayoutOrientation(JList.VERTICAL);
        builder.list.setVisibleRowCount(-1);
        builder.list.setFixedCellWidth(200);
        builder.list.setFixedCellHeight(20);
        builder.list.setFont(new Font("Arial", Font.PLAIN, 16));
        return builder;
    }

    /**
     * Set the data of the List.
     *
     * @param data the data to set
     * @return the ScrollPaneBuilder
     */
    public ScrollPaneBuilder withData(List<String> data) {
        list.setListData(data.toArray(new String[0]));
        return this;
    }

    /**
     * Set the selection mode of the List to single selection.
     *
     * @return the ScrollPaneBuilder
     */
    public ScrollPaneBuilder withSingleSelection() {
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return this;
    }

    /**
     * Set the selection mode of the List to multiple selection.
     *
     * @return the ScrollPaneBuilder
     */
    public ScrollPaneBuilder withMultipleSelection() {
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        return this;
    }

    /**
     * Set the size of the List.
     *
     * @param width  the width of the List
     * @param height the height of the List
     * @return the ScrollPaneBuilder
     */
    public ScrollPaneBuilder withSize(int width, int height) {
        scrollPane.setPreferredSize(new Dimension(width, height));
        scrollPane.setMinimumSize(new Dimension(width, height));
        scrollPane.setMaximumSize(new Dimension(width, height));
        scrollPane.setSize(new Dimension(width, height));
        return this;
    }

    /**
     * Allow the vertical scroll of the List.
     *
     * @return the ScrollPaneBuilder
     */
    public ScrollPaneBuilder alwaysScrollVertical() {
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return this;
    }

    /**
     * Deny the vertical scroll of the List.
     *
     * @return the ScrollPaneBuilder
     */
    public ScrollPaneBuilder neverScrollHorizontal() {
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return this;
    }

    /**
     * Allow the horizontal scroll of the List.
     *
     * @return the ScrollPaneBuilder
     */
    public ScrollPaneBuilder alwaysScrollHorizontal() {
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        return this;
    }

    /**
     * Set the selected value of the List.
     *
     * @param value the selected value
     * @return the ScrollPaneBuilder
     */
    public ScrollPaneBuilder withSelectedValue(String value) {
        list.setSelectedValue(value, true);
        return this;
    }

    /**
     * Set the selected values of the List.
     *
     * @param values the selected values
     * @return the ScrollPaneBuilder
     */
    public ScrollPaneBuilder withSelectedValues(List<String> values) {
        list.setSelectedIndices(IntStream.range(0, list.getModel().getSize()).filter(i -> values.stream().anyMatch(step -> list.getModel().getElementAt(i).equals(step))).toArray());
        return this;
    }

    /**
     * Get the selected value of the List.
     *
     * @return the selected value
     */
    public String getSelectedValue() {
        return list.getSelectedValue();
    }

    /**
     * Get the selected values of the List.
     *
     * @return the selected values
     */
    public List<String> getSelectedValues() {
        return list.getSelectedValuesList();
    }

    /**
     * Center the List in the Y axis.
     *
     * @return the ScrollPaneBuilder
     */
    public ScrollPaneBuilder isYCentered() {
        scrollPane.setAlignmentY(Component.CENTER_ALIGNMENT);
        return this;
    }

    /**
     * Build the final ScrollPane of the List;
     *
     * @return the final ScrollPane of the List
     */
    public JScrollPane build() {
        return scrollPane;
    }

}

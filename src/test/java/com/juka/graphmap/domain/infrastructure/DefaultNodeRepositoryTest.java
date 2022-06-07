package com.juka.graphmap.domain.infrastructure;

import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;
import com.juka.graphmap.infrastructure.DefaultNodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultNodeRepositoryTest {

    private DefaultNodeRepository nodeRepository;

    @BeforeEach
    void setUp() {
        nodeRepository = new DefaultNodeRepository();
    }

    @Test
    void getAllNodes_shouldReturnEmptyList_whenNoNodeAdded() {

        List<Node> nodes = nodeRepository.getAllNodes();

        assertThat(nodes).isEmpty();
    }

    @Test
    void getAllNodes_shouldReturnListWith1Element_when1NodeAdded() {

        Node node = new Node("node1", NodeType.CITY, 0, 0);
        nodeRepository.addNode(node);

        List<Node> nodes = nodeRepository.getAllNodes();

        assertThat(nodes).isEqualTo(Collections.singletonList(node));
    }

    @Test
    void getNode_shouldReturnNode_whenNodeAdded() {

        Node node = new Node("node1", NodeType.CITY, 0, 0);
        nodeRepository.addNode(node);

        Node foundNode = nodeRepository.getNode("node1");

        assertThat(foundNode).isEqualTo(node);
    }

    @Test
    void getNode_shouldReturnNull_whenNodeNotAdded() {

        Node foundNode = nodeRepository.getNode("node1");

        assertThat(foundNode).isNull();
    }

    @Test
    void hasEncounteredError_shouldReturnFalse_whenNoError() {

        boolean hasEncounteredError = nodeRepository.hasEncounteredError();

        assertThat(hasEncounteredError).isFalse();
    }

    @Test
    void hasEncounteredError_shouldReturnTrue_whenError() {

        nodeRepository.encounterError();

        boolean hasEncounteredError = nodeRepository.hasEncounteredError();

        assertThat(hasEncounteredError).isTrue();
    }

}

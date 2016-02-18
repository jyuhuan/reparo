package me.yuhuan.reparo.factory

import me.yuhuan.reparo.builder.GraphBuilder

import scala.language.higherKinds

/**
 * Contains common constructors for any graph.
 *
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 * @tparam G The kind of the graph.
 */
trait GraphFactory[+G[_, _, _]] {
  /**
   * Returns a new builder for this kind of graph.
   *
   * @tparam K The type of the index of an vertex.
   * @tparam V The type of the data in an vertex.
   * @tparam E The type of the data of an edge.
   *
   * @return A new builder for this kind of graph.
   */
  implicit def newBuilder[K, V, E]: GraphBuilder[K, V, E, G[K, V, E]]

  /**
   * Creates a graph by specifying the vertices and edges.
   *
   * @param nodes The vertices of the graph.
   * @param edges The edges of the graph.
   * @tparam K The type of the index of an vertex.
   * @tparam V The type of the data in an vertex.
   * @tparam E The type of the data of an edge.
   *
   * @return The graph which contains the specified vertices and edges.
   */
  def apply[K, V, E](nodes: (K, V)*)(edges: (K, K, E)*): G[K, V, E] = {
    val b = newBuilder[K, V, E]
    b.addNodes(nodes: _*)
    b.addEdges(edges: _*)
    b.result
  }
}

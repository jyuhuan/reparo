package me.yuhuan.reparo

import me.yuhuan.marauder._
import me.yuhuan.reparo.Graph._
import me.yuhuan.reparo.builder.GraphBuilder

import scala.annotation.unchecked.{uncheckedVariance => uv}
import scala.language.higherKinds


/**
 * Represents a directed graph.
 *
 * @tparam K The type of the index of an vertex.
 * @tparam N The type of the data in an vertex.
 * @tparam E The type of the data of an edge.
 *
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait Graph[@specialized(Int) K, +N, +E] { outer ⇒

  /**
   * Gets the data of the vertex at the given index.
   * @param i The index of the desired vertex.
   * @return The data of the desired vertex.
   */
  def apply(i: K): N

  /**
   * Gets the data of the edge at the given pair of indices.
   * @param i The first index of the edge.
   * @param j The second index of the edge.
   * @return The label of the desired edge.
   */
  def apply(i: K, j: K): E

  /**
   * Gets the vertex object at the given index.
   * @param i The index of the vertex.
   * @return The vertex object.
   */
  def nodeAt(i: K): Node[K, N] = Node(outer, i)

  /**
   * Gets the edge object at the given pair of indices.
   * @param i The first index of the edge.
   * @param j The second index of the edge.
   * @return The desired edge object.
   */
  def edgeAt(i: K, j: K): Edge[K, E] = Edge(outer, i, j)

  /**
   * Gets the indices of all nodes.
   * @return A set of indices of all nodes.
   */
  def nodeKeys: Iterable[K]

  /**
   * Gets the index pairs of all edges.
   * @return A set of index pairs of all edges.
   */
  def edgeKeys: Iterable[(K, K)]

  /**
   * Gets the vertex objects of all vertices.
   * @return A set of all vertex objects of all vertices.
   */
  def nodes: Iterable[Node[K, N]] = nodeKeys.view.map(i ⇒ nodeAt(i))

  /**
   * Gets the edge objects of all edges.
   * @return A set of edge objects of all edges.
   */
  def edges: Iterable[Edge[K, E]] = edgeKeys.view.map(p ⇒ edgeAt(p._1, p._2))

  /**
   * Gets the indices of the outgoing vertices of the vertex at the given index.
   * @param i The index of the vertex queried.
   * @return A set of indices of all outgoing vertices of the vertex queried.
   */
  def outgoingNodeKeysOf(i: K): Iterable[K]

  /**
   *
   * @param i
   * @return
   */
  def outgoingEdgeKeysOf(i: K): Iterable[(K, K)]

  /**
   * Gets the vertex objects of the outgoing vertices of the vertex at the given index.
   * @param i The index of the vertex queried.
   * @return A set of vertex objects of all outgoing vertices of the vertex queried.
   */
  def outgoingNodesOf(i: K): Iterable[Node[K, N]] = outgoingNodeKeysOf(i).map(v ⇒ nodeAt(v))

  /**
   * Gets the edge objects of the outgoing vertices of the vertex at the given index.
   * @param i The index of the vertex queried.
   * @return A set of edge objects of all outgoing vertices of the vertex queried.
   */
  def outgoingEdgesOf(i: K): Iterable[Edge[K, E]] = outgoingEdgeKeysOf(i).map(e ⇒ edgeAt(e._1, e._2))

  /**
   * The out degree of the vertex at the given index.
   * @param i The index of the vertex queried.
   * @return The out degree of the vertex queried.
   */
  def ougDegreeOf(i: K): Int = outgoingNodeKeysOf(i).size

  def pathBetween(i: K, j: K): Path[K, E] = {
    val StateSpace = new StateSpaceWithLazyAction[K, E] {
      def succ(s: K, a: E): scala.Seq[K] = outer.outgoingEdgesOf(s).find(_.data == a).map(_.vj.id).toSeq
      def succAction(s: K): scala.Seq[E] = outer.outgoingEdgesOf(s).map(_.data).toSeq
    }
    GraphSearch.depthFirstWithLazyAction(i, (k: K) => k == j)(StateSpace)
  }

  def mapNodes[V2](f: N ⇒ V2): Graph[K, V2, E] = new Graph[K, V2, E] {
    def apply(i: K): V2 = f(outer.apply(i))
    def apply(i: K, j: K): E = outer.apply(i, j)
    def edgeKeys = outer.edgeKeys
    def nodeKeys = outer.nodeKeys
    def outgoingEdgeKeysOf(i: K) = outer.outgoingEdgeKeysOf(i)
    def outgoingNodeKeysOf(i: K) = outer.outgoingNodeKeysOf(i)
  }

  def mapEdges[E2](f: E ⇒ E2): Graph[K, N, E2] = new Graph[K, N, E2] {
    def apply(i: K): N = outer.apply(i)
    def apply(i: K, j: K): E2 = f(outer.apply(i, j))
    def edgeKeys = outer.edgeKeys
    def nodeKeys = outer.nodeKeys
    def outgoingEdgeKeysOf(i: K) = outer.outgoingEdgeKeysOf(i)
    def outgoingNodeKeysOf(i: K) = outer.outgoingNodeKeysOf(i)
  }


  def filterNodes(f: N ⇒ Boolean): Graph[K, N, E] = new Graph[K, N, E] {
    def apply(i: K): N = outer.apply(i)
    def apply(i: K, j: K) = outer.apply(i, j)
    def edgeKeys = outer.edgeKeys.filter(p ⇒ f(apply(p._1)) && f(apply(p._2)))
    def nodeKeys = outer.nodeKeys.filter(p ⇒ f(apply(p)))
    def outgoingEdgeKeysOf(i: K) = outer.outgoingEdgeKeysOf(i)
    def outgoingNodeKeysOf(i: K) = outer.outgoingNodeKeysOf(i)
  }

  def filterEdges(f: E ⇒ Boolean): Graph[K, N, E] = new Graph[K, N, E] {
    override def apply(i: K): N = outer.apply(i)
    override def apply(i: K, j: K) = {
      if (!f(outer.edgeAt(i, i).data)) throw new Exception(s"Vertex $i does not exist!")
      else if (!f(outer.edgeAt(i, i).data)) throw new Exception(s"Vertex $j does not exist!")
      else outer.apply(i, j)
    }
    override def edgeKeys = outer.edgeKeys.filter(p ⇒ f(apply(p._1, p._2)))
    override def nodeKeys = outer.nodeKeys
    override def outgoingEdgeKeysOf(i: K) = outer.outgoingEdgeKeysOf(i)
    override def outgoingNodeKeysOf(i: K) = outer.outgoingNodeKeysOf(i)
  }

  def zip[V2, E2](that: Graph[K, V2, E2]): Graph[K, (N, V2), (E, E2)] = new Graph[K, (N, V2), (E, E2)] {
    def apply(i: K): (N, V2) = (outer(i), that(i))
    def apply(i: K, j: K): (E, E2) = (outer(i, j), that(i, j))
    def edgeKeys = outer.edgeKeys
    def nodeKeys = outer.nodeKeys
    def outgoingEdgeKeysOf(i: K) = outer.outgoingEdgeKeysOf(i)
    def outgoingNodeKeysOf(i: K) = outer.outgoingNodeKeysOf(i)
  }

  def zipNodes[V2, E2](that: Graph[K, V2, E2]): Graph[K, (N, V2), E] = new Graph[K, (N, V2), E] {
    def apply(i: K): (N, V2) = (outer(i), that(i))
    def apply(i: K, j: K): E = outer(i, j)
    def edgeKeys = outer.edgeKeys
    def nodeKeys = outer.nodeKeys
    def outgoingEdgeKeysOf(i: K) = outer.outgoingEdgeKeysOf(i)
    def outgoingNodeKeysOf(i: K) = outer.outgoingNodeKeysOf(i)
  }

  def zipEdges[V2, E2](that: Graph[K, V2, E2]): Graph[K, N, (E, E2)] = new Graph[K, N, (E, E2)] {
    def apply(i: K): N = outer(i)
    def apply(i: K, j: K): (E, E2) = (outer(i, j), that(i, j))
    def edgeKeys = outer.edgeKeys
    def nodeKeys = outer.nodeKeys
    def outgoingEdgeKeysOf(i: K) = outer.outgoingEdgeKeysOf(i)
    def outgoingNodeKeysOf(i: K) = outer.outgoingNodeKeysOf(i)
  }

  override def hashCode: Int = ???


  /**
   * Builds the given kind of graph that has the same vertices and edges as this graph.
   *
   * @param builder Builder for the desired kind of graph.
   * @tparam G The desired graph kind.
   * @return A graph of the given kind has the same vertices and edges as this graph.
   */
  def to[G[_, _, _]](implicit builder: GraphBuilder[K, N@uv, E@uv, G[K, N@uv, E@uv]]): G[K, N@uv, E@uv] = {
    val b = builder
    b.addNodes(this.nodeKeys.map(i ⇒ i → this (i)).toSeq: _*)
    b.addEdges(this.edgeKeys.map(p ⇒ (p._1, p._2, this (p._1, p._2))).toSeq: _*)
    b.result
  }
}

object Graph {
  /**
   * Represents a vertex in the graph.
   * Contains both the index and the data of the vertex.
   * @param id The index of the vertex.
   */
  case class Node[K, +N](g: Graph[K, N, _], id: K) {
    def data: N = g(id)
    def succ: Iterable[Node[K, N]] = g.outgoingNodesOf(id)
    override def toString = s"$id - ${g(id)}"
  }

  /**
   * Represents an edge in the graph.
   * Contains both the indices and the data of the edge.
   * @param id1 The first index of the edge.
   * @param id2 The second index of the edge.
   */
  case class Edge[K, +E](g: Graph[K, _, E], id1: K, id2: K) {
    def data: E = g(id1, id2)
    def vi = g.nodeAt(id1)
    def vj = g.nodeAt(id2)
    override def toString = s"$vi --- $data --> $vj"
  }
}

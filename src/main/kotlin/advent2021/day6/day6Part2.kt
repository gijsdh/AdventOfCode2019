package advent2021.day6
import getResourceAsText
import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    val input = getResourceAsText("input.txt")
    val inputTest = getResourceAsText("inputExample.txt")
    val orbits = input.lines().map { it.split(')') }

    println(orbits)
    var planetMap: HashMap<String, Planet2> = HashMap();

    orbits.forEach { s ->
        planetMap.putIfAbsent(s[0], Planet2(s[0]))
        planetMap.putIfAbsent(s[1], Planet2(s[1]))
        assert(planetMap[s[1]]?.connections == null)
        planetMap[s[1]]?.connections?.add(planetMap[s[0]]!!)
        planetMap[s[0]]?.connections?.add(planetMap[s[1]]!!)
    }

    println(BFS(planetMap["YOU"]!!, planetMap["SAN"]!!))
    
}


fun BFS(rootPlanet: Planet2, goalPlanet: Planet2): Int {
    var set: MutableSet<Planet2> = mutableSetOf()
    val queue = LinkedList<Pair<Planet2, Int>>() //currenct planet and depth
    set.add(rootPlanet)
    queue.add(Pair(rootPlanet, 0))
    while (queue.isNotEmpty()) {
        var pair = queue.pop()
        var planet = pair.first
        if (planet.name == goalPlanet.name) {
            return pair.second - 2 //YOU and SAN are not counted
        }
        for (nextPlanet in planet.connections) {
            if (!set.contains(nextPlanet)) {
                set.add(nextPlanet)
                queue.add(Pair(nextPlanet, pair.second + 1))
            }
        }
    }
    return -1
}

//Wiki BFS algorithme :
//procedure BFS(G, root) is
//2      let Q be a queue
//3      label root as explored
//4      Q.enqueue(root)
//5      while Q is not empty do
//6          v := Q.dequeue()
//7          if v is the goal then
//8              return v
//9          for all edges from v to w in G.adjacentEdges(v) do
//10              if w is not labeled as explored then
//11                  label w as explored
//12                  Q.enqueue(w)


class Planet2(val name: String) {
    var connections: MutableList<Planet2> = mutableListOf()
    override fun toString(): String {
        return "Planet(name='$name)"
    }
}



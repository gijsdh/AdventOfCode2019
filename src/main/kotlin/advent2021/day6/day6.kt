package advent2021.day6
import getResourceAsText

fun main(args: Array<String>) {
    val input = getResourceAsText("input.txt")
    val inputTest = getResourceAsText("inputExample.txt")
    val orbits = inputTest.lines().map { it.split(')') }

    println(orbits)
    var planetMap: HashMap<String, Planet> = HashMap();

    orbits.forEach { s ->
        planetMap.putIfAbsent(s[0], Planet(s[0]))
        planetMap.putIfAbsent(s[1], Planet(s[1]))
        assert(planetMap[s[1]]?.orbit == null)
        planetMap[s[1]]?.orbit = planetMap[s[0]]
    }
    var sum = 0
    for (planet in planetMap.values) {
        sum += countOrbits(planet) - 1 // -1 so that it does not count itself
    }

    println(sum)
}

fun countOrbits(planet: Planet): Int {
    if (planet.orbit == null) {
        return 1
    } else return 1 + countOrbits(planet.orbit!!)
}

class Planet(val name: String) {
    var orbit: Planet? = null
}
package main

class Wall(
        var x: Int,
        var y: Int
){
    override fun toString(): String {
        return "*"
    }
}
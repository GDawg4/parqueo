package main

class Level(
        var wallList: ArrayList<Wall>,
        var spotList: ArrayList<Spot>,
        var name: String,
        var color: String,
        var platesList: ArrayList<String> = ArrayList()
)
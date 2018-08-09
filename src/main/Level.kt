package main

class Level(
        var wallList: ArrayList<Wall>,
        var spotList: ArrayList<Spot>,
        var name: String,
        var color: String,
        var id: String,
        var freeSpaces: Int = spotList.size,
        var height: Int,
        var width: Int
){
    fun plateAlreadyIn(plate:String):Boolean{
        spotList.forEach {
            if (it.plateHere == plate){
                return true
            }
        }
        return false
    }

    fun stillFreeSPaces():Boolean{
        if (freeSpaces == 0){
            return false
        }
        return true
    }

    override fun toString(): String {
        var finalString = """
            Nombre: $name
            Identificador: $id
            Color: $color
            Mapa:
        """.trimIndent()
        for (i in 0..height){
            finalString += "\n"
            for (s in 0..width){
                var isEmptySpace = true
                wallList.forEach {
                    if(it.x == s && it.y == i){
                        finalString += it
                        isEmptySpace = false
                    }
                }
                spotList.forEach {
                    if(it.x == s && it.y == i){
                        finalString += it
                        isEmptySpace = false
                    }
                }
                if (isEmptySpace){
                    finalString += " "
                }
            }
        }
        return finalString
    }
}
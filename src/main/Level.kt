package main

class Level(
        var wallList: ArrayList<Wall>,
        var spotList: ArrayList<Spot>,
        var name: String,
        var color: String,
        var id: String,
        var platesList: ArrayList<String> = ArrayList()
){
    fun plateAlreadyIn(plate:String):Boolean{
        platesList.forEach {
            if (it == plate){
                return true
            }
        }
        return false
    }
    override fun toString(): String {
        var finalString = """
            Nombre: $name
            Identificador: $id
            Color: $color
            Mapa: \n
        """.trimIndent()
        for (i in 0..9){
            for (s in 0..9){
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
            finalString += "\n"
        }
        return finalString
    }
}
import main.Spot
import main.Wall
import main.Level
import java.io.File

var levelList: ArrayList<Level> = ArrayList()
var newLevelWalls: ArrayList<Wall> = ArrayList()
var newLevelSpot: ArrayList<Spot> = ArrayList()

fun colorIsTaken(color: String):Boolean{
    levelList.forEach {
        if (it.color == color) {
            return true
        }
    }
    return false
}

fun readFile(direction: String):ArrayList<String>{
    val mapAsLines: ArrayList<String> = ArrayList()
    File(direction).forEachLine{mapAsLines.add(it)}
    return mapAsLines
}

fun spotIsRepeated(map:ArrayList<String>):Boolean{
    var testList:ArrayList<String> = ArrayList()
    map.forEach{
        it.forEach {
            var testString = Character.toString(it)
            if(testString in testList){
                return true
            }
        }
    }
    return false
}

fun directionIsValid(direction: String): Boolean{
    try {
        var newMap = readFile(direction)
        if (spotIsRepeated(newMap)){
            return false
        }
        return true
    }catch (e: java.io.FileNotFoundException){
        return false
    }
}

fun showMenu(option: Int):String{
    when(option){
        0->return "Menu 1"
    }
    return "No válido"
}

fun createLists(map: ArrayList<String>){
    var x = 0
    var y = 0
    map.forEach {
        it.forEach {
            var charAsString = Character.toString(it)
            findSigns(charAsString,x,y)
            x++
        }
        y++
    }
}

fun findSigns(sign: String, x: Int, y:Int ){
    if (sign == "*"){
        val newWall = Wall(
                x = x,
                y = y
        )
        newLevelWalls.add(newWall)
    }
    else if(sign in "A".."Z" || sign in "a".."z" || sign in "1".."9"){
        val newSpot = Spot(
                x = x,
                y = y,
                spotName = sign
        )
        newLevelSpot.add(newSpot)
    }
}


fun main(args: Array<String>) {
    var wantsToContinue = true
    do {
        showMenu(1)
        var adminOrUser = readLine()!!
        when(adminOrUser){
            "1"->{
                var newLevelName:String
                var newLevelColor: String
                var newLevelDirection:String

                println("Ingrese el nombre del nuevo nivel")
                newLevelName = readLine()!!
                do {
                    println("Ingrese el color del nuevo nivel")
                    newLevelColor = readLine()!!
                }while (colorIsTaken(newLevelColor))

                do {
                    println("Ingrese la dirección de la estructura del mapa")
                    newLevelDirection = readLine()!!
                }while (!directionIsValid(newLevelDirection))

                createLists(readFile(newLevelDirection))


            }
        }
    }while (wantsToContinue)
}
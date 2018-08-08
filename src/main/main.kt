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

fun nameIsTaken(name:String):Boolean{
    levelList.forEach {
        if(it.name == name){
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
            }else
            {
                testList.add(testString)
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
        0->return """
            1.Administrador
            2. Conductor
            3. Salir
        """.trimIndent()
        1->return """
            1.Crear nivel
            2.Eliminar nivel
            3.Ver todos los niveles
            4.Salir
        """.trimIndent()
        2->return """
            1.Ingresar placa
            2. Salir
        """.trimIndent()

        3->return """
            1.Ingresar placa
            2.Salir
        """.trimIndent()
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
        x = 0
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
                name = sign
        )
        newLevelSpot.add(newSpot)
    }
}

fun levelExists(id: String):Boolean{
    levelList.forEach {
        if (it.name == id){
            return true
        }
    }
    return false
}

fun removeLevel(id:String){
    levelList.forEach {
        if (it.id == id){
            levelList.remove(it)
        }
    }
}

fun getSpecificLevel(name:String):Level?{
    levelList.forEach {
        if (it.name == name){
            return it
        }
    }
    return null
}

fun plateIsRegistered(plate:String):Level?{
    levelList.forEach {
        if(it.plateAlreadyIn(plate)){
            return it
        }
    }
    return null
}

fun chooseSpot(name:String, plate:String, spot:String):Boolean{
    getSpecificLevel(name)?.spotList?.forEach {
        if (it.name == spot){
            it.isTaken = true
            it.plateHere = plate
            return true
        }
    }
    return false
}

fun main(args: Array<String>) {
    var wantsToContinue = true
    var levelID = 1
    do {
        println(showMenu(0))
        var adminOrUser = readLine()!!
        when (adminOrUser) {
            "1" -> {
                do {
                    println(showMenu(1))
                    var adminOption = readLine()!!
                    var wantsToContinueAsAdmin = true
                    when (adminOption) {
                        "1" -> {
                            var newLevelColor: String
                            var newLevelDirection: String
                            var newLevelName:String
                            do {
                                println("Ingrese el nombre del nuevo nivel")
                                newLevelName = readLine()!!
                            }while (nameIsTaken(newLevelName))
                            do {
                                println("Ingrese el color del nuevo nivel")
                                newLevelColor = readLine()!!
                            } while (colorIsTaken(newLevelColor))

                            do {
                                println("Ingrese la dirección de la estructura del mapa")
                                newLevelDirection = readLine()!!
                            } while (!directionIsValid(newLevelDirection))

                            createLists(readFile(newLevelDirection))
                            var newLevel = Level(
                                    name = newLevelName,
                                    color = newLevelColor,
                                    wallList = newLevelWalls,
                                    spotList = newLevelSpot,
                                    id = levelID.toString()
                            )
                            levelList.add(newLevel)
                            println("El nuevo nivel ha sido creado con el identificador único de $levelID")
                            levelID++
                            newLevelWalls = ArrayList()
                            newLevelSpot = ArrayList()
                        }
                        "2" -> {
                            println("Ingrese el identificador \"ID\" del mapa que desea borrar")
                            var deleteLevelName = readLine()!!
                            if (levelExists(deleteLevelName)) {
                                removeLevel(deleteLevelName)
                            } else {
                                println("Nivel no encontrado, favor intentar nuevamente")
                            }
                        }
                        "3" -> {
                            levelList.forEach {
                                println(it.toString())
                            }
                        }
                        "4" -> {
                            wantsToContinueAsAdmin = false
                        }
                    }
                } while (wantsToContinueAsAdmin)
            }
            "2" -> {
                do {
                    var wantsToContinueAsUser = true
                    println(showMenu(3))
                    var userOption = readLine()!!
                    when (userOption) {
                        "1" -> {
                            println("Ingrese la placa que desea ingresar")
                            var plateToCheck = readLine()!!
                            if (plateIsRegistered(plateToCheck) != null) {
                                levelList.forEach {
                                    println(it.id + "."+ it.name)
                                }
                                println("Ingrese el nombre del nivel dónde desea parquearse")
                                var levelChoice = readLine()!!
                                if (levelExists(levelChoice)) {
                                    println(getSpecificLevel(levelChoice).toString())
                                    println("Qué lugar desea ocupar?")
                                    var chosenSpot = readLine()!!
                                    if (chooseSpot(levelChoice, plateToCheck, chosenSpot)) {
                                        println("Lugar apartado!")
                                    } else {
                                        println("Este lugar no existe favor intentar nuevamente")
                                    }
                                } else {
                                    println("Este nivel no existe, favor intentar nuevamente")
                                }
                            } else {
                                println("Esta placa ya está ingresada")
                                println(plateIsRegistered(plateToCheck))
                            }
                        }
                    }
                } while (wantsToContinueAsUser)
            }
        }

    } while (wantsToContinue)
}
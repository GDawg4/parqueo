package main

class Spot(
        var x: Int,
        var y: Int,
        var name: String,
        var isTaken: Boolean = false,
        var plateHere: String = ""
){
    fun ocuupySpot(plate: String){
        this.isTaken = false
        this.plateHere = plate
    }

    override fun toString(): String {
        if(isTaken){
            return "@"
        }
        return name
    }

}
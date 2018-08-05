package main

class Spot(
        var spotName: String,
        var isTaken: Boolean,
        var plateHere: String
){
    fun ocuupySpot(plate: String){
        this.isTaken = false
        this.plateHere = plate
    }
}
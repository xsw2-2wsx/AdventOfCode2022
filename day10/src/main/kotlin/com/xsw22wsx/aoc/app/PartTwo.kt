import com.xsw22wsx.aoc.app.executeInstructions
import com.xsw22wsx.aoc.app.readInstructions
import kotlin.math.abs

fun main() {
    for((index, sprite) in executeInstructions(readInstructions()).withIndex()) {

        if(abs(sprite - index % 40) <= 1) print("#")
        else print(".")

        if((index + 1) % 40 == 0) println()
    }

}